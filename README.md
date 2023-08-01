# Intro
Spring is a Java framework that makes it easier for programmers to create Java applications by 
implementing one of the design-patterns: dependency-injection.
Spring boot is the one of Spring frameworks.

As a developer, I always need a simple basic code initialization for my project. to make it easier,
I made this jwt-auth-basic-code project. hope it is useful for those of you.

# Installation Instructions
First of all, you can clone the project to your and run it by your favorite IDE (I use Intellij IDEA).
Note that you have to make some adjustments.

note : in this project, I use SQL SERVER as the database, Spring boot 3.1.1, and java 17.

# Adjustments
If you just need to test/run the whole project, please execute initiate_query.sql (in Query foler) first on your sql server app.
Then you have to adjust application.properties.

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L4C1-L9C1

replace it to your datasource information.

otherwise, you can replace server port and servlet context path.

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L1-L2

and you able to set the jwt expiration time in app.jwtExpirationMs, and the jwt secret in app.jwtSecret as below

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L23C1-L25C29

then you can build and run the project.

# Test the application
In this application, you are able to access swagger-ui.html for the documentation. Here's the format

   `http://localhost:server.port/context-path/swagger-ui.html`

There are services including login and register without authentication, and get all product with jwt authentication which has super admin or admin role (for example)
