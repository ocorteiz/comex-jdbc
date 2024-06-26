package com.ocorteiz.comex;

import com.ocorteiz.comex.model.Produto;
import com.ocorteiz.comex.model.ProdutoDTO;
import com.ocorteiz.comex.service.ProdutoService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;

public class TestProduto {

    public static void main(String[] args) {
        testListProduto();
    }

    public static void testSaveProduto() {
        ProdutoDTO dadosProduto = new ProdutoDTO(null, "Notebook", new BigDecimal("2000"), 2L);
        ProdutoService produtoService = new ProdutoService();
        produtoService.save(dadosProduto);
    }

    public static void testListProduto(){
        ProdutoService produtoService = new ProdutoService();
        Set<Produto> categorias = produtoService.list();
        categorias.stream()
                .sorted(Comparator.comparing(Produto::getId))
                .forEach(System.out::println);
    }
}
