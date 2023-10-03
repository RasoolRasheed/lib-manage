Library Management System
- This is a Library Management System application that helps manage books and members in a library.

*************

    Features
-------------------------------------
1. CRUD operations for books and members.
2. Borrow and return books functionality.
3. Search for books by title or author.

    Technologies Used 
-------------------------------------
Java
Spring Boot
Spring Data JPA
PostgreSQL
Swagger (for API documentation)
JUnit (for unit testing)

    Setup
    Prerequisites:
--------------------------------
Java JDK 8 or later installed
PostgreSQL installed and running

    Clone the repository:
-------------------------------------
git clone <repository_url>
cd library-management-system

    Database Configuration:
-----------------------------------
Create a PostgreSQL database named librarydb.
Update src/main/resources/application.properties with your database credentials.

    Build and Run:
-------------------------------------
./mvnw clean package
java -jar target/library-management-system-<version>.jar
Replace <version> with the actual version from the target folder.

    Access the Application:
-------------------------------------
Open a web browser and go to http://localhost:8080.

    API Documentation
The API documentation is available using Swagger UI. You can access it at http://localhost:8080/swagger-ui.html.

Usage
    Create books and members using the provided APIs or the Swagger UI.
    Borrow and return books using the respective APIs.
    Search for books by title or author using the provided API.
