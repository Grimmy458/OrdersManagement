package com.OrdersManagement.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.*;

public class FileUtils {
    private static final Gson gson = new Gson();
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        return file.delete();
    }

    public static <T> T ReadJsonAndCast(String filePath, TypeToken<T> type) throws FileNotFoundException, IOException, JsonParseException {
        InputStream in = null;
        BufferedReader reader = null;
        try  {
            in = new FileInputStream(filePath);
            reader = new BufferedReader(new InputStreamReader(in));
            String json = reader.readLine();
            T result = gson.fromJson(json, type.getType());
            in.close();
            return result;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public static <T> void WriteJson(String filePath, T object) throws SecurityException, NullPointerException, IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(object);
            writer.write(json);
        } catch (IOException e) {
            throw e;
        }
    }
}
