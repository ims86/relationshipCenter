package com.ubots.relationshipCenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubots.relationshipCenter.entities.enums.CalledType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_attendant")
public class Attendant implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer calledType;

    public Attendant() {
    }

    public Attendant(Long id, String name, CalledType calledType) {
        this.id = id;
        this.name = name;
        setCalledType(calledType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CalledType getCalledType() {
        return CalledType.valueOf(calledType);
    }

    public void setCalledType(CalledType calledType) {
        if(calledType != null) this.calledType = calledType.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendant attendant = (Attendant) o;
        return Objects.equals(id, attendant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
