package ku.kinkao.service;

import ku.kinkao.dto.RestaurantRequest;
import ku.kinkao.dto.RestaurantResponse;
import ku.kinkao.model.Restaurant;
import ku.kinkao.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantResponse getRestaurantById(UUID restaurantId) {
        Restaurant restaurant = repository.findById(restaurantId).get();
        return modelMapper.map(restaurant, RestaurantResponse.class);
    }

    //   ----> we are mapping DAO → DTO
    public List<RestaurantResponse> getRestaurants() {
        List<Restaurant> restaurants = repository.findAll();

        List<RestaurantResponse> dtos = restaurants
                .stream()
                .map(restaurant -> modelMapper.map(restaurant,
                        RestaurantResponse.class))
                .collect(Collectors.toList());

        return dtos;
    }


    //   ----> we are mapping DTO → DAO
    public void create(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = modelMapper.map(restaurantRequest,
                Restaurant.class);
        restaurant.setCreatedAt(Instant.now());
        repository.save(restaurant);
    }
}
