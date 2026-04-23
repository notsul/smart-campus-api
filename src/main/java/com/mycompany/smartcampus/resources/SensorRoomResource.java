/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.exception.RoomNotEmptyException;
import com.mycompany.smartcampus.model.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author sulaiman
 */
@Path("/rooms")
public class SensorRoomResource {
    
    private final GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms() {
        return roomDAO.getAll();
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("roomId") String roomId) {
        return roomDAO.getById(roomId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void addRoom(Room room) {
        roomDAO.add(room);
    }
    
    @DELETE
    @Path("/{roomId}")
    public void deleteRoom(@PathParam("roomId") String roomId) {
        Room room = roomDAO.getById(roomId);
        if(room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room with ID " + roomId + " cannot be deleted because it still has active sensors.");
        }
        roomDAO.delete(roomId);
    }
    
}
