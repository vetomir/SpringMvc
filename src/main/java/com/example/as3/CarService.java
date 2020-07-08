package com.example.as3;

import com.example.as3.model.Car;
import com.example.as3.model.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class CarService {
    private List<Car> carList;

    public CarService() {
        carList = new ArrayList<>();

        carList.add(new Car( "Volvo", "S60", Color.RED));
        carList.add(new Car( "BMW", "E90", Color.BLUE));
        carList.add(new Car( "Audi", "A6", Color.BLACK));
        carList.add(new Car( "Mercedes-Benz", "A-Klasse", Color.GREEN));
        carList.add(new Car( "Opel", "Omega", Color.YELLOW));
        carList.add(new Car( "Fiat", "Punto", Color.RED));

    }

    public List<Car> getCarList() {
        return carList;
    }
}
