package com.example.vehicle_fleet;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
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
        return mav;
    }

}
