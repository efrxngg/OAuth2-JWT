//package com.empresax.security.util;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class AppConsole implements CommandLineRunner {
//
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String pasw = "root1234";
//        String encpasw = passwordEncoder.encode(pasw);
//        System.out.println(encpasw);
//        System.out.println(passwordEncoder.matches(pasw, encpasw));
//    }
//
//}
//
