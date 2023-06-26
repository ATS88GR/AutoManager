package packages.service;

import packages.model.Car;
import java.util.ArrayList;

/**
 * interface for read/write from files and database
 */
public interface ReadWriteService {

    /**
     * to write
     * @param list to file with name
     * @param fileName
     */
    void fileWriter(ArrayList<Car> list, String fileName);

    /**
     * to read file with name
     * @param fileName
     * @return list of Car objects
     */
    ArrayList<Car> fileReader(String fileName);
}
