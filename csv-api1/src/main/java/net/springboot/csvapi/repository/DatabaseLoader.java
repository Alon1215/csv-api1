
package net.springboot.csvapi.repository;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import net.springboot.csvapi.service.CSVService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final OfficeUserRepository repository;

	@Autowired
	public DatabaseLoader(OfficeUserRepository repository) {
		this.repository = repository;
	}

	@Autowired
	CSVService fileService;


	@Override
	public void run(String... strings) throws Exception {
		final String CSV_FILE_NAME = "o365userlist.csv";

		// for now, run the script only if the csv file doesn't exist
		File checkFile = new File(CSV_FILE_NAME);
		if (!checkFile.exists()) {
			// execute the PowerShell script
			PowerShellScript();
		}

		FileInputStream input = new FileInputStream(checkFile);
		MultipartFile multipartFile = new MockMultipartFile("file",
				checkFile.getName(), "text/plain", IOUtils.toByteArray(input));

		fileService.save(multipartFile);

	}

	public void PowerShellScript() {
		try (PowerShell powerShell = PowerShell.openSession()) {
			// increase timeout to give enough time to the script to finish
			Map<String, String> config = new HashMap<>();
			config.put("maxWait", "80000");

			//Execute script
			powerShell.configuration(config).executeScript("get_data.ps1");
			//PowerShellResponse response = powerShell.configuration(config).executeScript("get_data.ps1");

			//Print results if the script
			//return ("Script output:\n" + response.getCommandOutput());
		}
		catch(PowerShellNotAvailableException e) {
			//Handle error when PowerShell is not available in the system
			//Maybe try in another way?
			throw new RuntimeException("fail to run PowerShell script: " + e.getMessage());
		}
	}

}
