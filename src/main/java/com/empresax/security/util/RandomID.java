package com.empresax.security.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RandomID {
    private static final Random random = new SecureRandom();

    private static String randomKey(int length) {
        return String.format("%" + length + "s", new BigInteger(length * 5/*base 32,2^5*/, random)
                .toString(32)).replace('\u0020', '0');
    }

    public static String generate() {
        return randomKey(16).toUpperCase();
    }

}