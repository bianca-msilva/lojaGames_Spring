package com.generation.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produtos {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 65)
	@Size(min = 5, max = 65, message = "O Nome deve conter no mínimo 5 caracteres e, no máximo, 65 caracteres!")
	@NotBlank(message = "O Atributo Nome é obrigatório!")
	private String nome;
	
	@Column(length = 1000)
	@Size(min = 10, max = 1000, message = "O Descrição deve conter no mínimo 10 caracteres e, no máximo, 1000 caracteres!")
	@NotBlank(message = "O Atributo Descrição é obrigatório!")
	private String descricao;
	
	@Column(length = 255)
	@Size(min = 10, max = 255, message = "O Link (URL) da Foto deve conter no mínimo 10 caracteres!")
	private String foto;
	
	@Column(length = 255)
	@Size(min = 10, max = 255, message = "O Link (URL) do Vídeo deve conter no mínimo 10 caracteres!")
	private String video;

	
	private Boolean desconto;

	private Boolean usado;
	
	@Positive(message = "O Preço deve ser um valor positivo (>0)!")
	@NotNull(message = "O Atributo Preço é obrigatório!")
	private BigDecimal preco;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Boolean getDesconto() {
		return desconto;
	}

	public void setDesconto(Boolean desconto) {
		this.desconto = desconto;
	}

	public Boolean getUsado() {
		return usado;
	}

	public void setUsado(Boolean usado) {
		this.usado = usado;
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
