package com.ocorteiz.comex.dao;

import com.ocorteiz.comex.exception.ClienteNaoEncontradoException;
import com.ocorteiz.comex.model.Produto;
import com.ocorteiz.comex.model.ProdutoDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDAO {

    private final Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    public void save(ProdutoDTO dadoProduto) {
        String sqlCheck = "SELECT EXISTS(SELECT 1 FROM categorias WHERE id = ?) as categoria_exist";
        String sqlProduto = "INSERT INTO produtos (nome, preco, categoria_id) VALUES (?,?,?)";

        boolean categoria_exist;

        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setLong(1, dadoProduto.categoria_id());
            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    categoria_exist = rs.getBoolean("categoria_exist");
                } else {
                    categoria_exist = false;
                }
            }
            psCheck.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!categoria_exist) {
            throw new ClienteNaoEncontradoException("Categoria nao encontrada");
        }

        try (PreparedStatement psSave = conn.prepareStatement(sqlProduto)) {
            psSave.setString(1, dadoProduto.nome());
            psSave.setBigDecimal(2, dadoProduto.preco());
            psSave.setLong(3, dadoProduto.categoria_id());

            psSave.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Produto> list() {
        Set<Produto> produtos = new HashSet<>();
        String sql = "SELECT * FROM produtos";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id =  rs.getLong("id");
                    String nome = rs.getString("nome");
                    BigDecimal preco = rs.getBigDecimal("preco");
                    Long categoria_id = rs.getLong("categoria_id");

                    ProdutoDTO dadosProduto = new ProdutoDTO(id, nome, preco, categoria_id);
                    Produto produto = new Produto(dadosProduto);

                    produtos.add(produto);
                }
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

}
