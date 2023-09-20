package com.ubots.relationshipCenter.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ubots.relationshipCenter.entities.enums.CalledStatus;
import com.ubots.relationshipCenter.entities.enums.CalledType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_called")
public class Called implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private Attendant attendant;

    private Integer type;
    private String title;
    private String description;
    private Integer status;

    public Called() {
    }

    public Called(Long id, Instant moment, Attendant attendant, CalledType type, String title, String description, CalledStatus status) {
        this.id = id;
        this.moment = moment;
        this.attendant = attendant;
        setType(type);
        this.title = title;
        this.description = description;
        setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public CalledType getType() {
        return CalledType.valueOf(type);
    }

    public void setType(CalledType type) {
        if(type != null) this.type = type.getCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalledStatus getStatus() {
        return CalledStatus.valueOf(status);
    }

    public void setStatus(CalledStatus status) {
        if(status != null) this.status = status.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Called called = (Called) o;
        return Objects.equals(id, called.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
