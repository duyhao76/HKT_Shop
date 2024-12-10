package vn.HKT.utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {

    private String value;

    public HttpUtils(String value) {
        this.value = value;
    }

    public <T> T toEntity(Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(value, clazz);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    public <T> List<T> toListEntity(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            // Nếu `value` là JSON array, parse toàn bộ array.
            list = new ObjectMapper().readValue(value, 
                new ObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return list;
    }

    public static HttpUtils of(BufferedReader bufferedReader) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new HttpUtils(stringBuilder.toString());
    }
}
