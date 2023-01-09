package com.amigoscode;

import com.amigoscode.Model.Coffee;
import com.amigoscode.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController

public class Main {

    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;

    public Main(CoffeeRepository coffeeRepository, UserRepository userRepository) {
        this.coffeeRepository = coffeeRepository;
        this.userRepository = userRepository;
    }


    public static void main(String[] args){
        System.out.println("Hello World!");
            SpringApplication.run(Main.class, args);
    }
    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("api/v1/coffees")
    @GetMapping
    public List<Coffee> getCoffees(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/brand")
    public List<Coffee> getCoffeeByBrand(@RequestParam String brand){
        return coffeeRepository.findCoffeeByBrand(brand.toLowerCase());
    }
    @GetMapping("/price")
    public List<Coffee> getCoffeeLessOrEqual(@RequestParam("priceLimit") Integer price){
        return coffeeRepository.findAllByPrice(price);
    }
    record NewCoffeeRequest(
        String name,
         String brand,
         Integer price,
         String roast
    ){}
    @PostMapping
    public void addCoffee(@RequestBody NewCoffeeRequest request){
        Coffee newCoffee = new Coffee();
        newCoffee.setName(request.name().toLowerCase());
        newCoffee.setBrand(request.brand().toLowerCase());
        newCoffee.setPrice(request.price());
        newCoffee.setRoast(request.roast().toLowerCase());
        System.out.println(newCoffee);
        coffeeRepository.save(newCoffee);
        System.out.println("New item added to db");
    }
    @DeleteMapping
    public void deleteCoffeeById(@RequestParam("coffeeId") Integer id){
        coffeeRepository.deleteById(id);
    }
   @DeleteMapping("/name")
    public void deleteCoffeeByName(@RequestParam("name") String name){
        System.out.println(name);
        coffeeRepository.deleteByName(name.toLowerCase());
    }
    @PutMapping("{coffeeId}")
    public Coffee updateCoffee(@RequestParam("coffeeId") Integer id, @RequestBody Coffee newCoffee ) throws Exception{
        return coffeeRepository.findById(id)
                .map(item -> {
                    item.setName(newCoffee.getName().toLowerCase());
                    item.setBrand(newCoffee.getBrand().toLowerCase());
                    item.setPrice(newCoffee.getPrice());
                    item.setRoast(newCoffee.getRoast().toLowerCase());
                    return coffeeRepository.save(item);
                }).orElseThrow(() -> new Exception("Id not found -" + id));

    }


}
