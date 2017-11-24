package org.whusim.enhancesystem.bean;

import java.util.List;

public class EntityJson {
    private String entity;
    private List<Tag_Attributes>attributes;

    public EntityJson() {
    }

    public EntityJson(String entity, List<Tag_Attributes> attributes) {
        this.entity = entity;
        this.attributes = attributes;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<Tag_Attributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Tag_Attributes> attributes) {
        this.attributes = attributes;
    }
}
