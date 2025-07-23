package com.placebra.edu.PP_3_1_2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.replaceAll("ROLE_", "");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {return true;}
        if (o == null || o.getClass() != getClass()) {return false;}

        Role role = (Role) o;
        return this.name.equals(role.getName());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
