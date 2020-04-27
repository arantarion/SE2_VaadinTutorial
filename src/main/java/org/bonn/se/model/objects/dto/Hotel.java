package org.bonn.se.model.objects.dto;

import java.util.Objects;

//an Serializable denken!!!
public class Hotel implements java.io.Serializable {

    public Hotel (String name, Integer id, String ort, String desc) {
        this.name = name;
        this.id = id;
        this.ort = ort;
        this.desc = desc;
    }

    public Hotel() {

    }

    private String name;
    private Integer id;
    private String ort;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return getId().equals(hotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public Hotel clone() throws CloneNotSupportedException {
        return (Hotel) super.clone();
    }

    @Override
    public String toString() {
        return name + " " + ort;
    }

}
