package br.unitins.topicos1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Produto extends PanacheEntity {
    @Column(nullable = false)
    public String nome;

    public String descricao;

    @Column(nullable = false)
    public Double preco;

    @Column(nullable = false)
    public Integer estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    public Categoria categoria;
}