package com.clarxlabs;

import io.micronaut.http.annotation.*;

@Controller("/lillia")
public class LilliaController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}