# Getting Started

### run test
mvn clean test

### build/run test/package app
mvn clean install

### run app
java -jar TodoTaskManager-0.0.1-SNAPSHOT.jar


### available API endpoints
1) check health - http://localhost:8080/actuator/health

2) Todo API
    
* POST   /api/v1/todos       createTodoItem
* GET    /api/v1/todos/{id}  getTodoItemById
* PATCH  /api/v1/todos/{id}  updateTodoItem

3) Task API

* GET  /api/v1/tasks/validateBrackets  checkBracketsBalanced


### dockerizing the app (newly added on 16/10/2019)
docker image is created and added to docker hub

* to pull - docker pull udithnalaka/todo-task-manager
* to run  - docker run -p 9090:8080 udithnalaka/todo-task-manager
* swagger doc can be access from - http://localhost:9090/swagger-ui.html


