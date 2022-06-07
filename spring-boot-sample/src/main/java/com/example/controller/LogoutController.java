package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LogoutController {
    @PostMapping("/logout")
    public String postLogout() {
        log.info("ログアウト");
        return "redirect:/login";
    }
}
