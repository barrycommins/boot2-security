package com.barrycommins.boot2security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
class DemoController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    String hello() {
        return "hello";
    }
}
