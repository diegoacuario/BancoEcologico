/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unl.banco.entities.service;

import com.unl.banco.entities.Clientes;
import com.unl.banco.entities.ControlApp;
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
@Path("com.unl.banco.entities.clientes")
public class ClientesFacadeREST extends AbstractFacade<Clientes> {

    @PersistenceContext(unitName = "BancoEcologicoPU")
    private EntityManager em;

    public ClientesFacadeREST() {
        super(Clientes.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Clientes entity) {
        super.create(entity);
    }

    @POST
    @Path("login")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Clientes> login(@FormParam("usuario") String usuario, @FormParam("clave") String clave, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Clientes> qry;

            qry = getEntityManager().createQuery("SELECT c FROM Clientes c WHERE c.nusuarioCliente = :nusuarioCliente AND c.contraseniaCliente = :contraseniaCliente", Clientes.class);
            qry.setParameter("nusuarioCliente", usuario);
            qry.setParameter("contraseniaCliente", clave);

            try {
                return qry.getResultList();
            } catch (NoResultException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @POST
    @Path("changepassword")
    @Produces({"text/plain", "application/json"})
    public String cambiarClave(@FormParam("idCliente") Integer idCliente, @FormParam("claveAnterior") String claveAnterior, @FormParam("claveNueva") String claveNueva, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Clientes> qry;

            qry = getEntityManager().createNamedQuery("Clientes.findByIdClientes", Clientes.class);
            qry.setParameter("idClientes", idCliente);

            Clientes c = qry.getSingleResult();
            if (c.getContraseniaCliente().equals(claveAnterior)) {
                c.setContraseniaCliente(claveNueva);
                super.edit(c);
                return "true";
            } else {
                return "clave";
            }
        } else {
            return "false";
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Clientes entity) {
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
    public Clientes find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Clientes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Clientes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
