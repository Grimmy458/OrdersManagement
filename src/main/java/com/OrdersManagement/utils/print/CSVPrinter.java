package com.OrdersManagement.utils.print;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVPrinter {

    public static void print(String filePath, String[] headers, ArrayList<ArrayList<String>> data) throws IOException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.join(",", headers));
            bufferedWriter.newLine();
            for (ArrayList<String> row : data) {
                bufferedWriter.write(String.join(",", row));
                bufferedWriter.newLine();
            }
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
