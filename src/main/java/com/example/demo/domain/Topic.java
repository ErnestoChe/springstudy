package com.example.demo.domain;


import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String themeName;

    public Topic(String themeName){
        this.themeName = themeName;
    }

    public Topic() {
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
