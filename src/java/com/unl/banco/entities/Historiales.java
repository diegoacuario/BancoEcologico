/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KRADAC
 */
@Entity
@Table(name = "historiales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historiales.findAll", query = "SELECT h FROM Historiales h"),
    @NamedQuery(name = "Historiales.findByIdHistorial", query = "SELECT h FROM Historiales h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "Historiales.findByCodbarrasTransacci\u00f3n", query = "SELECT h FROM Historiales h WHERE h.codbarrasTransacci\u00f3n = :codbarrasTransacci\u00f3n"),
    @NamedQuery(name = "Historiales.findByCantidadTransacci\u00f3n", query = "SELECT h FROM Historiales h WHERE h.cantidadTransacci\u00f3n = :cantidadTransacci\u00f3n"),
    @NamedQuery(name = "Historiales.findByIdInvitado", query = "SELECT h FROM Historiales h WHERE h.idInvitado = :idInvitado"),
    @NamedQuery(name = "Historiales.findByEstadoCobro", query = "SELECT h FROM Historiales h WHERE h.estadoCobro = :estadoCobro"),
    @NamedQuery(name = "Historiales.findByMacDispositivo", query = "SELECT h FROM Historiales h WHERE h.macDispositivo = :macDispositivo"),
    @NamedQuery(name = "Historiales.findByFechaCobro", query = "SELECT h FROM Historiales h WHERE h.fechaCobro = :fechaCobro"),
    @NamedQuery(name = "Historiales.findByLugarCobro", query = "SELECT h FROM Historiales h WHERE h.lugarCobro = :lugarCobro"),
    @NamedQuery(name = "Historiales.findByIdCajero", query = "SELECT h FROM Historiales h WHERE h.idCajero = :idCajero"),
    @NamedQuery(name = "Historiales.findByFechaRegistroNotificacion", query = "SELECT h FROM Historiales h WHERE h.fechaRegistroNotificacion = :fechaRegistroNotificacion")})
public class Historiales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codbarras_transacci\u00f3n")
    private String codbarrasTransacción;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_transacci\u00f3n")
    private double cantidadTransacción;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_invitado")
    private int idInvitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cobro")
    private boolean estadoCobro;
    @Size(max = 20)
    @Column(name = "mac_dispositivo")
    private String macDispositivo;
    @Column(name = "fecha_cobro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobro;
    @Size(max = 100)
    @Column(name = "lugar_cobro")
    private String lugarCobro;
    @Column(name = "id_cajero")
    private Integer idCajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroNotificacion;
    @JoinColumn(name = "id_clientes", referencedColumnName = "id_clientes")
    @ManyToOne(optional = false)
    private Clientes idClientes;
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    @ManyToOne(optional = false)
    private Cuentas idCuenta;
    @JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion")
    @ManyToOne(optional = false)
    private Transacciones idTransaccion;

    public Historiales() {
    }

    public Historiales(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Historiales(Integer idHistorial, String codbarrasTransacción, double cantidadTransacción, int idInvitado, boolean estadoCobro, Date fechaRegistroNotificacion) {
        this.idHistorial = idHistorial;
        this.codbarrasTransacción = codbarrasTransacción;
        this.cantidadTransacción = cantidadTransacción;
        this.idInvitado = idInvitado;
        this.estadoCobro = estadoCobro;
        this.fechaRegistroNotificacion = fechaRegistroNotificacion;
    }

    public Historiales(String codbarrasTransacción, double cantidadTransacción, int idInvitado, boolean estadoCobro, Date fechaRegistroNotificacion, Clientes idClientes, Cuentas idCuenta, Transacciones idTransaccion) {
        this.codbarrasTransacción = codbarrasTransacción;
        this.cantidadTransacción = cantidadTransacción;
        this.idInvitado = idInvitado;
        this.estadoCobro = estadoCobro;
        this.fechaRegistroNotificacion = fechaRegistroNotificacion;
        this.idClientes = idClientes;
        this.idCuenta = idCuenta;
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getCodbarrasTransacción() {
        return codbarrasTransacción;
    }

    public void setCodbarrasTransacción(String codbarrasTransacción) {
        this.codbarrasTransacción = codbarrasTransacción;
    }

    public double getCantidadTransacción() {
        return cantidadTransacción;
    }

    public void setCantidadTransacción(double cantidadTransacción) {
        this.cantidadTransacción = cantidadTransacción;
    }

    public int getIdInvitado() {
        return idInvitado;
    }

    public void setIdInvitado(int idInvitado) {
        this.idInvitado = idInvitado;
    }

    public boolean getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(boolean estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public String getMacDispositivo() {
        return macDispositivo;
    }

    public void setMacDispositivo(String macDispositivo) {
        this.macDispositivo = macDispositivo;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getLugarCobro() {
        return lugarCobro;
    }

    public void setLugarCobro(String lugarCobro) {
        this.lugarCobro = lugarCobro;
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public Date getFechaRegistroNotificacion() {
        return fechaRegistroNotificacion;
    }

    public void setFechaRegistroNotificacion(Date fechaRegistroNotificacion) {
        this.fechaRegistroNotificacion = fechaRegistroNotificacion;
    }

    public Clientes getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Clientes idClientes) {
        this.idClientes = idClientes;
    }

    public Cuentas getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuentas idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Transacciones getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Transacciones idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiales)) {
            return false;
        }
        Historiales other = (Historiales) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unl.banco.entities.Historiales[ idHistorial=" + idHistorial + " ]";
    }
    
}
