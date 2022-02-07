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
public class Actor {
    @XmlElement(name = "idactor")
    private int idActor;
    @XmlElement(name = "actorname")
    private String actorName;


    public Actor(String actorName) {
        this.actorName = actorName;
    }

    public Actor(int idActor, String actorName) {
        this.idActor = idActor;
        this.actorName = actorName;
    }
    
    

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName);
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
        Actor other = (Actor) obj;
        return Objects.equals(actorName, other.actorName);
    }
    

    @Override
    public String toString() {
        return actorName;
    }
    
    
}
