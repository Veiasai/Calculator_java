package com.veiasai;

import com.veiasai.security.MySecurity;
import com.veiasai.security.UserService;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@RestController
@SpringBootApplication
public class Service extends SpringBootServletInitializer {

    protected final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Service.class);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/calculator/{expression}")
    @ResponseBody
    public String Cal(@PathVariable String expression,HttpServletRequest httpServletRequest){
        logger.debug("访问cal:" + expression);
        Calculator cal = new Calculator();
        Scanner in = new Scanner(expression);
        cal.setInput(new Scanner(in.nextLine()));
        cal.cal();
        if (cal.getState() == 0)
        {
            logger.debug("success:" + cal.getResult_double());
            return String.valueOf(cal.getResult_double());
        }
        else
        {
            logger.debug("fail:" + cal.getE().toString());
            return cal.getE().toString();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Service.class, args);
    }

}
