package com.ocorteiz.comex.model;

public class Cliente {

    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(ClienteDTO dadosCliente) {
        this.id = dadosCliente.id();
        this.nome = dadosCliente.nome();
        this.cpf = dadosCliente.cpf();
        this.email = dadosCliente.email();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


