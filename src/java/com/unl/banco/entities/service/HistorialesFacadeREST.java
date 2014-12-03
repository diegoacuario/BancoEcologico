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
import com.unl.banco.entities.Personas;
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
@Path("com.unl.banco.entities.historiales")
public class HistorialesFacadeREST extends AbstractFacade<Historiales> {

    @PersistenceContext(unitName = "BancoEcologicoPU")
    private EntityManager em;

    public HistorialesFacadeREST() {
        super(Historiales.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Historiales entity) {
        super.create(entity);
    }

    @POST
    @Path("autorizacion")
    @Produces({"text/plain", "application/json"})
    public String autorizacion(@FormParam("idCuenta") Integer idCuenta, @FormParam("clave") String clave, @FormParam("cedulaInvitado") String cedulaInvitado, @FormParam("cantidad") Double cantidad, @FormParam("codigoBarra") String codigoBarra, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Cuentas> qry;
            TypedQuery<Invitados> qryP;

            qry = getEntityManager().createNamedQuery("Cuentas.findByIdCuenta", Cuentas.class);
            qry.setParameter("idCuenta", idCuenta);

            qryP = getEntityManager().createQuery("SELECT i FROM Invitados i WHERE i.idPersona.ciPersona = :ciPersona", Invitados.class);
            qryP.setParameter("ciPersona", cedulaInvitado);

            Cuentas c = qry.getSingleResult();
            if (c.getIdClientes().getContraseniaCliente().equals(clave)) {
                try {
                    Invitados p = qryP.getSingleResult();

                    super.create(new Historiales(codigoBarra, cantidad, p.getIdInvitado(), false, new Date(), c.getIdClientes(), c, new Transacciones(3)));
                    return "true";
                } catch (NoResultException e) {
                    return "cedula";
                }
            } else {
                return "clave";
            }
        } else {
            return "false";
        }
    }

    @POST
    @Path("historialCliente")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public String historialCliente(@FormParam("idCliente") Integer idCliente, @FormParam("usuario_app") String usuarioApp, @FormParam("contrasenia_app") String claveApp) {
        TypedQuery<ControlApp> qryC;
        qryC = getEntityManager().createNamedQuery("ControlApp.findByIdControl", ControlApp.class);
        qryC.setParameter("idControl", 1);
        ControlApp auxCA = qryC.getSingleResult();
        if (usuarioApp.equals(auxCA.getUsuario()) && claveApp.equals(auxCA.getClave())) {
            TypedQuery<Historiales> qry;

            qry = getEntityManager().createQuery("SELECT h FROM Historiales h WHERE h.idClientes.idClientes = :idCliente", Historiales.class);
            qry.setParameter("idCliente", idCliente);

            List<Historiales> lh = null;
            try {
                lh = qry.getResultList();
                String dato = "[";
                for (Historiales historiales : lh) {
                    dato += "{numCuenta: '" + historiales.getIdCuenta().getNumeroCuenta() + "',";
                    dato += "codbarras: '" + historiales.getCodbarrasTransacción() + "',";
                    dato += "nombreTransaccion: '" + historiales.getIdTransaccion().getNombreTransaccion() + "',";
                    dato += "fechaRegistro: '" + historiales.getFechaRegistroNotificacion() + "',";
                    dato += "estadoCobro: '" + historiales.getEstadoCobro() + "'},";
                }
                if (dato.length() > 1) {
                    dato = dato.substring(0, dato.length() - 1);
                }
                dato += "]";
                return dato;
            } catch (NoResultException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @GET
    @Path("historialCliente={idCliente}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public String historialCliente(@PathParam("idCliente") Integer idCliente) {
        TypedQuery<Historiales> qry;

        qry = getEntityManager().createQuery("SELECT h FROM Historiales h WHERE h.idClientes.idClientes = :idCliente", Historiales.class);
        qry.setParameter("idCliente", idCliente);

        List<Historiales> lh = null;
        try {
            lh = qry.getResultList();
            String dato = "[";
            for (Historiales historiales : lh) {
                dato += "{numCuenta: '" + historiales.getIdCuenta().getNumeroCuenta() + "',";
                dato += "codbarras: '" + historiales.getCodbarrasTransacción() + "',";
                dato += "nombreTransaccion: '" + historiales.getIdTransaccion().getNombreTransaccion() + "',";
                dato += "fechaRegistro: '" + historiales.getFechaRegistroNotificacion() + "'},";
            }
            if (dato.length() > 1) {
                dato = dato.substring(0, dato.length() - 1);
            }
            dato += "]";
            return dato;
        } catch (NoResultException e) {
            return null;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Historiales entity) {
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
    public Historiales find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("get={code}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public String findByCode(@PathParam("code") String code) {
        TypedQuery<Historiales> qry;
        TypedQuery<Invitados> qryInv;

        qry = getEntityManager().createNamedQuery("Historiales.findByCodbarrasTransacci\u00f3n", Historiales.class);
        qry.setParameter("codbarrasTransacci\u00f3n", code);
        try {
            Historiales h = qry.getSingleResult();
            if (h.getEstadoCobro()) {
                return "{failure: true, message: 'Ya se ha realizado una transacción con este código'}";
            } else {
                Invitados i;
                /*String personaInvitado = "";

                if (h.getIdInvitado() != -1) {
                    qryInv = getEntityManager().createNamedQuery("Invitados.findByIdInvitado", Invitados.class);
                    qryInv.setParameter("idInvitado", h.getIdInvitado());
                    i = qryInv.getSingleResult();
                    personaInvitado = i.getIdPersona().getApellidospPersona() + " " + i.getIdPersona().getNombresPersona();
                }*/
                String personaCuenta = h.getIdClientes().getIdPersona().getApellidospPersona()+ " "+h.getIdClientes().getIdPersona().getApellidosmPersona()+" "+h.getIdClientes().getIdPersona().getNombresPersona();

                String data = "data: {";
                data += "idTransaccion: " + h.getIdTransaccion().getIdTransaccion() + ",";
                data += "transaccion: '" + h.getIdTransaccion().getNombreTransaccion() + "',";
                data += "cantidad: " + h.getCantidadTransacción() + ",";
                data += "numeroCuenta: '" + h.getIdCuenta().getNumeroCuenta() + "',";
                data += "personaCuenta: '" + personaCuenta + "',";
                data += "tipoCuenta: '" + h.getIdCuenta().getTipoCuenta() + "'";
                data += "}";
                return "{success: true, " + data + "}";
            }
        } catch (NoResultException e) {
            return "{failure: true, message: 'No existe información del código'}";
        }
    }

    @GET
    @Path("set={code}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public String setByCode(@PathParam("code") String code) {
        TypedQuery<Historiales> qry;
        TypedQuery<Cuentas> qryC;

        qry = getEntityManager().createNamedQuery("Historiales.findByCodbarrasTransacci\u00f3n", Historiales.class);
        qry.setParameter("codbarrasTransacci\u00f3n", code);
        try {
            Historiales h = qry.getSingleResult();

            qryC = getEntityManager().createNamedQuery("Cuentas.findByIdCuenta", Cuentas.class);
            qryC.setParameter("idCuenta", h.getIdCuenta().getIdCuenta());
            Cuentas c = qryC.getSingleResult();

            if (h.getIdTransaccion().getIdTransaccion() == 1 || h.getIdTransaccion().getIdTransaccion() == 4) {
                c.setSaldoCuenta(c.getSaldoCuenta() + h.getCantidadTransacción());
                getEntityManager().persist(c);

                h.setEstadoCobro(true);
                h.setFechaCobro(new Date());
                super.edit(h);
                return "{success: true, message: 'Transacción Realizada Correctamente'}";
            } else {
                if (c.getSaldoCuenta() > h.getCantidadTransacción()) {
                    c.setSaldoCuenta(c.getSaldoCuenta() - h.getCantidadTransacción());
                    getEntityManager().persist(c);

                    h.setEstadoCobro(true);
                    h.setFechaCobro(new Date());
                    super.edit(h);
                    return "{success: true, message: 'Transacción Realizada Correctamente'}";
                } else {
                    return "{failure: true, message: 'La cuenta no tiene el saldo suficinte para retirar el monto deseado'}";
                }
            }
        } catch (NoResultException e) {
            return "{failure: true, message: 'No existe información del código'}";
        }
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Historiales> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Historiales> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
