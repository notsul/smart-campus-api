/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.dao;

import com.mycompany.smartcampus.model.BaseModel;
import java.util.List;
/**
 *
 * @author sulaiman
 */
public class GenericDAO<T extends BaseModel> {
    
    private final List<T> items;
    
    public GenericDAO(List<T> items) {
        this.items = items;
    }
    
    public T getById(String id) {
        for (T item : items) {
            if (item.getId() != null && item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
    
    
    public List<T> getAll() {
        return items;
    }
    
    
    public void add(T item) {
        items.add(item);
    }
    
    
    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if(item.getId() != null && item.getId().equalsIgnoreCase(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }
    
    
    public boolean delete(String id) {
        return items.removeIf(item -> item.getId() != null && item.getId().equalsIgnoreCase(id));
        
    }
    
}
