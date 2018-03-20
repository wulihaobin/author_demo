package com.nf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Echarts {
    @Id
    @GeneratedValue
    private Long id;
    private int shirt;
    private int sweater;
    private int chiffonShirt;
    private int pants;
    private int shoes;
    private int socks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShirt() {
        return shirt;
    }

    public void setShirt(int shirt) {
        this.shirt = shirt;
    }

    public int getSweater() {
        return sweater;
    }

    public void setSweater(int sweater) {
        this.sweater = sweater;
    }

    public int getChiffonShirt() {
        return chiffonShirt;
    }

    public void setChiffonShirt(int chiffonShirt) {
        this.chiffonShirt = chiffonShirt;
    }

    public int getPants() {
        return pants;
    }

    public void setPants(int pants) {
        this.pants = pants;
    }

    public int getShoes() {
        return shoes;
    }

    public void setShoes(int shoes) {
        this.shoes = shoes;
    }

    public int getSocks() {
        return socks;
    }

    public void setSocks(int socks) {
        this.socks = socks;
    }
}
