# Camunda Animal Picture App

## Description

The Camunda Animal Picture App is a simple application that allows users to fetch random pictures of a cat, a dog, or a bear. The application is built using Spring Boot and Camunda BPM, and it demonstrates how to use Camunda to handle business processes.

## Features

- Fetch random pictures of cats, dogs, or bears.
- Simple UI to request and display the pictures.
- Stores picture URLs in a database.
- Containerized for easy deployment.

## Prerequisites

- Java 17
- Docker
- Maven

## Setup
Before Use please note to set your own Camunda SaaS Credentials:

```
zeebe.client.cloud.region=<OWN_VALUE>
zeebe.client.cloud.clusterId=<OWN_VALUE>
zeebe.client.cloud.clientId=<OWN_VALUE>
zeebe.client.cloud.clientSecret=<OWN_VALUE>

```
	

### Clone the Repository

```bash
git clone <your-repo-url>
cd camunda-animal-picture-app
```

###Build the Project
```
./mvnw clean package
```
###Run the Application
```
docker-compose up --build
```
###Access the Application
Open your web browser and go to http://localhost:8081/index.html.

###Project Structure

```

camunda-animal-picture-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── camunda/
│   │   │               ├── CamundaApplication.java
│   │   │               ├── config/
│   │   │               │   └── CamundaConfiguration.java
│   │   │               ├── controller/
│   │   │               │   └── AnimalPictureController.java
│   │   │               ├── model/
│   │   │               │   └── Picture.java
│   │   │               ├── repository/
│   │   │               │   └── PictureRepository.java
│   │   │               ├── service/
│   │   │               │   └── AnimalPictureService.java
│   │   │               └── worker/
│   │   │                   └── FetchPictureWorker.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── animal-picture-process.bpmn
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── camunda/
│                       └── CamundaApplicationTests.java
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

###Camunda BPMN Process
The BPMN file animal-picture-process.bpmn is located in the src/main/resources directory. It defines the process for fetching animal pictures based on the user's selection.

###API Endpoints

- Start Process Instance ::: ``POST /api/pictures/start?animalType={animalType}``
	- Starts a new process instance to fetch a picture of the specified animal type (cat, dog, or bear).
- Get Picture URL ::: `` GET /api/pictures/{processInstanceKey}``
	- Retrieves the URL of the picture associated with the given process instance key.
	
###Running the Application Locally

- Build the Project:
	- ```./mvnw clean package ```
- Start the Application:
	- ``` docker-compose up --build ```
- Access UI
	- ``` Open your web browser and go to http://localhost:8081/index.html.```

###Architectural Diagram
Below is a simple architectural diagram showing how the components interact:

```
+--------------------+      +------------------+      +------------------+
|     User UI        |----->| Camunda Process  |----->| Fetch Picture    |
| (HTML/JavaScript)  |      |   (BPMN)         |      | Job Worker       |
+--------------------+      +------------------+      +------------------+
           |                          |                         |
           v                          v                         v
+--------------------+      +------------------+      +------------------+
|    REST API        |<-----|  Picture Service |<-----|   Picture DB     |
+--------------------+      +------------------+      +------------------+

```
	
###Author
```
---------------------
Aditya Kumar
---------------------

```
