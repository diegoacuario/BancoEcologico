/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities.service;

import com.unl.banco.entities.ControlApp;
import com.unl.banco.entities.Personas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author KRADAC
 */
@Stateless
@Path("com.unl.banco.entities.personas")
public class PersonasFacadeREST extends AbstractFacade<Personas> {
    @PersistenceContext(unitName = "BancoEcologicoPU")
    private EntityManager em;

    public PersonasFacadeREST() {
        super(Personas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Personas entity) {
        super.create(entity);
    }

    @POST
    @Path("registro")
    @Produces({"text/plain", "application/json"})
    public String create(
            @FormParam("ciPersona") String ciPersona,
            @FormParam("nombresPersona") String nombresPersona,
            @FormParam("apellidospPersona") String apellidospPersona,
            @FormParam("apellidosmPersona") String apellidosmPersona,
            @FormParam("usuario_app") String usuarioApp,
            @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            super.create(new Personas(ciPersona, nombresPersona, apellidospPersona, apellidosmPersona));
            TypedQuery<Personas> qry;
            qry = getEntityManager().createQuery("SELECT p FROM Personas p WHERE p.ciPersona = :ciPersona", Personas.class);
            qry.setParameter("ciPersona", ciPersona);

            return "" + qry.getSingleResult().getIdPersona();
        } else {
            return "false";
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Personas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Personas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Personas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("registro={ciPersona},{nombresPersona},{apellidospPersona},{apellidosmPersona}")
    @Produces({"text/plain", "application/json"})
    public String create(
            @PathParam("ciPersona") String ciPersona,
            @PathParam("nombresPersona") String nombresPersona,
            @PathParam("apellidospPersona") String apellidospPersona,
            @PathParam("apellidosmPersona") String apellidosmPersona) {

        super.create(new Personas(ciPersona, nombresPersona, apellidospPersona, apellidosmPersona));
        TypedQuery<Personas> qry;
        qry = getEntityManager().createQuery("SELECT p FROM Personas p WHERE p.ciPersona = :ciPersona", Personas.class);
        qry.setParameter("ciPersona", ciPersona);

        return "" + qry.getSingleResult().getIdPersona();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Personas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
