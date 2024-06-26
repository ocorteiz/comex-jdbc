package com.ocorteiz.comex.model;

public class Categoria {

    Long id;
    String nome;
    String descricao;

    public Categoria(CategoriaDTO dadosCategoria) {
        this.id = dadosCategoria.id();
        this.nome = dadosCategoria.nome();
        this.descricao = dadosCategoria.descricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
