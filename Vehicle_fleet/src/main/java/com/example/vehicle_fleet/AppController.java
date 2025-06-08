package com.example.vehicle_fleet;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AppController {

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public ModelAndView index(
            Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort) {
        ModelAndView mav = new ModelAndView("index");
        List<Car> listCars = carService.listAll(keyword);

        if (sort != null && !sort.isEmpty()) {
            boolean ascending = "asc".equalsIgnoreCase(sort);
            listCars.sort((c1, c2) -> {
                if (c1.getManufactureYear() == null) return 1;
                if (c2.getManufactureYear() == null) return -1;
                return ascending
                        ? c1.getManufactureYear().compareTo(c2.getManufactureYear())
                        : c2.getManufactureYear().compareTo(c1.getManufactureYear());
            });
        }

        Map<LocalDate, Long> countsByDate = carService.countCarsByRegistrationDate();
        List<LocalDate> sortedDates = countsByDate.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
        List<String> dateLabels = sortedDates.stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList());
        List<Long> counts = sortedDates.stream()
                .map(countsByDate::get)
                .collect(Collectors.toList());

        mav.addObject("listEntities", listCars);
        mav.addObject("keyword", keyword);
        mav.addObject("sort", sort);
        mav.addObject("dates", dateLabels);
        mav.addObject("counts", counts);
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
