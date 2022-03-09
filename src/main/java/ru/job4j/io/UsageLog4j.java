package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 1;
        long l = 2L;
        double d = 3.0;
        float f = 4.0f;
        char c = 'c';
        boolean bool = true;
        byte b = 5;
        short s = 6;

        LOG.debug("int: {}, long: {}, double: {}, float: {}, char: {}, boolean: {}, byte: {}, short: {}", i, l, d, f, c, bool, b, s);
    }
}
