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
@RequestMapping("/api")
public class CarApi {
    private CarService service;

    public CarApi(final CarService service) {
        this.service = service;
    }

    @GetMapping(value = "/all",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Car>> getAll(){
        return new ResponseEntity<>(service.getCarList(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Car> getCar(@Validated @PathVariable Integer id){
        Optional<Car> result = service.getCarList().stream().filter(x -> x.getId() == id).findFirst();
        if(result.isPresent()) {
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Car>> getByColor(@RequestParam String color){
        List<Car> result = new ArrayList<>();
        service.getCarList().stream()
                .filter(x -> x.getColor().getDisplayValue().equals(color))
                .forEach(x -> result.add(x));

        if(result.size() > 0){
            return new ResponseEntity(result, HttpStatus.OK);
        }
            else
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity addCar(@Validated @RequestBody Car car){
        boolean add = service.getCarList().add(car);
        if (add){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
