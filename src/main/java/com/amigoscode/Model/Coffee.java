package com.amigoscode.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Coffee {

    @Id
    @SequenceGenerator(
            name = "coffee_id+sequence",
            sequenceName = "coffee_id_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coffee_id_sequence"
    )
    private Integer Id;
    private String name;
    private String brand;
    private String roast;
    private Integer price;

    public Coffee(String coffeeName, String coffeeBrand, String roastType, Integer price) {
        this.name = coffeeName;
        this.brand = coffeeBrand;
        this.roast = roastType;
        this.price = price;
    }

    public Coffee() {
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getRoast() {
        return roast;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(name, coffee.name) && Objects.equals(brand, coffee.brand) && Objects.equals(roast, coffee.roast) && Objects.equals(price, coffee.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, roast, price);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", roast='" + roast + '\'' +
                ", price=" + price +
                '}';
    }
}
