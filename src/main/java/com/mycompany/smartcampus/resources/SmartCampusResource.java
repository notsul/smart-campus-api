/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.model.ApiDiscovery;
import com.mycompany.smartcampus.model.Admin;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sulaiman
 */

@Path("/")
public class SmartCampusResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiDiscovery getDiscovery() {
        Admin admin = new Admin("John Doe", "john_doe@smartcampus.com");
        
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        resources.put("readings", "/api/v1/sensors/{sensorId}/readings");
        
        return new ApiDiscovery("Smart Campus API", "v1", admin, resources);

    }
}
