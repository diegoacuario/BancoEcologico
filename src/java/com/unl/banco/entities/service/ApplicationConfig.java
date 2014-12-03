/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author KRADAC
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.unl.banco.entities.service.ClientesFacadeREST.class);
        resources.add(com.unl.banco.entities.service.ControlAppFacadeREST.class);
        resources.add(com.unl.banco.entities.service.CuentasFacadeREST.class);
        resources.add(com.unl.banco.entities.service.HistorialesFacadeREST.class);
        resources.add(com.unl.banco.entities.service.InvitadosFacadeREST.class);
        resources.add(com.unl.banco.entities.service.NotificacionesFacadeREST.class);
        resources.add(com.unl.banco.entities.service.PersonasFacadeREST.class);
        resources.add(com.unl.banco.entities.service.TransaccionesFacadeREST.class);
    }
    
}
