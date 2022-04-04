package com.example.clase3gtics.entity;

import javax.persistence.*;

@Entity
@Table(name = "territories")
public class Territory {
    @Id
    @Column(name = "territoryid", nullable = false, length = 20)
    private String id;

    @Column(name = "territorydescription", nullable = false, length = 50)
    private String territorydescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regionid", nullable = false)
    private Region regionid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritorydescription() {
        return territorydescription;
    }

    public void setTerritorydescription(String territorydescription) {
        this.territorydescription = territorydescription;
    }

    public Region getRegionid() {
        return regionid;
    }

    public void setRegionid(Region regionid) {
        this.regionid = regionid;
    }
}