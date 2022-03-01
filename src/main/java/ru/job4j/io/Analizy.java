package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader in = new BufferedReader(new FileReader(source))) {
            String startPeriod = "";
            String endPeriod = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] codeToDateArray = line.split(" ", 2);
                String errorCode = codeToDateArray[0];
                String dateTime = codeToDateArray[1];

                if (("400".equals(errorCode) || "500".equals(errorCode)) && startPeriod.isBlank()) {
                    startPeriod = dateTime;
                } else if (("200".equals(errorCode) || "300".equals(errorCode)) && !startPeriod.isBlank()) {
                    endPeriod = dateTime;
                    out.println(startPeriod + ";" + endPeriod);
                    startPeriod = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("server-log.txt", "unavailable.csv");
    }
}
