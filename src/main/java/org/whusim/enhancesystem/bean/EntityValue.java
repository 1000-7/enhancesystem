package org.whusim.enhancesystem.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class EntityValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int entityid;
    private int mentionid;
    private String entity;
    private String description;

    public EntityValue() {
    }


    public EntityValue(String entity, String description) {
        this.entity = entity;
        this.description = description;
    }

    public EntityValue(int mentionid, String entity, String description) {
        this.mentionid = mentionid;
        this.entity = entity;
        this.description = description;
    }

    public int getMentionid() {
        return mentionid;
    }

    public void setMentionid(int mentionid) {
        this.mentionid = mentionid;
    }

    public int getEntityid() {
        return entityid;
    }

    public void setEntityid(int entityid) {
        this.entityid = entityid;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
