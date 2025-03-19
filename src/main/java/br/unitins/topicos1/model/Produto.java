package br.unitins.topicos1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Produto extends PanacheEntity {
    public String nome;
    public String descricao;
    public double preco;
    public int estoque;

    @Enumerated(EnumType.STRING)
    public CategoriaProduto categoria;
}


