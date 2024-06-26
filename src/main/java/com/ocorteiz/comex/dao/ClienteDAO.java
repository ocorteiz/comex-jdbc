package com.ocorteiz.comex.dao;

import com.ocorteiz.comex.exception.ClienteNaoEncontradoException;
import com.ocorteiz.comex.model.Cliente;
import com.ocorteiz.comex.model.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ClienteDAO {

    private final Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void save(ClienteDTO dadosCliente) {

        String sql = "INSERT INTO clientes (nome, cpf, email) VALUES (?, ?, ?)";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, dadosCliente.nome());
            preparedStatement.setString(2, dadosCliente.cpf());
            preparedStatement.setString(3, dadosCliente.email());

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Set<Cliente> list() {
        Set<Cliente> clientes = new HashSet<>();
        String sql = "SELECT * FROM clientes";

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String nome = rs.getString(2);
                String cpf = rs.getString(3);
                String email = rs.getString(4);

                ClienteDTO dadosCliente = new ClienteDTO(id, nome, cpf, email);
                Cliente cliente = new Cliente(dadosCliente);

                clientes.add(cliente);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public void update(ClienteDTO dadosCliente) {
        String sqlSelect = "SELECT nome, cpf, email FROM clientes WHERE id = ?";
        String sqlUpdate = "UPDATE clientes SET nome = ?, cpf = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setLong(1, dadosCliente.id());
            ResultSet rs = psSelect.executeQuery();

            String nomeAtual;
            String cpfAtual;
            String emailAtual;

            if (rs.next()) {
                nomeAtual = rs.getString("nome");
                cpfAtual = rs.getString("cpf");
                emailAtual = rs.getString("email");
            } else {
                psSelect.close();
                rs.close();
                conn.close();
                throw new ClienteNaoEncontradoException("Cliente com ID " + dadosCliente.id() + " não existe.");
            }

            psSelect.close();
            rs.close();

            String novoNome = dadosCliente.nome() != null && !dadosCliente.nome().isEmpty() ? dadosCliente.nome() : nomeAtual;
            String novoCpf = dadosCliente.cpf() != null && !dadosCliente.cpf().isEmpty() ? dadosCliente.cpf() : cpfAtual;
            String novoEmail = dadosCliente.email() != null && !dadosCliente.email().isEmpty() ? dadosCliente.email() : emailAtual;

            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setString(1, novoNome);
            psUpdate.setString(2, novoCpf);
            psUpdate.setString(3, novoEmail);
            psUpdate.setLong(4, dadosCliente.id());

            psUpdate.executeUpdate();
            psUpdate.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        String sqlCheck = "SELECT COUNT(*) FROM clientes WHERE id = ?";
        String sqlDelete = "DELETE FROM clientes WHERE id = ?";

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
                    System.out.println("Cliente apagado com sucesso");
                }

                ps.close();
                rs.close();
                conn.close();
            } else {
                throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não existe.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
