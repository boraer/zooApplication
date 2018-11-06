# **Zoom Application**

## **Aim Of Project**

The aim of this project is dealing basic CRUD operations of a zoo and getting reports about the zoo.

## **ASSUMPTIONS**

The assumptions are given below; 

1. An animal could be located in a room or not
2. An animal could be located in its favorite room or not
3. An animal could be stay in a room whether it is happy or not
4. Each room has a capacity and if a room is full then a new animal could not stay in that room
5. Animal favorite room changes should be implemented but not implemented

## **TECHNOLOGY STACK**

* Java 8
* Java NIO
* Spring Boot 2
* Cron
* JPA
* Hibernate
* Gradle
* AOP
* JSON


## **WHAT HAVE BEEN DONE IN PROJECT**

1. **Dockerized Application**

2. **Using Swagger, Generate Api Documentation It can be reach from  /v2/api-docs**

3. **The application may be tested via Swagger Panel. It can be reach from /swagger-ui.html#/**

4. A detailed report api for zoo;
    * Get All Animals in Zoo Order By Name/CreateDate Ascending and Descending order
    * Get All Animals in a Room Order By Name/CreateDate Ascending and Descending order 
    * List of Favorite Rooms of an Animal
    *  **Happy Animal Report**

5. **Using AOP a Generic Log Mechanism has been implemented**

5. CRUD Operations for Animal and Room

6. Animal Place/Remove/Transfer operations in||from a room

7. Assign/Unassign a room as favorite for an animal

8. All Rest Controller Consume and Produce JSON 

## **SHORTCOMINGS**

* Oauth or another token based security module could be added. It has been not added due to time and it is said that it is not an essential part of the project

* Microservice Structure could be applied. The application is monolithic on the other hand with enough time modules could be slipt into microservices such as animal-service room-service and Event Driven Architecture could be used for complex operations (add/remove/tranfer/favroite animal to/from room)

## **TEST** 

eclEmma Tool is used for calculating Test Coverage. **The Unit and Integration Test Coverage of Project is 85%**
If you need surfire reports you need to run mvn test or mvn integration-test commands

## **DEPLOYMENT**

If  mvn clean package command is run maven automatically build project and push to your docker repository at your local machine. It is essential to have a docker environment at your local. You may change the configuration and could push to another repo also. 


 
