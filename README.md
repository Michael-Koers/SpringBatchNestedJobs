
# Introduction
This project was to test whether nested / composed Jobs in Spring Batch 5+ using the Jackson serializer for execution context (Jackson2ExecutionContextStringSerializer.class) still worked, as we were running into problems in our Spring Batch migration.

Turns out, Nested Jobs with Jackson serializer still work fine, so our issue is elsewhere :)

# Setting up

### Requirements
- Java 17
- Optional: Docker (you could also run an in-memory database)
- Optional: Editor for query'ing your database of choice. (I used SQL Server Management Studio as I used a MSSQL container)

### Database
To run this project for yourself, you need to have a Microsoft SQL instance running somewhere. 
Adviced to use Docker container:
``` 
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Password1!" --name mssql -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest
```

This runs a simple MSSQl docker container. Mind you, if you want to change any properties (like password) make sure to reflect the changes in the `application.properties` as well

Reason I used a persistent database was to be able to query the Spring Batch metadata tables after running jobs.
For more information, [see the Spring Meta-Data scheme documentation](https://docs.spring.io/spring-batch/reference/schema-appendix.html)
### Run

Now either simply run the project in your editor, via the `SpringBatchNestedJobsApplication.class`, or build a jar to execute as follows:

```
mvn clean package
java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```

### Experiment
Now you have your Spring Batch application running. Feel free to experiment with launching and changing jobs.


