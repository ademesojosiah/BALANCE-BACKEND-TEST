# BALANCEE-BACKEND-TEST
A RESTful API that allows users to create, update, and delete tasks. Users are be able to assign tasks to themselves, set due dates, and mark tasks as completed and basic user authentication with Java

# Task Management API

The Task Management API is a RESTful web service built with Spring Boot that allows users to manage tasks.


## Features

- User authentication using Spring Security.
- CRUD operations for tasks: Create, Read, Update, and Delete.
- Marking tasks as completed.
- Filtering tasks by completion status.
- Access control with authentication based on Spring Security.

## Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven
- MySQL or other compatible database (with necessary configurations)

## Installation

1. Clone the repository:


   ```sh
   git clone
   cd into directory
   ```

2. Configure your database settings in `src/main/resources/application.properties`.

3. Build the project:

   ```sh
   mvn clean install
   ```

4. Run the application:

   ```sh
   mvn spring-boot:run
   ```

## Usage

To use the API, you can interact with it using HTTP requests. You can use tools like `curl`, Postman, or any other REST client.

Ensure you are authenticated before making requests. You can authenticate using  basic authentication.

## Endpoints

### Register User

```http
GET /signup?username=george&password=osime
```

create a user

### Get All Tasks

```http
GET /api/v1/tasks?completed=true|false  || GET /api/v1/tasks
```

Get a list of tasks, optionally filtered by completion status.

### Create a Task

```http
POST /api/v1/tasks
```

Create a new task.

### Update a Task

```http
PUT /api/v1/tasks/{taskId}
```

Update an existing task.

### Complete a Task

```http
PUT /api/v1/tasks/{taskId}/complete
```

Mark a task as completed.

### Delete a Task

```http
DELETE /api/v1/tasks/{taskId}
```

Delete a task.

### Get a Task by ID

```http
GET /api/v1/tasks/{taskId}
```

Get a task by its ID.

## Contributors
Ademeso Josiah

```
