/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Next Design
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Director {
    @XmlElement(name = "iddirector")
    private int idDirector;
    @XmlElement(name = "directorname")
    private String directorName;

    public Director(String directorName) {
        this.directorName = directorName;
    }

    public Director(int idDirector, String directorName) {
        this.idDirector = idDirector;
        this.directorName = directorName;
    }
    

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    
    
    
    @Override
    public int hashCode() {
        return Objects.hash(directorName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Director other = (Director) obj;
        return Objects.equals(directorName, other.directorName);
    }

    @Override
    public String toString() {
        return directorName;
    }
}
