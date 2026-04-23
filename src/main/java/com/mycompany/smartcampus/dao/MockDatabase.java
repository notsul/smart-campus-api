/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.dao;

import com.mycompany.smartcampus.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author sulaiman
 */
public class MockDatabase {
    
    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final Map<String, List<SensorReading>> SENSOR_READINGS = new HashMap<>();
    
    static {
        ROOMS.add(new Room("LIB-301", "Library Quiet Study", 40, new ArrayList<>(Arrays.asList("TEMP-001", "CO2-002"))));
        ROOMS.add(new Room("ENG-101", "Engineering Lab", 25, new ArrayList<>()));
        ROOMS.add(new Room("SCI-202", "Science Seminar Room", 60, new ArrayList<>()));
        
        SENSORS.add(new Sensor("TEMP-001", "Temperature", "ACTIVE", 21.5, "LIB-301"));
        SENSORS.add(new Sensor("CO2-002", "CO2", "ACTIVE", 415.0, "LIB-301"));
        
        SENSOR_READINGS.put("TEMP-001", new ArrayList<>());
        SENSOR_READINGS.put("CO2-002", new ArrayList<>());
        
        SENSOR_READINGS.get("TEMP-001").add(new SensorReading("READ-001", System.currentTimeMillis(), 21.5));
        SENSOR_READINGS.get("CO2-002").add(new SensorReading("READ-002", System.currentTimeMillis(), 415.0));
    }
    
}
