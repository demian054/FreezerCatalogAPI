# FreezerCatalogAPI  https://travis-ci.org/demian054/FreezerCatalogAPI.svg?branch=master

-Document version 0.1
 
### Tech Task
```
Create an app that publishes a REST API with methods as specified below. Write the API code and test cases, 
and document the code. You may use common frameworks if you wish. You will be asked to explain your choices.

Specification
The API is a service to create a catalog of the food in my freezer. The endpoints are

/food
	Method to add some food to the freezer, giving name, type & quantity; returns ID
	Method to provide an ID & get the detail of the food
	Method update an item
/food/search
	Method search for food by name, type or date added


All methods should be authenticated with an API key. Data can be stored in memory or a temporary database such 
as H2 so we can run it locally and test using Postman.

What you are expected to deliver
Documented source code
A working solution
Test cases
A brief summary of your implementation decisions and thoughts on possible enhancements & challenges

```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and 
testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
	+ +1.8.0_172 java.			-> https://www.java.com/en/download/help/download_options.xml
	+ Apache Maven +3.5.4			-> https://maven.apache.org/install.html
	+ Postman    (For Test)			-> https://www.getpostman.com/
  
	Note: Some services have "Bearer Token" paramether requered, for use en Postman see the next 
	link https://learning.getpostman.com/docs/postman/sending_api_requests/authorization/

```

### Installing

  + To compile you can use maven in the mvn clean install

```
 mvn clean install
```


## Running the tests

  + To Test you can use maven in the mvn test

```
 mvn test
```

## End to end tests

 ### AuthControllerTest
 
	+ testAuthPersonSuccess				-> Test if the validation of the credentials its correct
	+ testAuthPersonBadCredentials			-> Test if the validation without credential
	+ testAuthPersonInvalidCredentials		-> Test if the validation with invalid credential
	
 ### FoodControllerTest
 
	+ testGetAllFoodsSuccess			-> List and count all elements of foods
	+ testAddFoodSuccess				-> Add a new Food
	+ testAddFoodAccess				-> Check the Admin role access to add a new Food
	+ testGetFoodByIdSuccess			-> Get one Food element by id 
	+ testUpdateFoodSuccess				-> Upadte a Food element and check updated correct
	+ testDeleteFoodByIdSuccess			-> Delete a Food element and check deleted correct
	+ testSearchByNameFoodSuccess			-> Get one Food element by name
	+ testSearchByTypeFoodSuccess			-> Get one Food element by Food Type
	+ testSearchByDateAddedSuccess			-> Get one Food element by Date of create
	
 ### PersonControllerTest
 
	+ testPersonRegisterSuccess			-> Check if an User can register success
	+ testGetAllPersonsSuccess			-> List and count all elements of Person
	+ testGetPersonByIdSuccess			-> Get one Person element by id
	+ testGetPersonByIdFail				-> Fail Get one Person element by wrong id

	

## Call GetAll Services
	
Return a list of all the objects Food in the table.
+ Method 	: 				Get
+ Authenticated : 		True (Bearer Token)
+ Roll : 					User, Admin
+ URL : 					/api/foods
+ Accept Paginable		True

### Request

 - Parameter
	+ foodId :				Id of the Food to search.

Example:
```
	http://localhost:8080/api/foods/1
```
	

### Response 

Example
```
	[
		{
			"createdAt": "2019-02-04T01:08:32.971+0000",
			"updatedAt": "2019-02-04T01:08:32.971+0000",
			"id": 1,
			"name": "bread",
			"quantity": 2,
			"foodType": {
				"createdAt": "2019-02-04T01:08:32.969+0000",
				"updatedAt": "2019-02-04T01:08:32.969+0000",
				"id": 10001,
				"name": "fruit"
			}
		}
	]
```
Note: Return a empty list if dont find any element.
	
	
## Call GetFoodById Services
 
Return a Food Object selected by id
+ Method 	: 			Get
+ Authenticated : 		True (Bearer Token)
+ Roll : 				User, Admin
+ URL : 				/api/foods/{foodId}
 + Accept Paginable		false
	
### Request
	
 - Parameters
	+ foodId :				Id of the Food to search.
	
	Example:
	```
		http://localhost:8080/api/foods/1
	```
		

### Response 
		
Example Success
```
	{
		"createdAt": "2019-02-04T03:11:05.653+0000",
		"updatedAt": "2019-02-04T03:11:05.653+0000",
		"id": 1,
		"name": "bread",
		"quantity": 2,
		"foodType": {
			"createdAt": "2019-02-04T03:11:05.653+0000",
			"updatedAt": "2019-02-04T03:11:05.653+0000",
			"id": 10001,
			"name": "fruit"
		}
	}
```
	
Response Error (if dont find the Food)
```
	{
		"resource": "5",
		"value": "Food by id",
		"message": "Food not found"
	}
```
	
## Call CreateFood Services
 
+ Create a new Food in the db
+ Method 	: 			Post
+ Authenticated : 		True (Bearer Token)
+ Roll : 				Admin
+ URL : 				/api/foods
+ Accept Paginable		false
		
Request Example
```
	{
		"name": "bread2",
		"quantity": 2,
		"foodType": {
			"name": "fruit2"
		}
	}
```
Note: The name of the food need to be unique, and the FoodType is created in case than dont exist
	
	
### Response 

Example
```
{
	"createdAt": "2019-02-04T03:23:14.360+0000",
	"updatedAt": "2019-02-04T03:23:14.360+0000",
	"id": 2,
	"name": "bread2",
	"quantity": 2,
	"foodType": {
		"createdAt": "2019-02-04T03:23:14.324+0000",
		"updatedAt": "2019-02-04T03:23:14.324+0000",
		"id": 10005,
		"name": "fruit2"
	}
}
```



## Call Search Services
... doc in contruction ...

## Call updateFood Services
... doc in contruction ...

## Call deleteFood Services
... doc in contruction ...



## Call signin Services
... doc in contruction ...



## Call register Services
... doc in contruction ...

## Call getAllPersons Services
... doc in contruction ...

## Call getPersonById Services
... doc in contruction ...


	
	
## Authors

* **Demian Bolivar** - *Initial work* - [FreezerCatalogAPI](https://github.com/demian054/FreezerCatalogAPI)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc