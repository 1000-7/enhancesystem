package org.whusim.enhancesystem.bean;

public class Tag_Attributes{
    private String tag;
    private String attribute;

    public Tag_Attributes() {
    }

    public Tag_Attributes(String tag, String attribute) {
        this.tag = tag;
        this.attribute = attribute;
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