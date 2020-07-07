package com.example.as3;

import com.example.as3.model.Car;
import com.example.as3.model.Color;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {
    private List<Car> carList;

    public CarApi() {
        carList = new ArrayList<>();

        carList.add(new Car(1, "Volvo", "S60", Color.RED));
        carList.add(new Car(2, "BMW", "E90", Color.BLUE));
        carList.add(new Car(3, "Audi", "A6", Color.BLACK));
        carList.add(new Car(4, "Mercedes-Benz", "A-Klasse", Color.GREEN));
        carList.add(new Car(5, "Opel", "Omega", Color.YELLOW));
        carList.add(new Car(6, "Fiat", "Punto", Color.RED));
    }

    @GetMapping(value = "/all",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Car>> getAll(){
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Car> getCar(@Validated @PathVariable Integer id){
        Optional<Car> result = carList.stream().filter(x -> x.getId() == id).findFirst();
        if(result.isPresent()) {
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity addCar(@Validated @RequestBody Car car){
        boolean add = carList.add(car);
        if (add){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity putCar(@PathVariable Integer id, @Validated @RequestBody Car car) {

        Optional<Car> oldVideo = carList.stream().filter(x -> x.getId() == id).findFirst();
        if (oldVideo.isPresent()) {
            carList.remove(oldVideo.get());
            carList.add(car);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Car> removeCar(@PathVariable Integer id){
        Optional<Car> result = carList.stream().filter(x -> x.getId() == id).findFirst();
        if(result.isPresent()) {
            carList.remove(result.get());
            return new ResponseEntity( HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
