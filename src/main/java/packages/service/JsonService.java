package packages.service;
import packages.model.Car;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public interface JsonService {
    String parseUrl(URL url);

    void parseCarJson(String resultJson);

    String buildCarsJson(ArrayList<Car> list);

    void jsonFileWriter(ArrayList<Car> list, File file);

    void jsonFileReader(File file);
}
