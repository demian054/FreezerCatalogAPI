package com.catalog.freezer.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.catalog.freezer.model.Food;
import com.catalog.freezer.model.SearchModel;

public interface FoodService {

	Page<Food> findAll(Pageable pageable);

	Food findById(Long foodId);

	Food save(@Valid Food food);
	
	Food update(Long foodId, @Valid Food food);
	
	void delete(Long foodId);

	Page<Food> search(SearchModel searchModel);	

}
