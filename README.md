# FreezerCatalogAPI

######################################################

Freezer Catalog API
Task
Create an app that publishes a REST API with methods as specified below. Write the API code and test cases, and document the code. You may use common frameworks if you wish. You will be asked to explain your choices.

Specification
The API is a service to create a catalog of the food in my freezer. The endpoints are

/food
	Method to add some food to the freezer, giving name, type & quantity; returns ID
	Method to provide an ID & get the detail of the food
	Method update an item
/food/search
	Method search for food by name, type or date added


All methods should be authenticated with an API key. Data can be stored in memory or a temporary database such as H2 so we can run it locally and test using Postman.

What you are expected to deliver
Documented source code
A working solution
Test cases
A brief summary of your implementation decisions and thoughts on possible enhancements & challenges


#######################################################

	Note: Some services have "Bearer Token" paramether requered, for use en Postman see the next 
	link https://learning.getpostman.com/docs/postman/sending_api_requests/authorization/

------------------- Rest Services -------------------

%%%%%%%%%%%%%%%    foods  %%%%%%%%%%%%%

...........................................................
->	GetAll : Return a list of all the objects Food in the table.

		Method 	: 				Get
		Authenticated : 		True (Bearer Token)
		Roll : 					User, Admin
		URL : 					/api/foods
		Accept Paginable		True
		
	Response json Example
	
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
		
	Note: Return a empty list if dont find any element.
		
...........................................................		
-> 	GetFoodById	: Return a Food Object selected by id
	
		Method 	: 				Get
		Authenticated : 		True (Bearer Token)
		Roll : 					User, Admin
		URL : 					/api/foods/{foodId}
		Accept Paginable		false
		
		Parameter
		foodId :				Id of the Food to search.

	Response json Example

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
		
	Response json Error (if dont find the Food)
	
		{
			"resource": "5",
			"value": "Food by id",
			"message": "Food not found"
		}
		
...........................................................
-> 	CreateFood : Create a new Food in the db
	
		Method 	: 				Post
		Authenticated : 		True (Bearer Token)
		Roll : 					Admin
		URL : 					/api/foods
		Accept Paginable		false
		
	Request Example
	
		{
			"name": "bread2",
			"quantity": 2,
			"foodType": {
				"name": "fruit2"
			}
		}
		
	Note: The name of the food need to be unique, and the FoodType is created in case than dont exist
	
	
	Response json Example
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
	
	
		
%%%%%%%%%%%%%%%    Auth    %%%%%%%%%%%%%


%%%%%%%%%%%%%%%    Person  %%%%%%%%%%%%%	
		
		
		
		
		
		
		
		
/api/auth


/api/person



