package packages.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import packages.model.Car;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * the class to read/write json files
 */
public class JsonReadWriteServiceImpl implements ReadWriteService {

    /**
     * to parse json information from
     * @param url to
     * @return string
     */
    public String parseUrl(URL url){
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

    /**
     * to print of parsing result of JSON string
     * @param resultJson
     */
    public void parseCarJson(String resultJson){
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

    /**
     * to build json array string of a Car objects from Car
     * @param list
     * @return json array string of a Car objects
     */
    public String buildCarsJson(ArrayList<Car> list){
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

    public void fileWriter(ArrayList<Car> list, String fileName){
        fileName = fileName + ".json";
        JSONArray jsonArray = new JSONArray();
        for (Car car: list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("year", car.getYear());
            jsonObject.put("brand", car.getBrand());
            jsonObject.put("model", car.getModel());
            jsonObject.put("cost", car.getCost());
            jsonArray.add(jsonObject);
            try (FileWriter writer = new FileWriter(fileName)){
                writer.write(jsonArray.toJSONString());
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("The list saved to file " + fileName + "\n");
    }

    public ArrayList<Car> fileReader(String fileName){
        System.out.println("The list is loading from file " + fileName + ".json");
        fileName = fileName + ".json";
        JSONParser parser = new JSONParser();
        ArrayList <Car> list = new ArrayList<>();
        try {
            JSONArray carArray = (JSONArray) parser.parse(new FileReader(fileName));
            Iterator iterator = carArray.iterator();
            while(iterator.hasNext()) {
                Car car = new Car();
                JSONObject jsonCarObject = (JSONObject) iterator.next();
                System.out.println(jsonCarObject);
                //System.out.println(iterator.next());
                car.setYear(Math.toIntExact((Long) jsonCarObject.get("year")));
                car.setBrand((String) jsonCarObject.get("brand"));
                car.setModel((String) jsonCarObject.get("model"));
                car.setCost(Math.toIntExact((Long) jsonCarObject.get("cost")));
                list.add(car);
            }
            System.out.println();
            return list;
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
