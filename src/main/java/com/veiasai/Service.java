package com.veiasai;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@Controller
@EnableAutoConfiguration
public class Service {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/calculator/{expression}")
    @ResponseBody
    public String Cal(@PathVariable String expression){
        Calculator cal = new Calculator();
        Scanner in = new Scanner(expression);
        cal.setInput(new Scanner(in.nextLine()));
        cal.cal();
        if (cal.getState() == 0)
        {
            return String.valueOf(cal.getResult_double());
        }
        else
        {
            return cal.getE().toString();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Service.class, args);
    }
}
