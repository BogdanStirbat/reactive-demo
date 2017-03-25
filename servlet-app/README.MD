This is an simple traditional Spring servlet web application, consuming the API exposed by demo-server.

When running this project, it is assumed that demo-server application is running at [http://localhost:8080](http://localhost:8080) ; if demo-server is not yet started, please start it according to instructions located in demo-server's root folder.


Being an Spring Boot application, starting this application is easy:
1. Compile the project:
```shell
  mvn clean install
```

2. Run the application
```shell
  java -jar target/servlet-app-0.0.1-SNAPSHOT.jar
```

Now, this application is running at [http://localhost:8082](http://localhost:8082) ; check, for example, [http://localhost:8082/message/25](http://localhost:8082/message/25)
