package br.unitins.topicos1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Categoria extends PanacheEntity {

    @Column(nullable = false)
    public String nome;

}