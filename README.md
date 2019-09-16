# social
Social app for HStoneAge code challenge
Instruction:
1. In order to launch an application, you need to use the following:

a) mvn clean install -T 1C
b) mvn spring-boot:run

2. Documentation has been automatically created by Swagger. Link to the API documentation is available after the launch
on the website: http://localhost:8080/swagger-ui.html
You may use it to make requests in an easier way as well.

3. You can use the following link:
http://localhost:8080/h2
to check database state.

Others:
1. Integration tests are disabled by default. Some of them won't work as long as there are no initial rows.
It might've been achieved by creating them in data.sql file for H2 database.
2. FollowDto has two fields due to no logging mechanism.
3. You can't follow the same person twice.
4. You can't follow yourself.
5. Technologies: Java 8, Spring 5(Spring Core, Spring Boot, Spring MVC), Swagger2, QueryDSL, MapStruct, H2 in-memory database, 
Lombok, Maven, JPA, Hibernate ORM
