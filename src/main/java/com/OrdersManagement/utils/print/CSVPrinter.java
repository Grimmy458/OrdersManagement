package com.OrdersManagement.utils.print;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CSVPrinter {

    public static void print(String filePath, String[] headers, ArrayList<ArrayList<String>> data) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filePath);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(file), StandardCharsets.UTF_8));
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

        }
    }
}
