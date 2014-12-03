/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities.service;

import com.unl.banco.entities.ControlApp;
import com.unl.banco.entities.Cuentas;
import com.unl.banco.entities.Historiales;
import com.unl.banco.entities.Invitados;
import com.unl.banco.entities.Transacciones;
import java.util.Date;
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
@Path("com.unl.banco.entities.cuentas")
public class CuentasFacadeREST extends AbstractFacade<Cuentas> {
    @PersistenceContext(unitName = "BancoEcologicoPU")
    private EntityManager em;

    public CuentasFacadeREST() {
        super(Cuentas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Cuentas entity) {
        super.create(entity);
    }

    @POST
    @Path("cuentas")
    @Produces({"application/json", "application/json"})
    public List<Cuentas> getCuentas(@FormParam("idCliente") Integer idCliente, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Cuentas> qry;

            qry = getEntityManager().createQuery("SELECT c FROM Cuentas c WHERE c.idClientes.idClientes = :idCliente", Cuentas.class);
            qry.setParameter("idCliente", idCliente);

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
    @Path("deposito-retiro")
    @Produces({"text/plain", "application/json"})
    public String depositoRetiro(@FormParam("idCuenta") Integer idCuenta, @FormParam("saldoCuenta") Double saldoCuenta, @FormParam("idTipoTransaccion") Integer idTipoTransaccion, @FormParam("cantidad") Double cantidad, @FormParam("codigoBarra") String codigoBarra, @FormParam("clave") String clave, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Cuentas> qry;

            qry = getEntityManager().createNamedQuery("Cuentas.findByIdCuenta", Cuentas.class);
            qry.setParameter("idCuenta", idCuenta);

            Cuentas c = qry.getSingleResult();
            if (idTipoTransaccion == 2) {
                if (c.getIdClientes().getContraseniaCliente().equals(clave)) {
                    /*c.setSaldoCuenta(saldoCuenta);
                    super.edit(c);*/
                    getEntityManager().persist(new Historiales(codigoBarra, cantidad, -1, false, new Date(), c.getIdClientes(), c, new Transacciones(idTipoTransaccion)));
                    return "true";
                } else {
                    return "clave";
                }
            } else {
                /*c.setSaldoCuenta(saldoCuenta);
                super.edit(c);*/
                getEntityManager().persist(new Historiales(codigoBarra, cantidad, -1, false, new Date(), c.getIdClientes(), c, new Transacciones(idTipoTransaccion)));
                return "true";
            }
        } else {
            return "false";
        }
    }

    @POST
    @Path("depositoexterno")
    @Produces({"text/plain", "application/json"})
    public String depositoExterno(@FormParam("idInvitado") Integer idInvitado, @FormParam("ciPersona") String ciPersona, @FormParam("numeroCuenta") String numeroCuenta, @FormParam("idTipoTransaccion") Integer idTipoTransaccion, @FormParam("cantidad") Double cantidad, @FormParam("codigoBarra") String codigoBarra, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Cuentas> qry;
            TypedQuery<Cuentas> qryCedula;
            TypedQuery<Invitados> qryInvi;

            qry = getEntityManager().createNamedQuery("Cuentas.findByNumeroCuenta", Cuentas.class);
            qry.setParameter("numeroCuenta", numeroCuenta);

            qryCedula = getEntityManager().createQuery("SELECT c FROM Cuentas c WHERE c.idClientes.idPersona.ciPersona = :ciPersona AND c.numeroCuenta = :numeroCuenta", Cuentas.class);
            qryCedula.setParameter("ciPersona", ciPersona);
            qryCedula.setParameter("numeroCuenta", numeroCuenta);

            try {
                Cuentas c = qry.getSingleResult();
                
                try {
                    Cuentas cCedula = qryCedula.getSingleResult();
                    /*c.setSaldoCuenta(c.getSaldoCuenta() + cantidad);
                    super.edit(c);*/
                    getEntityManager().persist(new Historiales(codigoBarra, cantidad, idInvitado, false, new Date(), c.getIdClientes(), c, new Transacciones(idTipoTransaccion)));
                    return "true";
                } catch (NoResultException e) {
                    return "cedula";
                }
            } catch (NoResultException e) {
                return "cuenta";
            }
        } else {
            return "false";
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Cuentas entity) {
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
    public Cuentas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Cuentas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Cuentas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
