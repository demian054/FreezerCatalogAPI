# FreezerCatalogAPI

```
### Tech Task
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

```


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc







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



