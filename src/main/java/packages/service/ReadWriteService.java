package packages.service;

import packages.model.Car;
import java.util.ArrayList;

public interface ReadWriteService {

    void fileWriter(ArrayList<Car> list, String fileName);
    ArrayList<Car> fileReader(String fileName);
}
