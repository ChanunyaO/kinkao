package ku.kinkao.controller;

import ku.kinkao.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/restaurant")
    public String getRestaurantPage(Model model) {
        model.addAttribute("restaurants", repository.findAll());
        return "restaurant";  // return restaurant.html
    }
}
