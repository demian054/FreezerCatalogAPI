package com.catalog.freezer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalog.freezer.model.FoodType;

@Repository 
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
	
	public FoodType findByName(String name);

}
