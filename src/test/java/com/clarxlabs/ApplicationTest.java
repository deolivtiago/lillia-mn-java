package com.clarxlabs;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest(transactional = false)
class ApplicationTest {

    @NonNull
    private final EmbeddedApplication<?> application;

    ApplicationTest(@NonNull EmbeddedApplication<?> application) {
        this.application = application;
    }

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }
}
