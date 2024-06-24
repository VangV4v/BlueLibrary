package com.vang.authemployeeservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test1")
public class TestController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String test() {

        return "Test";
    }
}
