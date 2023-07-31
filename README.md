# Intro
Spring is a Java framework that makes it easier for programmers to create Java applications by 
implementing one of the design-patterns: dependency-injection.
Spring boot is the one of Spring frameworks.

As a developer, I always need a simple basic code initialization for my project. to make it easier,
I made this simple-basic-code project. hope it is useful for those of you.

# Installation Instructions
First of all, you can clone the project to your and run it by your favorite IDE (I use Intellij IDEA).
Note that you have to make some adjustments.

note : in this project, I use SQL SERVER as the database, Spring boot 3.1.1, and java 17.

# Adjustments
If you just need to test/run the whole project, please execute initiate_query.sql (in Query foler) first with your sql server.
Then you have to adjust application.properties.

https://github.com/alkadarr/springboot-simple-basic-code/blob/2cb6e93e4e91c614681fc827bc8abddc2e0916c7/src/main/resources/application.properties#L4C1-L8C79

replace it to your datasource information.

otherwise, you can replace server port and servlet context path.

https://github.com/alkadarr/springboot-simple-basic-code/blob/0e538abd3125dc7d2a731bc7674fea70d340f76e/src/main/resources/application.properties#L1C1-L1C1

then you can build and run the project.

# Test the application
After you run the project, you are able to test with the next curl

   ` curl --location 'http://localhost:8080/app-name/product/all' `

You'll get a response as in below.
  
    `{
    "success": true,
    "message": "",
    "data": [
        {
            "productName": "Aqua",
            "createdDate": "2023-07-11T13:32:16.977",
            "createdBy": "SYSTEM",
            "updatedDate": null,
            "updatedBy": null
        },
        .
        .
        .
    ]
}`
