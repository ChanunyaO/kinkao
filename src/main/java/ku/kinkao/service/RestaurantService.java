package ku.kinkao.service;

import ku.kinkao.dto.RestaurantDto;
import ku.kinkao.model.Restaurant;
import ku.kinkao.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    //   ----> we are mapping DAO → DTO
    public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = repository.findAll();

        List<RestaurantDto> dtos = restaurants
                .stream()
                .map(restaurant -> modelMapper.map(restaurant,
                        RestaurantDto.class))
                .collect(Collectors.toList());

        return dtos;
    }


    //   ----> we are mapping DTO → DAO
    public void create(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto,
                Restaurant.class);
        restaurant.setCreatedAt(Instant.now());
        repository.save(restaurant);
    }
}
