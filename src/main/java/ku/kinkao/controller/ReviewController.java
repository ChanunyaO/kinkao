
package ku.kinkao.controller;

import ku.kinkao.dto.ReviewRequest;
import ku.kinkao.service.JwtAccessTokenService;
import ku.kinkao.service.RestaurantService;
import ku.kinkao.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public String getReviewPage(@PathVariable UUID id,
                                Model model) {

        model.addAttribute("restaurant",
                restaurantService.getRestaurantById(id));
        model.addAttribute("reviews",
                reviewService.getRestaurantReview(id));

        return "review";
    }

    @GetMapping("/add/{restaurantId}")
    public String getReviewForm(@PathVariable UUID restaurantId,
                                Model model) {
        model.addAttribute("restaurantId", restaurantId);
        return "review-add";
    }

    @PostMapping("/add")
    public String createReview(@ModelAttribute ReviewRequest review,
                               Model model, Principal principal) {
        String username = principal.getName();
        review.setUsername(username);
        reviewService.createReview(review);
        return "redirect:/review/" + review.getRestaurantId();
    }
}

