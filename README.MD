In this demo, a Spring reactive web application is compared with a traditional Spring servlet web application.

For this purpose, 4 different applications were created:
  * demo-server, a simple application; via an API, it accepts an message id, and retrieves the corresponding message from DB
  * reactiveweb-app, a Spring reactive web application; it accepts an message id, and solves the message consuming the demo-server's API.
  * servlet-app, a traditional Spring servlet web; it accepts an message id, and solves the message consuming the demo-server's API.
  * benchmark-runner, a Spring CMD application; 

To run this demo:
  * start demo-server, reactiveweb-app, servlet-app, each in it's own terminal, following instructions from each README.MD file
  * run the benchmark-runner:
  ```shell
  java -jar target/benchmark-runner-0.0.1-SNAPSHOT.jar servlet|reactive <number of concurrent requests>
  ```
