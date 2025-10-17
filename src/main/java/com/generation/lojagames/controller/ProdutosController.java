package com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produtos;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutosRepository;

import jakarta.validation.Valid;

@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll(){
		
		return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id){
		
		return produtosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());		
		
	}
	
	@GetMapping("/produto/descricao/{descricao}")
	public ResponseEntity<List<Produtos>> findAllByDescricao(@PathVariable String descricao){
		
		return ResponseEntity.ok(produtosRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@GetMapping("/desconto/{desconto}")
	public ResponseEntity<List<Produtos>> findAllByDesconto(@PathVariable Boolean desconto){
		
		return ResponseEntity.ok(produtosRepository.findAllByDesconto(desconto));
	}
	
	
	@GetMapping("/precoAbaixoDe/{preco}")
	public ResponseEntity<List<Produtos>> getAllByPrecoLess(@PathVariable BigDecimal preco){
	
		return ResponseEntity.ok(produtosRepository.findByPrecoLessThanEqualOrderByPrecoDesc(preco));
	}
	
	@GetMapping("/precoAcimaDe/{preco}")
	public ResponseEntity<List<Produtos>> getAllByPrecoGreater(@PathVariable BigDecimal preco){
	
		return ResponseEntity.ok(produtosRepository.findByPrecoGreaterThanEqualOrderByPrecoAsc(preco));
	}
	
	
	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto){
		
		if(categoriaRepository.existsById(produto.getCategoria().getId())) {
			produto.setId(null); 
			return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produto));
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A Categoria não existe", null);
	}
	
	
	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produto){
		
		if(produtosRepository.existsById(produto.getId())) {
			
			if(categoriaRepository.existsById(produto.getCategoria().getId())) {
				
				return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produto));  
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A Categoria não existe", null);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Produtos> produto = produtosRepository.findById(id);
		
		if(produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		produtosRepository.deleteById(id);
	}
	
}
