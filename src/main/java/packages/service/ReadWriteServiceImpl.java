package packages.service;

import packages.model.Car;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class ReadWriteServiceImpl implements ReadWriteService {

    public void FileWriter(ArrayList<Car> list, String fileName) {
        fileName = fileName + ".txt";
        try (FileWriter writer = new FileWriter(new File(fileName), false)) {
            list.forEach(car -> {
                String strF = String.format("Year: %d, brand: %s, madel %s, cost: %d\n",
                        car.getYear(), car.getBrand(), car.getModel(), car.getCost());
                System.out.print(strF);
                try {
                    writer.write(strF);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The list saved to file CarInfo.txt\n");
    }
    public void FileReader(String fileName) {
        System.out.println("The list is loading from file CarInfo.txt");
        fileName = fileName + ".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String s;
            while((s=br.readLine())!=null){
                System.out.println(s);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
