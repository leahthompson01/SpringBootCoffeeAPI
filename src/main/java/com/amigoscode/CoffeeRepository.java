package com.amigoscode;

import com.amigoscode.Model.Coffee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CoffeeRepository extends JpaRepository<Coffee, Integer > {
    Integer deleteByName(String name);
    List<Coffee> findCoffeeByBrand(String brand);

    @Query(value = "SELECT c.* FROM Coffee c WHERE c.price <=:price", nativeQuery = true)
    List<Coffee> findAllByPrice(@Param("price") Integer price);


}
