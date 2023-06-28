package packages.service;

import packages.model.Car;
import java.util.ArrayList;

/**
 * interface for read/write from files and database
 */
public interface ReadWriteService {

    /**
     * Writes fields of Car objects list to file with name fileName
     * @param list Car objects list
     * @param fileName file name
     */
    void fileWriter(ArrayList<Car> list, String fileName);

    /**
     * Reads file with name fileName, returns Car objects list
     * @param fileName file name
     * @return list of Car objects
     */
    ArrayList<Car> fileReader(String fileName);
}
