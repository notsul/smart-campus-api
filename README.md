--- OVERVIEW ---

This project implements a RESTful API for a university Smart Campus system using JAX-RS. The API manages Rooms and their associated Sensors (e.g. temperature, CO2 and occupancy), providing a structured interface for accessing and updating campus data.

It follows REST principles with clear resource hierarchies, JSON responses, and standard HTTP methods. The system also includes validation and robust error handling.



--- BUILDING/LAUNCHING THE SERVER ---

1. Download or clone the project from the GitHub repository.

2. Open the project in NetBeans 18 IDE.

3. Ensure Apache Tomcat 9 is added in NetBeans as the server.

4. In NetBeans, right-click the project and select 'Clean and Build'.

5. Right-click the project again and select 'Run'.

6. NetBeans will build the project, deploy it to Tomcat, and start the server.

7. Open Postman and input:

  GET `http://localhost:8080/smartCampus/api/v1`

8. If the API is running correctly, the discovery endpoint will return the Smart Campus API metadata in JSON format.



--- Curl Commands ---

1. API Discovery
curl http://localhost:8080/smartCampus/api/v1

2. Get all rooms
curl http://localhost:8080/smartCampus/api/v1/rooms

3. Create a new room
curl -X POST http://localhost:8080/smartCampus/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"BUS-105","name":"Business Room","capacity":80}'

4. Get sensors filtered by type (CO2)
curl http://localhost:8080/smartCampus/api/v1/sensors?type=CO2

5. Create a new sensor
curl -X POST http://localhost:8080/smartCampus/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"TEMP-010","type":"Temperature","status":"ACTIVE","currentValue":22.5,"roomId":"ENG-101"}'

6. Add a sensor reading
curl -X POST http://localhost:8080/smartCampus/api/v1/sensors/TEMP-001/readings \
-H "Content-Type: application/json" \
-d '{"value":25.5}'
