/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.exception.LinkedResourceNotFoundException;
import com.mycompany.smartcampus.model.Room;
import com.mycompany.smartcampus.model.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author sulaiman
 */
@Path("/sensors")
public class SensorResource {
    
    private final GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private final GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {
        List<Sensor> allSensors = sensorDAO.getAll();
        
        if (type == null) {
            return allSensors;
        }
        
        List<Sensor> filteredSensors = new ArrayList<>();
        
        for (Sensor sensor : allSensors) {
            if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                filteredSensors.add(sensor);
            }
        }
        
        return  filteredSensors;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSensor(Sensor sensor) {
        Room room = roomDAO.getById(sensor.getRoomId());
        
        if (room == null) {
            throw new LinkedResourceNotFoundException("Cannot create sensor. Room with ID " + sensor.getRoomId() + " does not exist.");
        }
        sensorDAO.add(sensor);
        if (room.getSensorIds() == null) {
            room.setSensorIds(new ArrayList<>());
        }
       
        room.getSensorIds().add(sensor.getId());
    }
    
    
    @Path("/{sensorId}/readings")
    public  SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensorDAO.getById(sensorId);
        return new SensorReadingResource(sensor);
    }
    
    
    
}
