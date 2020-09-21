# DroneDeliveryService
This is simple microservices based application, which is delivering goods using drones from list of stores.
In this application user can choose any customer from list displayed and check delivery time to send goods. This application uses shortest routes to deliver goods based on the distance from customer to store and store to drone depot. 

There is an assumption for drone velocity which is 60km/h while calculating time to deliver goods.

This application have 4 microservices and one eureka server application to manage microservices lifecycle.

1.	eureka-server
  This application is to launch eureka server to register microservices. 
Below command can be used to start eureka server:
java -jar eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar

2.	store-service
  This application is a store microservice which is responsible for manage store information and return store related data from db.
Below command can be used to start store microservice which also register as eureka client:
java -jar store-service\target\store-service-0.0.1-SNAPSHOT.jar

3.	customer-service
  This application is a customer microservice which is responsible for manage customer information and return customer related data from db.
Below command can be used to start customer microservice which also register as eureka client:
java -jar customer-service\target\customer-service-0.0.1-SNAPSHOT.jar

4.	drone-service
  This application is a drone microservice which is responsible for manage drone information and return drone related data from db.
Below command can be used to start drone microservice which also register as eureka client:
java -jar drone-service\target\drone-service-0.0.1-SNAPSHOT.jar

5.	delivery-web-server
  This application is to interact with user interface and allow user to select any customer from list displayed. After selecting a customer, its displayed all information about  distance and travel time.

This application is consumer of all above 3 microservices.

Below command can be used to start delivery web server microservice which also register as eureka client:
java -jar delivery-web-server\target\delivery-web-server -0.0.1-SNAPSHOT.jar


This application using below technologies:
1.	Spring Boot
2.	Eureka Server
3.	Spring MVC/Thyme Leaf
4.	Java 8
5.	hsqldb (in memory DB)

