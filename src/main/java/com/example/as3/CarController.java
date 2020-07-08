package com.example.as3;

import com.example.as3.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {


    private String addEdit = "add";
    private Integer findId = 1;
    private CarService service;

    public CarController( final CarService service) {
        this.service = service;
    }

    @GetMapping
    String showCars(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("cars", service.getCarList());
        model.addAttribute("addEdit", addEdit);
        model.addAttribute("findId", findId);
        return "car";
    }

/*    @PostMapping
    String addCar(
            @ModelAttribute("car") @Valid Car current,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()){
            return "car";
        }
        service.getCarList().add(current);
        model.addAttribute("car", new Car());
        model.addAttribute("cars", service.getCarList());
        model.addAttribute("message", "Dodano!");
        return "car";
    }*/

    @PostMapping(params = "add")
    String add() {
        addEdit = "add";
        return "redirect:/cars";
    }
    @PostMapping(params = "edit")
    String edit() {
        addEdit = "edit";
        return "redirect:/cars";
    }
    @PostMapping(params = "delete")
    String delete() {
        addEdit = "delete";
        return "redirect:/cars";
    }

   @PostMapping
    String editCar(
            @ModelAttribute("car") @Valid Car newCar,
            BindingResult bindingResult,
            Model model
    ) {

        //if some of the field is empty
        if(bindingResult.hasErrors()){
            model.addAttribute("car", new Car());
            model.addAttribute("cars", service.getCarList());
            model.addAttribute("addEdit", addEdit);
            return "car";
        }

        ////

       Optional<Car> oldCar = service.getCarList().stream().filter(x -> x.getId() == newCar.getId()).findFirst();
       if (oldCar.isPresent()) {
           newCar.setId(oldCar.get().getId());
           service.getCarList().remove(oldCar.get());

           model.addAttribute("message", "Edytowano!");
       }
       else
           model.addAttribute("message", "Dodano!");

       service.getCarList().add(newCar);
       model.addAttribute("car", new Car());
       model.addAttribute("cars", service.getCarList());
       model.addAttribute("addEdit", addEdit);
        return "car";
    }

    @PostMapping(params = "deleteCar")
    String deleteCar(
            @ModelAttribute("car") @Valid Car mockCar,
            BindingResult bindingResult,
            Model model
    ) {

        //if some of the field is empty
        if (bindingResult.hasErrors()) {
            model.addAttribute("car", new Car());
            model.addAttribute("cars", service.getCarList());
            model.addAttribute("addEdit", addEdit);
            return "car";
        }

        ////

        Optional<Car> oldCar = service.getCarList().stream().filter(x -> x.getId() == mockCar.getId()).findFirst();
        if (oldCar.isPresent()) {
            service.getCarList().remove(oldCar.get());

            model.addAttribute("message", "Usunięto!");
        } else
            model.addAttribute("message", "Nie znaleziono!");

        model.addAttribute("car", new Car());
        model.addAttribute("cars", service.getCarList());
        model.addAttribute("addEdit", addEdit);
        return "car";
    }
}
