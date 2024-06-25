package org.falcon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurrencyConverterController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
