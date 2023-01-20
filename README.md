# Drones Management Service

This service enables us to communicate with drones.

## Features

* Drone registration
* Loading drones with medical loads.
* Querying drones available for loading.
* Querying a drone's battery level.
* Periodically logs objects' state to database.

## Pre-installation requirements

* Install [Java](https://www.java.com/en/download/).
* Set the `JAVA_HOME` environment variable.
* Ensure that port 8080 no your computer is not being used by another application.

## Installation

* Extract the files of the `"joel-mugaya.zip"`.
* In the terminal, navigate into the extracted directory `"joel-mugaya"`.
* Run `"./mvnw package"` for Mac-os and Linux users or `".\mvnw.cmd package"` on windows.
* After packaging is complete, run the command `"java -jar ./target/api-0.0.1-SNAPSHOT.jar"` to start the application. If prompted to allow fire-wall access, click okay/accept.
* The application will then be accessible at: `"http://localhost:8080/"`.
* The database can be accessed at: `"http://localhost:8080/h2-console"` using `"JDBC URL: jdbc:h2:mem:appDb"` and `"UserName: sa"` with no password.

## Endpoints

**Register a drone.**  

Request:- `POST /drones/register`  
  {  
    "serialNumber": "TYUBc50",   
    "model":"Heavyweight",  
    "weightLimit":500,  
    "batteryCapacity": 90,  
    "state": "IDLE"  
  }  
 
Response on success:-  
status code: 201,  
Body: {  
    "serialNumber": "TYUBc50",  
    "model": "Heavyweight",  
    "weightLimit": 500,  
    "batteryCapacity": 90,  
    "state": "IDLE",  
    "medications": null  
}  


**Get drones avalilable for loading.**  

Request:- `GET /drones/available` 
 
Response on success:-  
status code: 200, Body: [ ... ]  


**Get a drone by serial number.**  

Request:- `GET /drones/{serialNumber}`  

Response on success:-  
status code: 200,  
Body: { "serialNumber": "TYUBc50", "model": "Heavyweight", "weightLimit": 500, "batteryCapacity": 90, "state": "IDLE", "medications": [] }  


**Get drone's battery level.**  

Request:- `GET /drones/{serialNumber}/battery_level`  

Response on success:-  
status code: 200, Body: {Percentage}  


**Load a Drone.**  

Request:- `POST /drones/{serialNumber}/load`  
{ "name":"COVID19 Test kit", "weight": 60, "code":"SNDP_566", "image":"" } 
 
Response on success:-  
status code: 200,  
Body: {  
    "serialNumber": "TYUBc50",  
     ...  
    "medications": [
	    {
		    "name":"COVID19 Test kit",
		    "weight": 60,
		    "code":"SNDP_566",
		    "image":""
		},
		...
    ]  
}  


**Check a drone's load.**  

Request:- `GET /drones/{serialNumber}/load`  

Response on success:-  
status code: 200,  
Body: [
	    {
		    "name":"COVID19 Test kit",
		    "weight": 60,
		    "code":"SNDP_566",
		    "image":""
		},
		...
    ] 


### Error responses

status code: 400, Body: { "message": "error message" }

other status codes: {
    "timestamp": "timestap",
    "status": status code,
    "error": "error message",
    "path": "request path"
}  
.  
  

