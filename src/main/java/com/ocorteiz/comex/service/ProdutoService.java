package com.ocorteiz.comex.service;

import com.ocorteiz.comex.ConnectionFactory;
import com.ocorteiz.comex.dao.ProdutoDAO;
import com.ocorteiz.comex.model.Produto;
import com.ocorteiz.comex.model.ProdutoDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class ProdutoService {

    private final ConnectionFactory connection = new ConnectionFactory();

    public void save(ProdutoDTO dadosProduto) {
        try {
            Connection conn = connection.getConnection();
            new ProdutoDAO(conn).save(dadosProduto);
            System.out.println("Produto salvo com sucesso!");
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Produto> list() {
        try {
            Connection conn = connection.getConnection();
            Set<Produto> produtos = new ProdutoDAO(conn).list();
            conn.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
