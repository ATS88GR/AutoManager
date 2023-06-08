package org.example.service;

import org.example.model.Car;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

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
    public static void jsonFileWriter(ArrayList<Car> list, File file){
        JSONArray jsonArray = new JSONArray();
        for (Car car: list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("year", car.getYear());
            jsonObject.put("brand", car.getBrand());
            jsonObject.put("model", car.getModel());
            jsonObject.put("cost", car.getCost());
            jsonArray.add(jsonObject);
            try (FileWriter writer = new FileWriter(file)){
                writer.write(jsonArray.toJSONString());
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    public static void jsonFileReader(File file) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray carArray = (JSONArray) parser.parse(new FileReader(file));
            Iterator iterator = carArray.iterator();
            while(iterator.hasNext()) System.out.println(iterator.next());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
