package org.example;

public class StatisticStorage {
    private static int intNumber = 0;
    private static int floatNumber = 0;
    private static int stringNumber = 0;
    private static double maxNumber = Double.MIN_VALUE;
    private static double minNumber = Double.MAX_VALUE;
    private static double totalSum = 0;
    private static long maxStringLength = Long.MIN_VALUE;
    private static long minStringLength = Long.MAX_VALUE;

    public static void addInt(double number) {
        intNumber++;
        isMaximumNumber(number);
        isMinimumNumber(number);
        totalSum += number;
    }

    public static void addFloat(double number) {
        floatNumber++;
        isMaximumNumber(number);
        isMinimumNumber(number);
        totalSum += number;
    }

    public static void addString(String string) {
        stringNumber++;
        isMaximumLength(string);
        isMinimumLength(string);
    }

    private static void isMaximumNumber(double number) {
        if (number > maxNumber) {
            maxNumber = number;
        }
    }

    private static void isMinimumNumber(double number) {
        if (number < minNumber) {
            minNumber = number;
        }
    }

    private static void isMaximumLength(String string) {
        if (string.length() > maxStringLength) {
            maxStringLength = string.length();
        }
    }

    private static void isMinimumLength(String string) {
        if (string.length() < minStringLength) {
            minStringLength = string.length();
        }
    }

    public static void printShortStat() {
        System.out.println("____________________________SHORT STATISTIC____________________________");
        System.out.println("Number of Integers: \t" + intNumber);
        System.out.println("Number of Floats: \t\t" + floatNumber);
        System.out.println("Number of Strings: \t\t" + stringNumber);
        int totalNumber = intNumber + floatNumber + stringNumber;
        System.out.println("Total number: \t" + totalNumber);
    }

    public static void printFullStat() {
        System.out.println("____________________________FULL STATISTIC____________________________");
        System.out.println("Number of Integers: \t" + intNumber);
        System.out.println("Number of Floats: \t\t" + floatNumber);
        System.out.println("Number of Strings: \t\t" + stringNumber);
        int totalNumber = intNumber + floatNumber + stringNumber;
        System.out.println("Total number: \t" + totalNumber);
        if (intNumber + floatNumber > 0) {
            System.out.println("Max value: \t" + maxNumber);
            System.out.println("Min value: \t" + minNumber);
            double avgValue = totalSum / (intNumber + floatNumber);
            System.out.println("Avg value: \t" + avgValue);
        }
        else {
            System.out.println("Max value: \t-");
            System.out.println("Min value: \t-");
            System.out.println("Avg value: \t-");
        }
        System.out.println("Total sum: \t" + totalSum);
        if (stringNumber > 0) {
            System.out.println("Max length of string: \t" + maxStringLength);
            System.out.println("Min length of string: \t" + minStringLength);
        }
        else {
            System.out.println("Max length of string: \t-");
            System.out.println("Min length of string: \t-");
        }
    }
}