# Smart Campus API
## Overview

This project implements a RESTful API for a university Smart Campus system using JAX-RS. The API manages Rooms and their associated Sensors (e.g. temperature, CO2 and occupancy), providing a structured interface for accessing and updating campus data.

It follows REST principles with clear resource hierarchies, JSON responses, and standard HTTP methods. The system also includes nested resources and robust error handling.


---

## Building and Launching the Server

1. Download or clone the project from the GitHub repository.

2. Open the project in NetBeans 18 IDE.

3. Ensure Apache Tomcat 9 is configured as the server in NetBeans.

4. Right-click the project and select 'Clean and Build' (this will download all required dependencies using Maven).

5. Right-click the project again and select 'Run'.

6. NetBeans will build the project, deploy it to Tomcat, and start the server.

7. Open Postman and send a GET request to:

   http://localhost:8080/smartCampus/api/v1

8. If the API is running correctly, the discovery endpoint will return the Smart Campus API metadata in JSON format.


---

## Sample curl Commands

### 1. API Discovery
curl http://localhost:8080/smartCampus/api/v1

### 2. Get all rooms
curl http://localhost:8080/smartCampus/api/v1/rooms

### 3. Create a new room
curl -X POST http://localhost:8080/smartCampus/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"BUS-105","name":"Business Room","capacity":80}'

### 4. Get sensors filtered by type (CO2)
curl http://localhost:8080/smartCampus/api/v1/sensors?type=CO2

### 5. Create a new sensor
curl -X POST http://localhost:8080/smartCampus/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"TEMP-010","type":"Temperature","status":"ACTIVE","currentValue":22.5,"roomId":"ENG-101"}'

### 6. Add a sensor reading
curl -X POST http://localhost:8080/smartCampus/api/v1/sensors/TEMP-001/readings \
-H "Content-Type: application/json" \
-d '{"value":25.5}'


---

# Report

## Part 1: Service Architecture & Setup

### 1.	Project & Application Configuration
Question: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime
treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

Answer:

### 2.	The ”Discovery” Endpoint
Question: Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

Answer:

## Part 2: Room Management

### 1.	Room Resource Implementation
Question: When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client-side processing

Answer:

### 2.	Room Deletion & Safety Logic
Question: Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

Answer:

## Part 3: Sensor Operations & Linking

### 1.	Sensor Resource & Integrity 
Question: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

Answer:

### 2.	Filtered Retrieval & Search
Question: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections?

Answer:

## Part 4: Deep Nesting with Sub – Resources

### 1.	The Sub-Resource Locator Pattern
Question: Discuss the architectural benefits of the Sub-Resource Locator pattern. How does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

Answer:

## Part 5: Advanced Error Handling, Exception Mapping & Logging

### 2.	Dependency Validation (422 Unprocessable Entity)
Question: Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

Answer:

### 4.	The Global Safety Net (500)
Question: From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

Answer:

### 5.	API Request & Response Logging Filters
Question: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?

Answer:



