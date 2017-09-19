# gilang.zw
Spring Boot "FriendManagement" Service

In here i used Java 1.7, Maven, Spring Boot and Database MySql.
How to Run
1. Clone this repository
2. Make sure you are using JDK 1.7
3. You can build the project and run the tests by running mvn install package
4. Once successfully built, you can run the service by : mvn spring-boot:run
5. For the database you just create the database without the table, because the table with automaticaly generated when application run.

About The Service
This service have 7 REST API.

#NOTE : every used email in this service, should be already added to database using API "add people".

1. API for "add people".
	- URL    		: http://localhost:8080/service/add-people
	- METHOD 		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST 		: { "name" : "Jhon", "emailAddress" : "john@example.com" }
	- RESPONSE		: 

2. API for "Create Friend Connection"
	- URL			: http://localhost:8080/service/add-friend
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { "friends": [ "john@example.com", "common@example.com" ] }
	- RESPONSE		: 
	
3. API for "Retrieve The Friend List"
	- URL 			: http://localhost:8080/service/find-all-friend
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { "email": "andy@example.com" }
	- RESPONSE		: 
4. API for "Retrieve The Commons Friend list Between Two Email Address"
	- URL			: http://localhost:8080/service/find-f-between-two-email
	- METHOD		: POST
	- CONTENT-TYPE 	: application/json
	- REQUEST		: { "friends": [ "andy@example.com", "john@example.com" }
	- RESPONSE		: 
	
5. API for "Subscribe People"
	- URL			: http://localhost:8080/service/add-subscribe
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { "requestor": "lisa@example.com",   "target": "john@example.com" }
	- RESPONSE		:
	
6. API for "Block People"
	- URL 			: http://localhost:8080/service/block-people
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: {   "requestor": "andy@example.com",   "target": "john@example.com" } 
	- RESPONSE		:
	
7. API for "Retrieve All Email Address That Can Receive Updateds"
	- URL 			: http://localhost:8080/service/send-email
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: {   "sender":  "john@example.com",   "text": "Hello World! kate@example.com" } 
	- RESPONSE		: 