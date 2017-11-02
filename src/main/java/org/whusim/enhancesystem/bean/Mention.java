package org.whusim.enhancesystem.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mention {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mentionid;
    private String mentionvalue;

    public Mention(String mentionvalue) {
        this.mentionvalue = mentionvalue;
    }

    public int getMentionid() {
        return mentionid;
    }

    public void setMentionid(int mentionid) {
        this.mentionid = mentionid;
    }


    public String getMentionvalue() {
        return mentionvalue;
    }

    public Mention() {
    }

    public void setMentionvalue(String mentionvalue) {
        this.mentionvalue = mentionvalue;
    }
}
