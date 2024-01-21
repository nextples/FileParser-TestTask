package org.example;

import java.util.regex.*;

public class TypeDetector {

    public static boolean isLineInteger(String line) {
        Pattern intPattern = Pattern.compile("^-?(0|[1-9]\\d*)$");
        Matcher intMatcher = intPattern.matcher(line);
        return intMatcher.find();
    }

    public static boolean isLineFloat(String line) {
        //  рассматриваем 3 случая: либо число задано через точку(без экспоненты), либо число задано через экспоненту (без точки), либо и то и то
        Pattern floatPattern = Pattern.compile("^(-?[0-9]+\\.[0-9]+)$|^(-?(0|[1-9]\\d*)E[-+][0-9]+)$|^(-?[0-9]+\\.[0-9]+E[-+][0-9]+)$");
        Matcher floatMatcher = floatPattern.matcher(line);
        return floatMatcher.find();
    }

    public static boolean isLineString(String line) {
        return (!isLineInteger(line) && !isLineFloat(line));
    }
}
