package com.ocorteiz.comex.service;

import com.ocorteiz.comex.ConnectionFactory;
import com.ocorteiz.comex.dao.ClienteDAO;
import com.ocorteiz.comex.model.Cliente;
import com.ocorteiz.comex.model.ClienteDTO;

import java.sql.Connection;
import java.util.Set;

public class ClienteService {

    private final ConnectionFactory connection = new ConnectionFactory();

    public void save(ClienteDTO dadosCliente) {
        Connection conn = connection.getConnection();
        new ClienteDAO(conn).save(dadosCliente);
    }

    public Set<Cliente> list() {
        Connection conn = connection.getConnection();
        return new ClienteDAO(conn).list();
    }

    public void update(ClienteDTO dadosCliente) {
        Connection conn = connection.getConnection();
        new ClienteDAO(conn).update(dadosCliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void delete(Long id) {
        Connection conn = connection.getConnection();
        new ClienteDAO(conn).delete(id);
    }


}
