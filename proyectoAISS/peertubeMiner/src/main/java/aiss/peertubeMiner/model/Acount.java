package aiss.peertubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Acount")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Acount {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("acount_link")
    private String acount_link;

    @JsonProperty("picture_link")
    private String picture_link;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAcount_link() {
        return acount_link;
    }

    public void setAcount_link(String acount_link) {
        this.acount_link = acount_link;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }
    @Override
    public String toString() {
        return "Acount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acount_link='" + acount_link + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }
}
