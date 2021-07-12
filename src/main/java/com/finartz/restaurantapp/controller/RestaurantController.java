package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant){
        return new ResponseEntity(restaurantService.create(restaurant), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> get(@PathVariable Long id){
        return new ResponseEntity(restaurantService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll(){
        return new ResponseEntity(restaurantService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant){
        return new ResponseEntity(restaurantService.update(restaurant), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurant> deleteById(@PathVariable Long id){
        restaurantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
