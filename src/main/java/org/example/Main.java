package org.example;


import org.apache.commons.cli.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Options utilOptions = new Options();

        Option appendOption = new Option("a", false, "add data to an existing file");
        appendOption.setArgs(0);
        utilOptions.addOption(appendOption);

        Option prefixNameOption = new Option("p", true, "add prefix to file name");
        prefixNameOption.setArgs(1);
        utilOptions.addOption(prefixNameOption);

        Option outputPathOption = new Option("o", true, "add path for output files");
        outputPathOption.setArgs(1);
        utilOptions.addOption(outputPathOption);

        OptionGroup statsOption = new OptionGroup();
        statsOption.addOption(new Option("s", false, "print short stat"));
        statsOption.addOption(new Option("f", false, "print full stat"));
        utilOptions.addOptionGroup(statsOption);

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdParser.parse(utilOptions, args);
        } catch (ParseException e) {
            System.err.println("Invalid command line arguments were passed");
            System.exit(1);
        }
        String[] programArgs = commandLine.getArgs();

        String intSavePath = "integers.txt";
        String floatSavePath = "floats.txt";
        String stringSavePath = "strings.txt";
        if (commandLine.hasOption("p")) {
            intSavePath = commandLine.getOptionValues("p")[0] + intSavePath;
            floatSavePath = commandLine.getOptionValues("p")[0] + floatSavePath;
            stringSavePath = commandLine.getOptionValues("p")[0] + stringSavePath;
        }

        if (commandLine.hasOption("o")) {
            intSavePath = commandLine.getOptionValues("o")[0] + "/" + intSavePath;
            floatSavePath = commandLine.getOptionValues("o")[0] + "/" + floatSavePath;
            stringSavePath = commandLine.getOptionValues("o")[0] + "/" + stringSavePath;
        }

        int intCounter = 0;
        int floatCounter = 0;
        int stringCounter = 0;

        for (int i = 0; i < programArgs.length; i++) {
            BufferedReader buffReader = null;
            String currentLine = null;
            try {
                buffReader = new BufferedReader(new FileReader(programArgs[i]));
                currentLine = buffReader.readLine();
            } catch (IOException e) {
                System.err.println("Invalid file names were passed");
                System.exit(1);
            }



            try {
                while (currentLine != null) {
                    if (TypeDetector.isLineInteger(currentLine)) {
                        File integers = new File(intSavePath);
                        if (!(integers.exists() /*&& integers.isFile())*/)) {
                            integers.createNewFile();
                        }
                        FileWriter intWriter = new FileWriter(integers, commandLine.hasOption("a") || (intCounter != 0));
                        intWriter.write(currentLine + "\n");
                        intWriter.close();
                        intCounter++;
                        if (commandLine.hasOption("f")) {
                            StatisticStorage.createFullStat(Integer.parseInt(currentLine));
                        }
                        else {
                            StatisticStorage.createShortStat(Integer.parseInt(currentLine));
                        }
                        currentLine = buffReader.readLine();
                    }
                    else if (TypeDetector.isLineFloat(currentLine)) {
                        File floats = new File(floatSavePath);
                        if (!(floats.exists() && floats.isFile())) {
                            floats.createNewFile();
                        }
                        FileWriter floatWriter = new FileWriter(floats, commandLine.hasOption("a") || (floatCounter != 0));
                        floatWriter.write(currentLine + "\n");
                        floatWriter.close();
                        floatCounter++;
                        if (commandLine.hasOption("f")) {
                            StatisticStorage.createFullStat(Double.parseDouble(currentLine));
                        }
                        else {
                            StatisticStorage.createShortStat(Double.parseDouble(currentLine));
                        }
                        currentLine = buffReader.readLine();
                    }
                    else {
                        File strings = new File(stringSavePath);
                        if (!(strings.exists() && strings.isFile())) {
                            strings.createNewFile();
                        }
                        FileWriter stringWriter = new FileWriter(strings, commandLine.hasOption("a") || (stringCounter != 0));
                        stringWriter.write(currentLine + "\n");
                        stringWriter.close();
                        stringCounter++;
                        if (commandLine.hasOption("f")) {
                            StatisticStorage.createFullStat(currentLine);
                        }
                        else {
                            StatisticStorage.createShortStat(currentLine);
                        }
                        currentLine = buffReader.readLine();
                    }

                }
                buffReader.close();
            } catch (IOException e) {
                System.err.println("Incorrect save path was passed\n ");
                System.exit(1);
            }
        }

        if (commandLine.hasOption("f")) {
            StatisticStorage.printFullStat();
        }
        else {
            StatisticStorage.printShortStat();
        }
    }
}