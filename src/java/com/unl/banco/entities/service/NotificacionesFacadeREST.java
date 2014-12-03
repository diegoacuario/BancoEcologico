/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unl.banco.entities.service;

import com.unl.banco.entities.ControlApp;
import com.unl.banco.entities.Notificaciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
@Path("com.unl.banco.entities.notificaciones")
public class NotificacionesFacadeREST extends AbstractFacade<Notificaciones> {

    @PersistenceContext(unitName = "BancoEcologicoPU")
    private EntityManager em;

    public NotificacionesFacadeREST() {
        super(Notificaciones.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Notificaciones entity) {
        super.create(entity);
    }

    @POST
    @Path("notificacionInvitado")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Notificaciones> historialInvitado(@FormParam("idInvitado") Integer idInvitado, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Notificaciones> qry;

            qry = getEntityManager().createQuery("SELECT n FROM Notificaciones n WHERE n.idHistorial.idInvitado = :idInvitado AND n.estadoNotificacion = :estado", Notificaciones.class);
            qry.setParameter("idInvitado", idInvitado);
            qry.setParameter("estado", false);

            List<Notificaciones> lh = null;
            try {
                lh = qry.getResultList();
                return lh;
            } catch (NoResultException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @GET
    @Path("notificacion={idInvitado}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Notificaciones> otra(@PathParam("idInvitado") Integer idInvitado) {

        TypedQuery<Notificaciones> qry;

        qry = getEntityManager().createQuery("SELECT n FROM Notificaciones n WHERE n.idHistorial.idInvitado = :idInvitado AND n.estadoNotificacion = :estado", Notificaciones.class);
        qry.setParameter("idInvitado", idInvitado);
        qry.setParameter("estado", false);

        List<Notificaciones> lh = null;
        try {
            lh = qry.getResultList();
            return lh;
        } catch (NoResultException e) {
            return null;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Notificaciones entity) {
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
    public Notificaciones find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Notificaciones> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Notificaciones> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
