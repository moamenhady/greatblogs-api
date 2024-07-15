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

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (version 17 or higher)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/moamenhady/greatblogs-api.git
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
   
2. Configure other properties as needed (e.g., server port, logging).

## Usage

```bash
java -jar target/greatblogs-api-1.2.2.jar
```

Access the API at http://localhost:8080/

## API Endpoints

All endpoints require user to be authenticated except `/authors/signup`

authors:

- **POST /api/authors/signup:** Sign-up a new author account with username, password and email.
- **GET /api/authors/all:** Retrieve all authors by username.
- **GET /api/authors/{id}:** Retrieve a specific author.
- **PUT /api/authors/update/{id}:** Update an existing author's fullName and about.

posts:

- **POST /api/posts/create:** Create a new blog post.
- **GET /api/posts/all:** Retrieve all blog posts.
- **GET /api/posts/{id}:** Retrieve a specific blog post.
- **PUT /api/posts/update/{id}:** Update an existing blog post.
- **DELETE /api/posts/delete/{id}:** Delete a blog post.

## Authentication

This API uses Basic Authentication. Include the Authorization header in your requests with the format Basic base64(username:password).

Example using curl:

```bash
curl -X GET -H "Authorization: Basic base64(username:password)" http://localhost:8080/authors/all
```

## License

> This project is licensed under the [MIT License](LICENSE).
