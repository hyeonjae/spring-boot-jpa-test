package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class ItemEntity {
    @Id
    @Column(name = "item_id")
    Long id;

    @Column
    String name;

    public ItemEntity() {
    }

    public ItemEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
