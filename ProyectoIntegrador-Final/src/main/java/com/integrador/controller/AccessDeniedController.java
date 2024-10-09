package com.integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {

    @RequestMapping("/error/403")
    public String error403() {
        return "error/403";
    }
}
