package br.org.ark.secureapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProtectedApiController {

    @GetMapping
    public Object doSomething() {
        return "Protected Message!";
    }
}
