package com.example.demo;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/data")
    public String data(Model model) {
        GreetingController runScript = new GreetingController();
        String response = runScript.script();
        model.addAttribute("response", response);
        return "data";
    }

    public String script() {
        try (PowerShell powerShell = PowerShell.openSession()) {
            //Increase timeout to give enough time to the script to finish
            Map<String, String> config = new HashMap<>();
            config.put("maxWait", "80000");

            //Execute script
            PowerShellResponse response = powerShell.configuration(config).executeScript("get_data.ps1");

            //Print results if the script
            return ("Script output:\n" + response.getCommandOutput());
        } catch(PowerShellNotAvailableException ex) {
            //Handle error when PowerShell is not available in the system
            //Maybe try in another way?
        }


        return null;
    }

}
