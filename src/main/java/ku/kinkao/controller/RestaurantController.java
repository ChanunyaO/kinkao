package ku.kinkao.controller;

import ku.kinkao.dto.RestaurantDto;
import ku.kinkao.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant")
    public String getRestaurantPage(Model model) {
        model.addAttribute("restaurants", restaurantService.getRestaurants());
        return "restaurant";  // return restaurant.html
    }

    @GetMapping("/restaurant/add")
    public String getAddPage() {
        return "restaurant-add";  // return restaurant-add.html
    }

    @PostMapping("/restaurant/add")
    public String addRestaurant(@ModelAttribute RestaurantDto restaurant,
                                Model model) {
        restaurantService.create(restaurant);
        return "redirect:/restaurant";
    }

}