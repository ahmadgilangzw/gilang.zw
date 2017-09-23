Spring Boot "FriendManagement" Service

In here i used Java 1.7, Maven, Spring Boot and Database MySql.

Description the code

1. People Controller



- We have method registerPeople this method used for register the new people for join in our application. You are cannot using email in this API without registered before.
- In this method we have Parameter Object People, you can request this api by JSON value like :
{
	"name" : "example",
	"emailAddress" : "example@example.com"
}
- If parameter is null, email already registered or the code have exception, the method will return the error message.
- If Success, the data will saved to the database and this api will return value of people created.










2. Friend Controller

This controller used for manage all about friend, like add friend, find friend and find friend between two email.

In this controller we have 3 API URL Method.

* Method registerFriend



- we have method registerFriend, this method used for add a new friend.
- In this method we must request using JSON format like 
{
  "friends": [
    "andy@example.com",
    "common@example.com"
  ]
}
- If the parameter request is null, email not registered yet, email already to be friend or the code have Error Exception, this method will return error message.
- If success, data will be saved to database and return success true.





* Method findAllFriend



- This method will used for find all friend by parameter JSON email.
- We must request JSON parameter like :
{ 
  "email":  "example@example.com"
}
- If the parameter null, email does not contain friend or code have error exception this method will return error message.
- If success the method will return list of friend for your email.


















* Method findFriendBetweenTwoEmail



- This method is used for find friend who are friends with both emails.
- We must request using JSON format like :
{
  "friends": [
    "example@example.com",
    "ex@example.com"
  ]
}

- If the parameter null, both of email not contain friends, or the code have exception, this method will return the error message.
- If success, this method will return list of friend by JSON format.















* Subscribe Controller

This controller used for manage all about subscribe like add subscribe, block people or unsubscribe.

In this controller we have 2 API URL Method.

- Method addSubscribe



- This method used for add a new subscribe.
- You can request this metho by JSON format value like :
{   
   "requestor": "lisa@example.com",   
   "target": "john@example.com" 
         }
- If the parameter is null, email not registered yet, email already subscribe, or the code have error exception, this method will return the error message.
- If success, the data will be save to the database and this method will return success true.









- Method blockPeople



- This method used for block people or unsubscribe.
- If the email requestor and email target not already to be friend, email target will be bloked.
- If the email requestor and email target already to be friend, email target will be unsubscribe.
- If parameter null, email not already registerd, email already blocked, email already unsubscribed, this method will return the error message.
- If success, this method will save the data and return success true.

















* Send Received Email Controller

This controller used for send message.

This controller just have one method.

- Method sendEmail



- This method used for send message to friend, subscribe or mentioned.
- You can request this method with JSON format value like : 
{   
   "sender":  "john@example.com",   
   "text": "Hello World! kate@example.com" 
          }
- If parameter is null, sender not already registered, or code have error exception, this method will return the error message.
- If success, the data will be save and will return success true and list of send message.











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
	- REQUEST 		: { 
						"name" : "Jhon", 
						"emailAddress" : "john@example.com" 
					  }
	- RESPONSE		: { 
						"id": 10, 
						"name": "Jhon", 
						"emailAddress": 
						"john@example.com", 
						"created": "19 09 2017 17:25:28", "updated": "19 09 2017 17:25:28" 
					  }

2. API for "Create Friend Connection"
	- URL			: http://localhost:8080/service/add-friend
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { 
						"friends": [ 
							"john@example.com", 
							"common@example.com" 
							] 
					  }
	- RESPONSE		: { 
						"Success": true 
					  }
	
3. API for "Retrieve The Friend List"
	- URL 			: http://localhost:8080/service/find-all-friend
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { 
						"email": "andy@example.com" 
					  }
	- RESPONSE		: {
						"friends": [
							"john@example.com"
						],
						"count": 1,
						"Success": true
					  }
	
4. API for "Retrieve The Commons Friend list Between Two Email Address"
	- URL			: http://localhost:8080/service/find-f-between-two-email
	- METHOD		: POST
	- CONTENT-TYPE 	: application/json
	- REQUEST		: { 
						"friends": [ 
							"andy@example.com", 
							"john@example.com" 
					  }
	- RESPONSE		: {
						"friends": [
							"common@example.com"
						],
						"count": 1,
						"Success": true
					  }
	
5. API for "Subscribe People"
	- URL			: http://localhost:8080/service/add-subscribe
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: { 
						"requestor": "lisa@example.com",   
						"target": "john@example.com" 
					  }
	- RESPONSE		: { 
						"Success": true 
					  }
	
6. API for "Block People"
	- URL 			: http://localhost:8080/service/block-people
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: {   
						"requestor": "andy@example.com",   
						"target": "john@example.com" 
					  } 
	- RESPONSE		: { 
						"Success": true 
					  }
	
7. API for "Retrieve All Email Address That Can Receive Updateds"
	- URL 			: http://localhost:8080/service/send-email
	- METHOD		: POST
	- CONTENT-TYPE	: application/json
	- REQUEST		: {   
						"sender":  "john@example.com",   
						"text": "Hello World! kate@example.com" 
					  } 
	- RESPONSE		: {
						"recipients": [
							"common@example.com",
							"lisa@example.com",
							"kate@example.com"
						],
						"Success": true
					  }
