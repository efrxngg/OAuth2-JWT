package com.empresax.security.util;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class AppConsole implements CommandLineRunner {

    @Override
    @Transactional
    public void run(String... args) throws Exception {


    }

}
