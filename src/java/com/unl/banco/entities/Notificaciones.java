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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KRADAC
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n"),
    @NamedQuery(name = "Notificaciones.findByIdNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.idNotificacion = :idNotificacion"),
    @NamedQuery(name = "Notificaciones.findByFechaCaducidad", query = "SELECT n FROM Notificaciones n WHERE n.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "Notificaciones.findByEstadoNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.estadoNotificacion = :estadoNotificacion"),
    @NamedQuery(name = "Notificaciones.findByEstadoCobro", query = "SELECT n FROM Notificaciones n WHERE n.estadoCobro = :estadoCobro")})
public class Notificaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_notificacion")
    private Integer idNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_notificacion")
    private boolean estadoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cobro")
    private boolean estadoCobro;
    @JoinColumn(name = "id_historial", referencedColumnName = "id_historial")
    @OneToOne(optional = false)
    private Historiales idHistorial;

    public Notificaciones() {
    }

    public Notificaciones(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificaciones(Integer idNotificacion, Date fechaCaducidad, boolean estadoNotificacion, boolean estadoCobro) {
        this.idNotificacion = idNotificacion;
        this.fechaCaducidad = fechaCaducidad;
        this.estadoNotificacion = estadoNotificacion;
        this.estadoCobro = estadoCobro;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(boolean estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public boolean getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(boolean estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public Historiales getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Historiales idHistorial) {
        this.idHistorial = idHistorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unl.banco.entities.Notificaciones[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
