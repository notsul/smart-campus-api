/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.exception.SensorUnavailableException;
import com.mycompany.smartcampus.model.Sensor;
import com.mycompany.smartcampus.model.SensorReading;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author sulaiman
 */
public class SensorReadingResource {
    
    private final Sensor sensor;
    private final GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);

    public SensorReadingResource(Sensor sensor) {
        this.sensor = sensor;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getAllReadings() {
        
        List<SensorReading> readings = MockDatabase.SENSOR_READINGS.get(sensor.getId());
        
        if (readings == null ) {
            return new ArrayList<>();
        }
        
        return readings;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addReading(SensorReading reading) {
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor with ID " + sensor.getId() + " is under maintenance and cannot accept new readings.");
            
        }
        List<SensorReading> readings = MockDatabase.SENSOR_READINGS.get(sensor.getId());
        
        if (readings == null) {
            readings = new ArrayList<>();
            MockDatabase.SENSOR_READINGS.put(sensor.getId(), readings);
            
        }
        
        if (reading.getTimestamp() <= 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }
        
        readings.add(reading);
        sensor.setCurrentValue(reading.getValue());
        sensorDAO.update(sensor);
    }
    

    
    
    
}
