package com.catalog.freezer.service.impl;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.catalog.freezer.exception.FoodException;
import com.catalog.freezer.model.Food;
import com.catalog.freezer.model.FoodType;
import com.catalog.freezer.model.SearchModel;
import com.catalog.freezer.repository.FoodRepository;
import com.catalog.freezer.repository.FoodTypeRepository;
import com.catalog.freezer.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Override
	public Food findById(Long foodId) {
		return foodRepository.findById(foodId)
				.orElseThrow(() -> new FoodException("Food by id", foodId.toString(), "Food not found"));
	}

	@Override
	public Food save(@Valid Food food) {
		FoodType foodType = foodTypeRepository.findByName(food.getFoodType().getName());
		if (foodType == null) {
			foodType = foodTypeRepository.save(new FoodType(food.getFoodType().getName()));
		}
		food.setFoodType(foodType);

		return foodRepository.save(food);
	}

	@Override
	public Food update(Long foodId, @Valid Food foodRequest) {
		return foodRepository.findById(foodId).map(food -> {

			food.setName(foodRequest.getName());
			food.setQuantity(foodRequest.getQuantity());
			food.setFoodType(foodRequest.getFoodType());

			return foodRepository.save(food);
		}).orElseThrow(() -> new FoodException("Food by id", foodId.toString(), "Food not found"));

	}

	@Override
	public void delete(Long foodId) {
		foodRepository.findById(foodId)
				.orElseThrow(() -> new FoodException("Food by id", foodId.toString(), "Food not found"));
		foodRepository.deleteById(foodId);
	}

	@Override
	public Page<Food> search(SearchModel searchModel) {
		return foodRepository.search(
				searchModel.getName(), 
				searchModel.getFoodTypeName(), 
				searchModel.getCreatedAtStart(),
				searchModel.getCreatedAtEnd(),
				searchModel.getPageable()
				);
	}

	@Override
	public Page<Food> findAll(Pageable pageable) {
		return foodRepository.findAll(pageable);
	}

}
