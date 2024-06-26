package com.ocorteiz.comex.model;

import java.math.BigDecimal;

public class Produto {

    Long id;
    String nome;
    BigDecimal preco;
    Long categoria_id;

    public Produto(ProdutoDTO dadosProduto) {
        this.id = dadosProduto.id();
        this.nome = dadosProduto.nome();
        this.preco = dadosProduto.preco();
        this.categoria_id = dadosProduto.categoria_id();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria_id=" + categoria_id +
                '}';
    }
}
