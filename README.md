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

### 1.1	Project & Application Configuration
Question: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime
treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

Answer: JAX-RS creates a new instance of a resource class for each incoming request. This helps prevent issues when multiple users access the API simultaneously. However, shared data structures like lists and maps still need to be managed carefully, as many requests can access them and modify them at the same time which will lead to data inconsistency if not handled properly.

### 1.2	The ”Discovery” Endpoint
Question: Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

Answer: Hypermedia refers to the inclusion of links in API responses to visualise the actions clients can take next. This makes the API easier to use because clients do not need to rely on external documentation to navigate it. It also reduces the need to hardcode URLs, making the system easier to maintain.

## Part 2: Room Management

### 2.1	Room Resource Implementation
Question: When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client-side processing

Answer: Returning only room IDs makes the response small and uses less bandwidth. However, the client would need to make additional requests to obtain full details. Returning full room objects provides all the information in a single response making it easier to use, but it increases the data size.

### 2.2	Room Deletion & Safety Logic
Question: Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

Answer: In my API, the DELETE operation is idempotent. The first request deletes the room if it exists and has no sensors. If the request is sent again, the room has already been deleted, so the server returns a generic 500 error, but nothing changes on the server.

## Part 3: Sensor Operations & Linking

### 3.1	Sensor Resource & Integrity 
Question: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

Answer: That annotation means the API only accepts JSON data. If a client sends a different format, like text/plain or XML, JAX-RS cannot process it and returns an error. This helps ensure that only properly formatted requests are accepted.

### 3.2	Filtered Retrieval & Search
Question: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections?

Answer: Using @QueryParam is better for filtering because it allows optional queries without changing the URL structure. Clients can easily add filters, for example: sensors?type=CO2. On the other hand, using path parameters like sensors/type/CO2 is harder to scale because each filter would require a different URL, making query parameters much easier and more scalable for filtering.

## Part 4: Deep Nesting with Sub – Resources

### 4.1	The Sub-Resource Locator Pattern
Question: Discuss the architectural benefits of the Sub-Resource Locator pattern. How does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

Answer: The Sub-Resource Locator pattern helps keep the API organised by moving nested logic into separate classes. This makes each resource easier to understand. It also prevents a single class from becoming too large and messy, making it easier to add new features later.

## Part 5: Advanced Error Handling, Exception Mapping & Logging

### 5.2	Dependency Validation (422 Unprocessable Entity)
Question: Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

Answer: HTTP 422 is more appropriate in this scenario because there is nothing wrong with the request but the data it contains is incorrect, such as using a roomId that doesn’t exist. A 404 error code means the resource cannot be found. Therefore, using 422 makes it clear that the error lies with the input data as opposed to the API itself.

### 5.4	The Global Safety Net (500)
Question: From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

Answer: Exposing Java stack traces can reveal sensitive internal details, such as package and class names and the overall system structure. An attacker could exploit this information to identify potential vulnerabilities and exploit them. Implementing a global exception mapper can catch random errors and, in turn, hide their stack traces, helping prevent this information from being exposed to the client.

### 5.5	API Request & Response Logging Filters
Question: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?

Answer: A JAX-RS filter allows logging to be handled in a single central place rather than adding it to each method. This reduces repeated code and keeps the resource classes easy to read. It also makes logging much easier to manage and update across the whole API.



