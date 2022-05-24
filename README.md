# ptvd-api & ui

In this project we reliazed a simple data visiualization app. Data was taken from  http://data.worldbank.org/ 

## SETUP
In your local machine, one must have installed
 * maven
 * java15
 * node 16.14 or greater
 * yarn 1.22.18 or greater
 
 
 
1.  First this is to setut up the database. Create a new database connection and a database. After that place the username/password/port in the application.yml file **and** liquibase.properties file
2.  Move to src folder
3.  Run liquibase to create tables/load data with one of the following ways
    * Either set the property **spring.liquibase.enabled = true ** and run the app
    * Or run liquibase with its plugin via the maven tab
    * run **mvn liquibase:update** in the src folder
4.  Run the application, either via IDE or run mvn **spring-boot:run** in the src folder


Now if you visit http://localhost:8080/mds/swagger-ui/ and the app started successfully you should see the swagger doc up and running. 

4. Move to file ui
5. run **yarn**
6. run **yarn install**

Now you should have a working UI in the http://127.0.0.1:3000/

If you want to load the demo data as well, move to file scripts&data

7. Run the python file (this should generate a bunch of files)
8. Connect to the database we set up at step 1 and run SHOW VARIABLES LIKE 'secure_file_priv';
9. The response directory is where you should copy the files from step 7
10. Either uncomment from changelog the line that runs the LoadDataInit.sql and run liquibase again, or copy the contents of that file and directly run them in the DB IDE. 

NOTE: CHANGE THE FILEPATH FROM THE FILE LoadDataInit.sql 


## ABOUT THIS PROJECT
This project was part of MYE030 course of UOI under the proffessor *Panagiotis Vasialeiadis* who provided the necessary materiel to implement this project.

The main goal was to set up a database, with proper tables and configurations and have a straight forward way to restore data if they were corrupted/lost.
In addition to that clean code and architectures were required for both the Back-End and Front-End part and a clear documented API.

To achieve those tasks we did the following

* Created and tested different db schemas and configurations so we can end up with a more robust and faster to respond database
* User python scripts to transfrom the initial data to a specific format so the can be loaded with liquibase thus if the db was corrupted within a very short amount of it would be up and running again
* Used java conventions and patterns to stucture the code so it can be easily maintenable
* Added **SWAGGER**, so we could document the API and have a running HTTP CLient to test and experiment with the API we are providing


A link to a video explaining the app, and the process of developing it in greek and apparently in low quality.
https://drive.google.com/file/d/1PqZjpBqxzZeXCV5faDF6jJfvJb9u9vqh/view?usp=sharing

#### TODOS
- [ ] Although the app is not very big, we should have tests that cover the whole functionality of the application, so we can maintain the code easier and be user that we provide something that works
 
- [ ] Add error handlingh to respond with 4xx statuses instead of 5xx


