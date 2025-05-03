package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ManyToOne;

@Entity
@SequenceGenerator(name = "produto_seq_gen", sequenceName = "produto_seq", allocationSize = 1)
public class Produto extends DefaultEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq_gen")
    public Long id;

    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;

    @ManyToOne
    private Categoria categoria;

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}