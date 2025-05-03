package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@SequenceGenerator(name = "kit_seq_gen", sequenceName = "kit_seq", allocationSize = 1)
public class Kit extends DefaultEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kit_seq_gen")
    public Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @ManyToMany
    @JoinTable(
            name = "kit_produto",
            joinColumns = @JoinColumn(name = "kit_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}