package com.ocorteiz.comex.dao;

import com.ocorteiz.comex.exception.CategoriaNaoEncontradoException;
import com.ocorteiz.comex.exception.ClienteNaoEncontradoException;
import com.ocorteiz.comex.model.Categoria;
import com.ocorteiz.comex.model.CategoriaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CategoriaDAO {

    private final Connection conn;

    public CategoriaDAO(Connection conn) {
        this.conn = conn;
    }

    public void save(CategoriaDTO dadosCategoria) {
        String sqlSave = "INSERT INTO categorias (nome, descricao) VALUES (?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sqlSave);

            ps.setString(1, dadosCategoria.nome());
            ps.setString(2, dadosCategoria.descricao());

            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Categoria> list() {
        Set<Categoria> categorias = new HashSet<>();
        String sqlSelect = "SELECT * FROM categorias";

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                CategoriaDTO dadosCategoria = new CategoriaDTO(id, nome, descricao);
                Categoria categoria = new Categoria(dadosCategoria);

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }

    public Set<String> listForNome() {
        Set<String> categorias = new HashSet<>();
        String sqlSelect = "SELECT nome FROM categorias";

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                categorias.add(nome);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }

    public void update(CategoriaDTO dadosCategoria) {
        String sqlSelect = "SELECT descricao FROM categorias WHERE id = ?";
        String sqlUpdate = "UPDATE categorias SET descricao = ? WHERE id = ?";

        try {
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setLong(1, dadosCategoria.id());
            ResultSet rs = psSelect.executeQuery();

            String descricaoAtual;

            if (rs.next()) {
                descricaoAtual = rs.getString("descricao");
            } else {
                psSelect.close();
                rs.close();
                conn.close();
                throw new CategoriaNaoEncontradoException("Categoria com ID " + dadosCategoria.id() + " não existe.");
            }

            psSelect.close();
            rs.close();

            String descricaoNova = dadosCategoria.descricao() != null && !dadosCategoria.descricao().isEmpty() ? dadosCategoria.descricao() : descricaoAtual;

            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setString(1, descricaoNova);
            psUpdate.setLong(2, dadosCategoria.id());

            psUpdate.executeUpdate();
            psUpdate.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        String sqlCheck = "SELECT COUNT(*) FROM categorias WHERE id = ?";
        String sqlDelete = "DELETE FROM categorias WHERE id = ?";

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(sqlCheck);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next() && rs.getLong(1) > 0) {
                ps = conn.prepareStatement(sqlDelete);
                ps.setLong(1, id);
                int linhasAfetadas = ps.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Cliente apagado com sucsso");
                }

                ps.close();
                rs.close();
                conn.close();
            } else {
                throw new CategoriaNaoEncontradoException("Categoria com ID " + id + " não existe.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
