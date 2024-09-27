# Product Manager Application

This is the Product Manager application developed with Spring Boot. This guide will walk you through the process of cloning, building, and running the application.

## Requirements

- Java JDK 11 or higher
- Maven 3.6 or higher
- MySQL database
- MySQL Connector (JDBC)

## Setup

### 1. Clone the Repository

bash
git clone <repository-url>
cd product-manager


### 2. Set Up the Database

1. Ensure that MySQL is running.
2. Create a new database for the application:

sql
CREATE DATABASE product_manager;


3. Import the initial database dump:

bash
mysql -u root -p product_manager < "product-manager-dump.sql"


### 3. Configuration

1. Open the file `src/main/resources/application.yml`.
2. Configure the database connection:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_manager
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


### 4. Install Dependencies

Ensure that you have installed all dependencies with Maven:

bash
mvn clean install


### 5. Run the Application

To start the application, execute the following command:

bash
mvn spring-boot:run


### 6. Access the Application

Open your web browser and go to:

http://localhost:8080

## Features

- User registration
- Product management
- Category management

## Troubleshooting

If you encounter issues while starting the application:

- Ensure that all dependencies are correctly installed.
- Check the settings in `application.properties`.
- Verify the MySQL database connection and permissions.

## Support

If you would like to contribute to the application, or you need help please contact me directly per mail: cedric.baumann@ict.csbe.ch
