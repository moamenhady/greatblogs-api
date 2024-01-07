# GreatBlogs API

This is a RESTful API for a simple blog engine developed using Spring Boot, Spring Data, Spring Security, and PostgreSQL.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [License](#license)

## Features

- Create, read, update, and delete blog posts.
- User authentication using Basic Authentication.
- Secure password storage using bcrypt.
- Persistent storage of blog data in a PostgreSQL database.

## Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (version 21 or higher)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/greatblogs-api.git
   ```
   
2. Build the project using Maven:

   ```bash
   cd greatblogs-api
   mvn clean install
   ```

#### Configuration

1. Configure the database connection in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
   
3. Configure other properties as needed (e.g., server port, logging).

## Usage

```bash
java -jar target/greatblogs-api-1.0.0.jar
```

Access the API at http://localhost:8080/

## API Endpoints

All endpoints requires user to be authenticated exept `/authors/signup`

authors:

- **POST /authors/signup:** Sign-up a new author account with username, password and email.
- **GET /authors/all:** Retrieve all authors by username.
- **GET /authors/{id}:** Retrieve a specific author.
- **PUT /authors/update/{id}:** Update an existing author's fullName and about.

posts:

- **POST /posts/create:** Create a new blog post.
- **GET /posts/all:** Retrieve all blog posts.
- **GET /posts/{id}:** Retrieve a specific blog post.
- **PUT /posts/update/{id}:** Update an existing blog post.
- **DELETE /posts/delete/{id}:** Delete a blog post.

## Authentication

This API uses Basic Authentication. Include the Authorization header in your requests with the format Basic base64(username:password).

Example using curl:

```bash
curl -X GET -H "Authorization: Basic base64(username:password)" http://localhost:8080/authors/all
```

## License

> This project is licensed under the [MIT License](LICENSE).
