package packages.service;

import packages.model.Car;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteServiceImpl implements ReadWriteService {

    public void fileWriter(ArrayList<Car> list, String fileName) {
        fileName = fileName + ".txt";
        try (FileWriter writer = new FileWriter(new File(fileName), false)) {
            list.forEach(car -> {
                String strF = String.format("Year: %d, brand: %s, model: %s, cost: %d\n",
                        car.getYear(), car.getBrand(), car.getModel(), car.getCost());
                //System.out.print(strF);
                try {
                    writer.write(strF);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nThe list saved to file " + fileName + "\n");
    }
    @Override
    public ArrayList<Car> fileReader(String fileName) {
        System.out.println("The list is loading from file " + fileName + ".txt");
        fileName = fileName + ".txt";
        ArrayList <Car> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String s;
            while((s=br.readLine())!=null){
                System.out.println(s);
                String [] sArr = s.split(",");
                list.add(new Car(Integer.parseInt(sArr[0].substring(6)), sArr[1].substring(8),
                        sArr[2].substring(8), Integer.parseInt(sArr[3].substring(7))));
            }
            return list;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
