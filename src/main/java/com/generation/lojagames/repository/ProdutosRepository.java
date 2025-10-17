package com.generation.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	public List<Produtos> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	public List<Produtos> findAllByDesconto(Boolean desconto);
	
	public List<Produtos> findByPrecoLessThanEqualOrderByPrecoDesc(BigDecimal preco);
	
	public List<Produtos> findByPrecoGreaterThanEqualOrderByPrecoAsc(BigDecimal preco);
}
