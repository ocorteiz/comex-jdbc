package com.ocorteiz.comex;

import com.ocorteiz.comex.model.Categoria;
import com.ocorteiz.comex.model.CategoriaDTO;
import com.ocorteiz.comex.service.CategoriaService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TestCategoria {

    public static void main(String[] args) {
        testSaveCategoria();
    }

    public static void testSaveCategoria() {
        CategoriaDTO dadosCategoria = new CategoriaDTO(null, "Informartica", "Produtos de informatica, semelhantes etc.");
        CategoriaService categoriaService = new CategoriaService();
        categoriaService.save(dadosCategoria);
    }

    public static void testListCategoria(){
        CategoriaService categoriaService = new CategoriaService();
        Set<Categoria> categorias = categoriaService.list();
        categorias.stream()
                .sorted(Comparator.comparing(Categoria::getId))
                .forEach(System.out::println);
    }

    public static void testListForNomeCategoria(){
        CategoriaService categoriaService = new CategoriaService();
        List<String> categoriasForNome = categoriaService.listForNome();
        categoriasForNome.forEach(System.out::println);
    }

    public static void testUpdateCategoria(){
        CategoriaDTO dadosCategoria = new CategoriaDTO(1L, null, "Produtos de informartica, semelhantes etc.");
        CategoriaService categoriaService = new CategoriaService();
        categoriaService.update(dadosCategoria);
    }

    public static void testDeleteCategoria() {
        CategoriaService categoriaService = new CategoriaService();
        categoriaService.delete(3L);
    }

}
