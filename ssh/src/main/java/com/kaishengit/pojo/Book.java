package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    @ManyToOne(fetch =FetchType.EAGER )
    @JoinColumn(name = "aconter_id")
    private Aconter aconter;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Aconter getAconter() {
        return aconter;
    }

    public void setAconter(Aconter aconter) {
        this.aconter = aconter;
    }
}
