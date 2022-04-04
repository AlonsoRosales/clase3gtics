package com.example.clase3gtics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "regionid", nullable = false)
    private Integer id;

    @Column(name = "regiondescription", nullable = false, length = 50)
    private String regiondescription; //region_description

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegiondescription() {
        return regiondescription;
    }

    public void setRegiondescription(String regiondescription) {
        this.regiondescription = regiondescription;
    }
}