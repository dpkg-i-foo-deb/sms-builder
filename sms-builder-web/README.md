# SMS-Builder

SMS-Builder is a software tool building to help researchers follow a SMS building process. The software is adapted to the stages of the SMS building process 1) Planning, 2) Study search, 3) Quality analysis, 4) Data collection, 5) Study analysis and classification, and 6) Results.

SMS-Builder was build using:

- Maria DB for data storage
- Java 21 as the programming language
- Java Server Faces for building the GUI.
- Maven as a tool for supports project structuring and dependency management
- Git for version control and change management
- Docker as tools to deploy the software

# Build
```
$ mvn clean package
```
or
```
$ sh sms-builder-web-uberjar.sh
```
# Create image
```
$ docker build -t griduq/sms-builder .
```
or
```
$ sh build-image.sh
```
# RUN
```
mvn compile quarkus:dev
```
```
$ docker run -d -p 8080:8080 -p 4848:4848 -p 3306:3306 --name sms-builder-web griduq/sms-builder-web 
```
or
```
docker run -e MYSQL_ROOT_PASSWORD=YOUR_PASSWORD -d -p 3306:3306 -p 8080:8080 --name sms-builder griduq/sms-builder
```
or
```
$ java -jar ./target/sms-builder-web.jar --port 8080
```
or
```
$ sh sms-builder-run.sh
```
or 
```
$ sh smsbuilderweb.sh
```

## URL

https://localhost:8443

https://localhost:8443/q/swagger-ui/

https://localhost:8443/q/openapi

https://localhost:8443/q/openapi?format=json

