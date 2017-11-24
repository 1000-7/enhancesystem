package org.whusim.enhancesystem.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class EntityValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String entity;
    private String tag;
    private String attribute;

    public EntityValue() {
    }

    public EntityValue(String entity, String tag, String attribute) {
        this.entity = entity;
        this.tag = tag;
        this.attribute = attribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
