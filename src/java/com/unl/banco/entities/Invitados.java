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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "invitados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitados.findAll", query = "SELECT i FROM Invitados i"),
    @NamedQuery(name = "Invitados.findByIdInvitado", query = "SELECT i FROM Invitados i WHERE i.idInvitado = :idInvitado"),
    @NamedQuery(name = "Invitados.findByContraseniaInvitado", query = "SELECT i FROM Invitados i WHERE i.contraseniaInvitado = :contraseniaInvitado")})
public class Invitados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_invitado")
    private Integer idInvitado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contrasenia_invitado")
    private String contraseniaInvitado;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;

    public Invitados() {
    }

    public Invitados(Integer idInvitado) {
        this.idInvitado = idInvitado;
    }

    public Invitados(Integer idInvitado, String contraseniaInvitado) {
        this.idInvitado = idInvitado;
        this.contraseniaInvitado = contraseniaInvitado;
    }

    public Invitados(String contraseniaInvitado, Personas idPersona) {
        this.contraseniaInvitado = contraseniaInvitado;
        this.idPersona = idPersona;
    }

    public Integer getIdInvitado() {
        return idInvitado;
    }

    public void setIdInvitado(Integer idInvitado) {
        this.idInvitado = idInvitado;
    }

    public String getContraseniaInvitado() {
        return contraseniaInvitado;
    }

    public void setContraseniaInvitado(String contraseniaInvitado) {
        this.contraseniaInvitado = contraseniaInvitado;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvitado != null ? idInvitado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitados)) {
            return false;
        }
        Invitados other = (Invitados) object;
        if ((this.idInvitado == null && other.idInvitado != null) || (this.idInvitado != null && !this.idInvitado.equals(other.idInvitado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unl.banco.entities.Invitados[ idInvitado=" + idInvitado + " ]";
    }
    
}
