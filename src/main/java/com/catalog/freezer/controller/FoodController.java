package com.catalog.freezer.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.freezer.model.Food;
import com.catalog.freezer.model.SearchModel;
import com.catalog.freezer.service.FoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

	@Autowired
    FoodService foodService;
		

    @GetMapping
	@PreAuthorize("isAuthenticated()")
    public List<Food> getAllFoods(Pageable pageable) {
        return foodService.findAll(pageable).getContent();
    }
    
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public List<Food> search(Pageable pageable,
    		@RequestParam(value = "name", required = false) String name, 
    		@RequestParam(value = "type", required = false) String foodTypeName, 
    		@RequestParam(value = "createdAtStart", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date createdAtStart,
    		@RequestParam(value = "createdAtEnd", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date createdAtEnd) {
    	
        return foodService.search(new SearchModel(name, foodTypeName, createdAtStart, createdAtEnd, pageable)).getContent();
    }
    
    @GetMapping("/{foodId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Food> getFood(@PathVariable Long foodId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(foodService.findById(foodId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(foodService.save(food));
    }

    @PutMapping("/{foodId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Food> updateFood(@PathVariable Long foodId, @Valid @RequestBody Food food) {
        return ResponseEntity.status(HttpStatus.OK)
        		.body(foodService.update(foodId, food));
    }

    
    @DeleteMapping("/{foodId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFood(@PathVariable Long foodId) {
    	foodService.delete(foodId);
        return ResponseEntity.status(HttpStatus.OK).body("Food has been deleted successfully.");
    }

}
