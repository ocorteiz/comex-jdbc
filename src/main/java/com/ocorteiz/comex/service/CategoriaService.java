package com.ocorteiz.comex.service;

import com.ocorteiz.comex.ConnectionFactory;
import com.ocorteiz.comex.dao.CategoriaDAO;
import com.ocorteiz.comex.model.Categoria;
import com.ocorteiz.comex.model.CategoriaDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class CategoriaService {

    public final ConnectionFactory connection = new ConnectionFactory();

    public void save(CategoriaDTO dadosCategoria) {
        try {
            Connection conn = connection.getConnection();
            new CategoriaDAO(conn).save(dadosCategoria);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Categoria> list(){
        try {
            Connection conn = connection.getConnection();
            Set<Categoria> categorias = new CategoriaDAO(conn).list();
            conn.close();
            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listForNome(){
        try {
            Connection conn = connection.getConnection();
            Set<String> categorias = new CategoriaDAO(conn).listForNome();
            conn.close();
            return categorias.stream()
                    .sorted()
                    .toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CategoriaDTO dadosCategoria) {
        try {
            Connection conn = connection.getConnection();
            new CategoriaDAO(conn).update(dadosCategoria);
            System.out.println("Categoria atualizada");
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try {
            Connection conn = connection.getConnection();
            new CategoriaDAO(conn).delete(id);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
