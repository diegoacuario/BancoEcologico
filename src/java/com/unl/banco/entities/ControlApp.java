/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KRADAC
 */
@Entity
@Table(name = "control_app")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlApp.findAll", query = "SELECT c FROM ControlApp c"),
    @NamedQuery(name = "ControlApp.findByIdControl", query = "SELECT c FROM ControlApp c WHERE c.idControl = :idControl"),
    @NamedQuery(name = "ControlApp.findByUsuario", query = "SELECT c FROM ControlApp c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "ControlApp.findByClave", query = "SELECT c FROM ControlApp c WHERE c.clave = :clave")})
public class ControlApp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_control")
    private Integer idControl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "clave")
    private String clave;

    public ControlApp() {
    }

    public ControlApp(Integer idControl) {
        this.idControl = idControl;
    }

    public ControlApp(Integer idControl, String usuario, String clave) {
        this.idControl = idControl;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getIdControl() {
        return idControl;
    }

    public void setIdControl(Integer idControl) {
        this.idControl = idControl;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControl != null ? idControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlApp)) {
            return false;
        }
        ControlApp other = (ControlApp) object;
        if ((this.idControl == null && other.idControl != null) || (this.idControl != null && !this.idControl.equals(other.idControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unl.banco.entities.ControlApp[ idControl=" + idControl + " ]";
    }
    
}
