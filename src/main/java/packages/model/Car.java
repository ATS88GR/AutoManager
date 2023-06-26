package packages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private int year;
    private String brand;
    private String model;
    private int cost;

    public Car() {
    }
}
