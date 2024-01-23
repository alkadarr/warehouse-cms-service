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
<details>
  <summary><strong>User Roles</strong></summary>

### Admin (ROLE_ADMIN)

- **Role Description:** Full access to all functionalities and data.
- **Permissions:**
    - CRUD operations on branches, products, warehouse storage, and transactions.
    - Access to all endpoints and functionalities.
    - User management (create, update, delete users).

### Manager (ROLE_MANAGER)

- **Role Description:** Higher-level access, focused on managing branches and overseeing transactions.
- **Permissions:**
    - CRUD operations on branches.
    - View and manage transactions.
    - Limited product and warehouse storage management.

### Clerk (ROLE_CLERK)

- **Role Description:** Limited access for transaction handling and basic data management.
- **Permissions:**
    - Record transactions.
    - View branch, product, and warehouse storage information.
    - Limited update/delete permissions.

### Auditor (ROLE_AUDITOR)

- **Role Description:** Read-only access for auditing purposes.
- **Permissions:**
    - View all data (branches, products, warehouse storage, transactions).
    - No write or update permissions.

### Warehouse Staff (ROLE_WAREHOUSE_STAFF)

- **Role Description:** Specific access for warehouse-related tasks.
- **Permissions:**
    - CRUD operations on warehouse storage.
    - Record transactions related to warehouse storage.

### Search and Export Role (ROLE_SEARCH_EXPORT)

- **Role Description:** Specialized access for searching and exporting data.
- **Permissions:**
    - Access to search functionality.
    - Ability to export data in various formats.
</details>

## API Usage

<details>
  <summary><strong>Warehouse Controller</strong></summary>

- **GET /api/auth**
  - Description: Retrieve user token description.
- **POST /api/auth**
  - Description: Generate token by username password.

</details>

<details>
  <summary><strong>User Controller</strong></summary>

- **GET /api/user**
  - Description: Retrieve all users.
- **GET /api/user/{userId}**
  - Description: Retrieve a user by ID.
- **GET /api/user/paging**
  - Description: Retrieve paginated user data.
- **POST /api/user**
  - Description: Register a new user.
- **PUT /api/user**
  - Description: Update user information.
- **DELETE /api/user/{userId}**
  - Description: Soft delete a user by ID.

</details>

<details>
  <summary><strong>Role Controller</strong></summary>

- **GET /api/role**
  - Description: Retrieve list of all roles.
- **GET /api/role/{roleId}**
  - Description: Retrieve a role by ID.
  
</details>

<details>
  <summary><strong>Warehouse Controller</strong></summary>

- **GET /api/warehouse**
  - Description: Retrieve all warehouses.
- **GET /api/warehouse/{warehouseId}**
  - Description: Retrieve a warehouse by ID.
- **GET /api/warehouse/paging**
  - Description: Retrieve paginated warehouse data.
- **POST /api/warehouse**
  - Description: Register a new warehouse.
- **PUT /api/warehouse**
  - Description: Update warehouse information.
- **DELETE /api/warehouse/{warehouseId}**
  - Description: Soft delete a warehouse by ID.

</details>


## Test the application
In this application, you are able to access swagger-ui.html for the documentation. Here's the format

   `http://localhost:server.port/context-path/swagger-ui.html`

There are services including login and register without authentication
