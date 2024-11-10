package com.umbrella.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "features")
public class Feature {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "icon_code")
    private String iconCode;
    @Column(name = "title")
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
