package salakhov.lesson.factory;

import java.util.ArrayList;
import java.util.List;

public class CompositeCars implements Car {
    private List<Car> cars = new ArrayList<Car>();

    public String startEngine() {
        String result = "";
        for (Car car : cars) {
            result = result.concat(car.startEngine() + "\n");
        }
        return result;
    }

    public void add(Car car) {
        cars.add(car);
    }

    public void getInfo() {
        for (Car car : cars) {
            car.getInfo();
        }
    }
}
