
# FetchAndSave Backend

This project is a Spring Boot application that fetches posts from an external API and saves them to a PostgreSQL database. It includes endpoints to fetch, save, and delete posts. The backend is deployed on Heroku.

## Features

- Fetch posts from an external API and save them to a PostgreSQL database
- Retrieve all saved posts
- Delete all saved posts

## Technologies Used

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Prerequisites

- JDK 17 or higher
- Maven
- Git

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/TyTe108/FetchAndSave.git
cd FetchAndSave
```

### Local Development

This project is configured to use Heroku’s environment variables for database access, making local development setup a bit complex. However, you can still run it locally with some adjustments.

1. Create a PostgreSQL database.
2. Update `src/main/resources/application.properties` with your local database credentials.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

3. Build and Run Locally

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Access the Application

- Fetch and save posts: `GET /fetch-posts`
- Retrieve all posts: `GET /posts`
- Delete all posts: `DELETE /delete-posts`

## Deployment on Heroku

### Create a Heroku App

```bash
heroku create
```

### Add PostgreSQL Add-on

```bash
heroku addons:create heroku-postgresql:hobby-dev
```

### Deploy to Heroku

1. Add the Heroku remote:

    ```bash
    heroku git:remote -a your-heroku-app-name
    ```

2. Deploy the application:

    ```bash
    git push heroku main
    ```

3. Ensure your Heroku environment variables are set correctly for the database connection. Heroku will automatically configure `JDBC_DATABASE_URL`, `JDBC_DATABASE_USERNAME`, and `JDBC_DATABASE_PASSWORD`.

## Additional Configuration

### CORS Configuration

To allow requests from your frontend, ensure you have the correct CORS configuration in `WebConfig.java`:

```java
package com.example.datacollection.fetch_and_save;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://your-netlify-app-url")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## License

This project is licensed under the MIT License.
