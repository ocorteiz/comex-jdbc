package com.ocorteiz.comex.model;

import java.math.BigDecimal;

public record ProdutoDTO (Long id, String nome, BigDecimal preco, Long categoria_id){
}
