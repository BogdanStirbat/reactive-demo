This application offers a simple API, for retrieving an message from database according to a message id. 

To start this application, first the database needs to be configured. Then, the server will be run as a simple Spring Boot application.

###### For setting the database, execute following steps.
1. Download derby
  Go to [https://db.apache.org/derby/derby_downloads.html](Apache Derby official releases page) , download and extract the latest archive.

2. Start Apache Derby Network Server (the database server used by application)
  Go to /place/where/you/extracted/derby/bin, run:
```shell
  sh startNetworkServer
```

3. Download SQuirreL SQL Client
   Go to [http://squirrel-sql.sourceforge.net/#installation](SQuirreL SQL Client download page) , download the latest jar and run it; running this jar will install SQuirreL SQL Client

4. Connect to the Derby datatabase using SQuirreL SQL Client
    * Open SQuirreL SQL Client, then go to Drivers, then edit Apache Derby Client (select this driver, then press the pencil button)
    * Add the Derby client driver jar, by clicking 'Extra Class Path', press 'Add', navigate to /place/where/you/extracted/derby/drivers and select derbyclient.jar
    * Press 'OK', go to Aliasses
    * Connect to the application's database, by adding a new alias: click the Plus button, and set 'Apache Derby Client' for driver, 'sa' for username, 'password' for password, 'jdbc:derby://localhost:1527/demo_messages;create=true' for url, and chhose a descriptive name for Name; press 'Test' (you should see and 'Ok' popup), then press 'Ok'
    * Connect to this alias, then disconnect. Congratulations, you created the application's database! Inspect the /place/where/you/extracted/derby/bin folder, if you don't belive me! (I love the simplicity of setting Derby database, for simple demos and tutorials)
    * Edit the alias: fro URL, remove the ';create=true' part (we don't want to recreate the database each time we connect)
    * Connect again, and run script from populate_db.sql; this script will populate the DB with some dummy messages, our application will receive an id and will return the message associated with it.


Now, we can compile and run the application. Being an Spring Boot application, this part is rather easy:
1. Compile the project:
```shell
  mvn clean install
```

2. Run the application
```shell
  java -jar target/demo-server-0.0.1-SNAPSHOT.jar
```

Now, this application is running at [http://localhost:8080](http://localhost:8080) ; check, for example, [http://localhost:8080/message/25](http://localhost:8080/message/25)
