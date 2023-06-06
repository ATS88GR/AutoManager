package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class JsonService {
    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void parseCarJson(String resultJson) {
        try {
            JSONObject carJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
            System.out.println("Марка авто: " + carJsonObject.get("brand"));
            System.out.println("Модель авто: " + carJsonObject.get("model"));
            System.out.println("Год выпуска: " + carJsonObject.get("year"));
            System.out.println("Стоимость: " + carJsonObject.get("cost"));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
    public static String buildCarsJson(ArrayList<Car> list){
        JSONArray jsonArray = new JSONArray();
        for (Car car: list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("year", car.getYear());
            jsonObject.put("brand", car.getBrand());
            jsonObject.put("model", car.getModel());
            jsonObject.put("cost", car.getCost());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
