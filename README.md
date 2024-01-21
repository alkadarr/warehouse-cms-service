# Warehouse CMS Service

This project is a Spring Boot application for managing warehouse storage information across branch stores using a Case Management System (CMS).

## Installation Instructions
First of all, you can clone the project to your and run it by your favorite IDE (I use Intellij IDEA).
Note that you have to make some adjustments.

note : in this project, I use SQL SERVER as the database, Spring boot 3.1.1, and java 17.

## Adjustments
If you just need to test/run the whole project, please execute initiate_query.sql (in Query foler) first on your sql server app.
Then you have to adjust application.properties.

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L4-L9

replace it to your datasource information.

otherwise, you can replace server port and servlet context path.

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L1-L2

and you are able to set the jwt expiration time in app.jwtExpirationMs, and the jwt secret in app.jwtSecret as below

https://github.com/alkadarr/springboot-jwt-auth-basic-code/blob/cb4714c9cdaebb2e9e7a01a50c4a3c60620c125f/src/main/resources/application.properties#L23C1-L25C29

then you can build and run the project.

## User Roles

### Admin (ROLE_ADMIN)

- **Role Description:** Full access to all functionalities and data.
- **Permissions:**
    - CRUD operations on branches, products, storage gudangs, and transactions.
    - Access to all endpoints and functionalities.
    - User management (create, update, delete users).

### Manager (ROLE_MANAGER)

- **Role Description:** Higher-level access, focused on managing branches and overseeing transactions.
- **Permissions:**
    - CRUD operations on branches.
    - View and manage transactions.
    - Limited product and storage gudang management.

### Clerk (ROLE_CLERK)

- **Role Description:** Limited access for transaction handling and basic data management.
- **Permissions:**
    - Record transactions.
    - View branch, product, and storage gudang information.
    - Limited update/delete permissions.

### Auditor (ROLE_AUDITOR)

- **Role Description:** Read-only access for auditing purposes.
- **Permissions:**
    - View all data (branches, products, storage gudangs, transactions).
    - No write or update permissions.

### Warehouse Staff (ROLE_WAREHOUSE_STAFF)

- **Role Description:** Specific access for warehouse-related tasks.
- **Permissions:**
    - CRUD operations on storage gudangs.
    - Record transactions related to storage gudangs.

### Search and Export Role (ROLE_SEARCH_EXPORT)

- **Role Description:** Specialized access for searching and exporting data.
- **Permissions:**
    - Access to search functionality.
    - Ability to export data in various formats.

## API Usage

ON GOING

### 

## Test the application
In this application, you are able to access swagger-ui.html for the documentation. Here's the format

   `http://localhost:server.port/context-path/swagger-ui.html`

There are services including login and register without authentication
