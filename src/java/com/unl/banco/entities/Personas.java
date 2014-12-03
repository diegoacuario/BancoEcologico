/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KRADAC
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByIdPersona", query = "SELECT p FROM Personas p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Personas.findByCiPersona", query = "SELECT p FROM Personas p WHERE p.ciPersona = :ciPersona"),
    @NamedQuery(name = "Personas.findByNombresPersona", query = "SELECT p FROM Personas p WHERE p.nombresPersona = :nombresPersona"),
    @NamedQuery(name = "Personas.findByApellidospPersona", query = "SELECT p FROM Personas p WHERE p.apellidospPersona = :apellidospPersona"),
    @NamedQuery(name = "Personas.findByApellidosmPersona", query = "SELECT p FROM Personas p WHERE p.apellidosmPersona = :apellidosmPersona")})
public class Personas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ci_persona")
    private String ciPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombres_persona")
    private String nombresPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apellidosp_persona")
    private String apellidospPersona;
    @Size(max = 45)
    @Column(name = "apellidosm_persona")
    private String apellidosmPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Clientes> clientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Invitados> invitadosList;

    public Personas() {
    }

    public Personas(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Personas(Integer idPersona, String ciPersona, String nombresPersona, String apellidospPersona) {
        this.idPersona = idPersona;
        this.ciPersona = ciPersona;
        this.nombresPersona = nombresPersona;
        this.apellidospPersona = apellidospPersona;
    }

    public Personas(String ciPersona, String nombresPersona, String apellidospPersona, String apellidosmPersona) {
        this.ciPersona = ciPersona;
        this.nombresPersona = nombresPersona;
        this.apellidospPersona = apellidospPersona;
        this.apellidosmPersona = apellidosmPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getCiPersona() {
        return ciPersona;
    }

    public void setCiPersona(String ciPersona) {
        this.ciPersona = ciPersona;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setNombresPersona(String nombresPersona) {
        this.nombresPersona = nombresPersona;
    }

    public String getApellidospPersona() {
        return apellidospPersona;
    }

    public void setApellidospPersona(String apellidospPersona) {
        this.apellidospPersona = apellidospPersona;
    }

    public String getApellidosmPersona() {
        return apellidosmPersona;
    }

    public void setApellidosmPersona(String apellidosmPersona) {
        this.apellidosmPersona = apellidosmPersona;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @XmlTransient
    public List<Invitados> getInvitadosList() {
        return invitadosList;
    }

    public void setInvitadosList(List<Invitados> invitadosList) {
        this.invitadosList = invitadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unl.banco.entities.Personas[ idPersona=" + idPersona + " ]";
    }
    
}
