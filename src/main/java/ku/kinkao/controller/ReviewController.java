package ku.kinkao.controller;

import ku.kinkao.service.JwtAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {

    @Autowired
    private JwtAccessTokenService jwtService;

    @GetMapping("/review")
    public String getReviewPage(Model model) {

        String jwtResponse = jwtService.requestAccessToken();

        System.out.println("Token: " + jwtResponse);

        return "review";
    }
}
