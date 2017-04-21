package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @GetMapping("/math/pi")
    public String getPi() {
        return String.valueOf(Math.PI).substring(0, 17);
    }
}
