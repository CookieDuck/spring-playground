package com.example.controller;

import com.example.model.Shape;
import com.example.service.MathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/pi")
    public String getPi() {
        return String.valueOf(Math.PI).substring(0, 17);
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "operation", required = false, defaultValue = "add") MathService.Operation operation,
                            @RequestParam Integer x, @RequestParam Integer y) {
        return MathService.generateExpressionAndResult(operation, x, y);
    }

    @PostMapping("/sum")
    public String sum(@RequestParam(value = "n") List<Integer> values) {
        return MathService.generateExpressionAndSum(values);
    }

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String volume(@PathVariable Integer length,
                         @PathVariable Integer width,
                         @PathVariable Integer height) {
        return MathService.generateExpressionAndVolume(length, width, height);
    }

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    produces = MediaType.TEXT_HTML_VALUE)
    public String area(Shape shape) {
        if (shape.isCircle()) {
            return MathService.generateExpressionAndCircleArea(shape.getRadius());
        } else if (shape.isRectangle()) {
            return MathService.generateExpressionAndRectangleArea(shape.getWidth(), shape.getHeight());
        }
        return "Invalid";
    }
}
