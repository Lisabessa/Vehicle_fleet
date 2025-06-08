package com.example.vehicle_fleet;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public ModelAndView index(Model model, @Param("keyword") String keyword) {
        ModelAndView mav = new ModelAndView("index");
        List<Car> listCars = carService.listAll(keyword);
        mav.addObject("listEntities", listCars);
        mav.addObject("keyword", keyword);
        mav.addObject("editLink", "/editCar/");
        mav.addObject("newLink", "/newCar");
        return mav;
    }

    @RequestMapping("/newCar")
    public ModelAndView ViewNewUserPage(Model model) {
        ModelAndView mav = new ModelAndView("change_entity");
        Car car = new Car();
        mav.addObject("entity", car);
        mav.addObject("option", "create");
        return mav;
    }

    @RequestMapping("/editCar/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Car car = carService.getCar(String.valueOf(id));
        mav.addObject("entity", car);
        mav.addObject("option", "edit");
        return mav;
    }

}
