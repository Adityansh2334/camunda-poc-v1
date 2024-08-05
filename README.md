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
Before Use please note to set your own Camunda SaaS Credentials & Google Custom Search Engine Creds:

```
zeebe.client.cloud.region=<ZEEBE_CLIENT_CLOUD_REGION>
zeebe.client.cloud.clusterId=<ZEEBE_CLIENT_CLOUD_CLUSTER_ID>
zeebe.client.cloud.clientId=<ZEEBE_CLIENT_CLOUD_CLIENT_ID>
zeebe.client.cloud.clientSecret=<ZEEBE_CLIENT_CLOUD_CLIENT_SECRET>


google.custom.search.apiKey=<GOOGLE_SEARCH_ENGINE_API_KEY>
google.cse.id=<GOOGLE_SEARCH_ENGINE_ID>

```
## Steps for get Google Custom Search Engine Credentials
	Obtain an API Key:

- Go to the Google Cloud Console.
- Create a new project or select an existing one.
- Enable the Custom Search API.
- Create credentials to obtain an API key.
- Set Up a Custom Search Engine:

	Go to the Custom Search Engine page.
- Create a new search engine.
- In the Sites to Search section, you can add any site or * to search the entire web.
- Note the Search Engine ID.

## Environment Variables
Before running the application, set the following environment variables:

	- ZEEBE_CLIENT_CLOUD_REGION
	- ZEEBE_CLIENT_CLOUD_CLUSTER_ID
	- ZEEBE_CLIENT_CLOUD_CLIENT_ID
	- ZEEBE_CLIENT_CLOUD_CLIENT_SECRET
	
	- GOOGLE_SEARCH_ENGINE_API_KEY
	- GOOGLE_SEARCH_ENGINE_ID

You can set these variables in your shell or add them to a ``.env`` file in the root directory of your project. Docker Compose will automatically load variables from this file.


## Clone the Repository

```bash
git clone https://github.com/Adityansh2334/camunda-poc-v1.git
cd camunda-poc-v1
```
### Build the Project
```
./mvnw clean package
```
### Run the Application

With ``.env`` file: 

```
docker-compose up --build

```
Without ``.env`` file:

```
ZEEBE_CLIENT_CLOUD_REGION=<your-cloud-region> ZEEBE_CLIENT_CLOUD_CLUSTER_ID=<your-cluster-id> ZEEBE_CLIENT_CLOUD_CLIENT_ID=<your-client-id> ZEEBE_CLIENT_CLOUD_CLIENT_SECRET=<your-client-secret> docker-compose up -d
```

### Access the Application
Open your web browser and go to http://localhost:8081/index.html.

### Project Structure

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

### Camunda BPMN Process
The BPMN file animal-picture-process.bpmn is located in the src/main/resources directory. It defines the process for fetching animal pictures based on the user's selection.

### API Endpoints

- Start Process Instance ::: ``POST /api/pictures/start?animalType={animalType}``
	- Starts a new process instance to fetch a picture of the specified animal type (cat, dog, or bear).
- Get Picture URL ::: `` GET /api/pictures/{processInstanceKey}``
	- Retrieves the URL of the picture associated with the given process instance key.
	
### Running the Application Locally

- Build the Project:
	- ```./mvnw clean package ```
- Start the Application:
	- ``` docker-compose up --build ```
- Access UI
	- ``` Open your web browser and go to http://localhost:8081/index.html.```

### Architectural Diagram
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
	
### Author
```
---------------------
Aditya Kumar
---------------------

```
