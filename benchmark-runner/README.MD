This application is an Spring application running from command line.

It is a client for reactiveweb-app and servlet-app. It starts, in parrallel, the number of requests specified as input argument.


When running this application, it assumed that demo-server is running at [http://localhost:8080](http://localhost:8080) , reactiveweb-app at [http://localhost:8081](http://localhost:8081) , servlet-app at [http://localhost:8082](http://localhost:8082)

If the 3 applications are not yet running, please start them, following instructions located in each project's folder.


To compile the project, run:
```shell
  mvn clean install
```

To start 25 concurrent requests to servlet-app, run:
```shell
  java -jar target/benchmark-runner-0.0.1-SNAPSHOT.jar servlet 25
```
Please observe this benchmark's console, and servlet-app's console.


To start 25 concurrent requests to reactiveweb-app, run:
```shell
  java -jar target/benchmark-runner-0.0.1-SNAPSHOT.jar reactive 25
```
Please observe this benchmark's console, and reactiveweb-app's console.


From reactiveweb-app's console, did you noticed something interesting? 

