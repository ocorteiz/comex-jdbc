package com.ocorteiz.comex;

import com.ocorteiz.comex.model.Cliente;
import com.ocorteiz.comex.model.ClienteDTO;
import com.ocorteiz.comex.service.ClienteService;

import java.util.Comparator;
import java.util.Set;

public class TestCliente {

    public static void main(String[] args) {
        testDeleteCliente();
    }

    public static void testSaveCliente() {
        ClienteDTO dadosCliente = new ClienteDTO(null,"Caio Silva", "33344455566", "caio@caio.com");
        ClienteService clienteService = new ClienteService();
        clienteService.save(dadosCliente);
    }

    public static void testListCliente(){
        ClienteService clienteService = new ClienteService();
        Set<Cliente> clientes = clienteService.list();
        clientes.stream()
                .sorted(Comparator.comparing(Cliente::getId))
                .forEach(System.out::println);
    }

    public static void testUpdateCliente(){
        ClienteDTO dadosCliente = new ClienteDTO(3L,"Caio Silva", null, null);
        ClienteService clienteService = new ClienteService();
        clienteService.update(dadosCliente);
    }

    public static void testDeleteCliente(){
        ClienteService clienteService = new ClienteService();
        clienteService.delete(4L);
    }

}
