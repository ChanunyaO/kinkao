package ku.kinkao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ku.kinkao.model.Restaurant;

@Repository
public interface RestaurantRepository extends
        JpaRepository<Restaurant,Integer> {
}
