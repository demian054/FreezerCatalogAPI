package com.catalog.freezer.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.catalog.freezer.model.Food;

@Repository 
public interface FoodRepository extends JpaRepository<Food, Long> {

	public Page<Food> findByNameOrFoodType_NameOrCreatedAtBetweenAllIgnoreCase(
			String name, 
			String foodTypeName, 
			Date createdAt,
			Date createdAtEnd, 
			Pageable pageable);
	
	@Query("Select f FROM Food f WHERE "
			+ "( f.createdAt between coalesce(:createdAtStart, now()) and coalesce(:createdAtEnd, now()) ) "
			+ "or LOWER(f.name) = LOWER(:name) "
			+ "or LOWER(f.foodType.name) = LOWER(:foodTypeName) ")
			
	
	public Page<Food> search(
			@Param("name") String name,
			@Param("foodTypeName") String FoodTypeName,
			@Param("createdAtStart") Date createdAtStart,
			@Param("createdAtEnd") Date createdAtEnd,
			Pageable pageable);

}
