package com.clarxlabs.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/lillia")
public class LilliaController {

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "oi";
    }
}
