package ku.kinkao.controller;

import ku.kinkao.model.Restaurant;
import ku.kinkao.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/restaurant")
    public String getRestaurantPage(Model model) {
        model.addAttribute("restaurants", repository.findAll());
        return "restaurant";  // return restaurant.html
    }

    @GetMapping("/restaurant/add")
    public String getAddPage() {
        return "restaurant-add";  // return restaurant-add.html
    }

    @PostMapping("/restaurant/add")
    public String addRestaurant(@ModelAttribute Restaurant restaurant,
                                Model model) {
        repository.save(restaurant);
        return "redirect:/restaurant";
    }

}
