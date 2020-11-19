package net.springboot.csvapi.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.springboot.csvapi.model.OfficeUser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"DisplayName", "WhenCreated", "SignInName","isLicensed" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<OfficeUser> csvToOfficeUsers(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<OfficeUser> OfficeUsers = new ArrayList<OfficeUser>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                OfficeUser user = new OfficeUser(
                        csvRecord.get("SignInName"),
                        csvRecord.get("DisplayName"),
                        Boolean.parseBoolean(csvRecord.get("IsLicensed")),
                        csvRecord.get("WhenCreated")
                );

                OfficeUsers.add(user);
            }

            return OfficeUsers;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
