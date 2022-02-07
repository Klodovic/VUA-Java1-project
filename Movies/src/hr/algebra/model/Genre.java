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
public class Genre {
    @XmlElement(name = "idgenre")
    private int idGenre;
    @XmlElement(name = "genrename")
    private String genreName;


    public Genre(String genreName) {
        this.genreName = genreName;
    }

    

    public Genre(int idGenre, String genreName) {
        this.idGenre = idGenre;
        this.genreName = genreName;
    }

    

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(genreName);
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
        Genre other = (Genre) obj;
        return Objects.equals(genreName, other.genreName);
    }
    
    
    @Override
    public String toString() {
        return genreName;
    }
    
    
}
