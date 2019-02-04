package com.catalog.freezer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "foods")
public class Food extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7528000208062942254L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(max = 100)
    @Column(unique = true)
	private String name;
	
	@NotNull
	@Min(value = 0)
	private Long quantity;
	
	
	@ManyToOne()
	@NotNull
    @JoinColumn(name = "foodType_id", nullable = false)
	private FoodType foodType;

	public Food() {}

	public Food(Long id, @NotNull @Size(max = 100) String name, @NotNull @Min(0) Long quantity, @NotNull FoodType foodType) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.foodType = foodType;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", quantity=" + quantity + ", foodType=" + foodType + "]";
	}
	
	


}
