# Wildlife Tracker
## Description
This is an application that stores data given by users and retrieves it by the use of Spark REST routing and SQL queries.
## Technologies used
- Postgresql
- SparkJava
- Handlebars
- Intelli j
- Gradle
- Junit

## Behavior Driven Development

This application implements Create Read Update Delete ( CRUD ) operations in its entirety which rely on the basic database

| Behavior                   | Input                                                                   | Expected Output                                                                                           |
|----------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Register Ranger            | Ranger details that satisfy the given format                            | A new Ranger object is created and added to the database.                                                 |
| Record Endangered Animal   | Endangered animal details as well as the location of sighting           | A new Endangered animal object is created along with a location object and both are added to the database |   
| Record Unthreatened Animal | Endangered animal details as well as the location of sighting           | A new Endangered animal object is created along with a location object and both are added to the database |
| View Sightings             | Sightings Link is clicked                                               | Sightings are queried and retrieved from the database                                                     |
| View Ranger                | Ranger view links are clicked                                           | Specific ranger is queried and retrieved from the database                                                |
| View Location              | Location Link is clicked                                                | The specific Location is queried and retrieved                                                            |
| Delete Sighting(s)         | The delete button on each sighting or the delete all button is clicked  | The respective sighting or all sightings are deleted from the database                                    |


## Test Driven Development
Each database operation method has been tested using Junit to make sure the correct operations take place without errors.

## Setup
### Prerequisites
- Postgresql ( 14.2 )
- Java Development Kit ( JDK )
- Gradle

### Install

Clone the repository using the following command:
```
git clone https://github.com/Nyanjuimarvin/Wildlife-Tracker.git
```

### Recreate Database
Navigate to the root directory of the project in your terminal and Run the following command to recreate the database on your local machine
```
psql < create.sql;
```
If the database seems to be populated, reset it using:
```
psql < drop.sql
```
and recreate it again.

You may need to change the connection string in Db.java so that it matches your postgres username and password. If so, focus on the lines that match the following:
```
String username = (dbUri.getUserInfo() == null) ? <YOUR-DATABASE-USERNAME-HERE> : dbUri.getUserInfo().split(“:”)[0]; 
String password = (dbUri.getUserInfo() == null) ? <YOUR-DATABASE-PASSWORD-HERE> : dbUri.getUserInfo().split(“:”)[1]; 
```


### Run
Open the project in Intellij Idea and refresh gradle to download dependencies.  
After downloading dependencies, run
```
gradle build
```  
and thereafter
```
gradle run
```
to ignite a Spark server.The project can now be viewed at
```
localhost:4567
```

## Live Link
https://w-life.herokuapp.com/
## Contact
For any issues, additional requests or compliments, reach out to me using:
* E-mail - marnyanjui@gmail.com



## License and Copyright

Copyright 2022 Marvin Nyanjui

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.