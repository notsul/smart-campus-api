/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.model;

import java.util.Map;
/**
 *
 * @author sulaiman
 */
public class ApiDiscovery {
    private String api;
    private String version;
    private Admin admin;
    private Map<String, String> resources;

    public ApiDiscovery() {
    }

    public ApiDiscovery(String api, String version, Admin admin, Map<String, String> resources) {
        this.api = api;
        this.version = version;
        this.admin = admin;
        this.resources = resources;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }
    
}
