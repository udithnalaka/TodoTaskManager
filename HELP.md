# Getting Started

### run test
mvn clean test

### build/run test/package app
mvn clean install

### run app
java -jar ./target/TodoTaskManager-0.0.1-SNAPSHOT.jar


### available API endpoints
1) check health - http://localhost:8080/actuator/health

2) Todo API
    
* POST   /api/v1/todos       createTodoItem
* GET    /api/v1/todos/{id}  getTodoItemById
* PATCH  /api/v1/todos/{id}  updateTodoItem

3) Task API

* GET  /api/v1/tasks/validateBrackets  checkBracketsBalanced


