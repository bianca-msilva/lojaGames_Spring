package com.generation.lojagames.model;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Atributo Nome é obrigatório!")
	@Size(min = 4, message = "O Nome deve ter no mínimo 4 caracteres!")
	private String nome;
	
	@NotBlank(message = "O Atributo Usuário é obrigatório!")
	@Email(message = "O Atributo Usuário deve ser um email válido!")
	private String usuario;  // email 
	
	@NotBlank(message = "O Atributo Senha é obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha; 
	
	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
	private String foto;
	
	private String descricao;
	
	@NotNull(message = "O Atributo Data de Nascimento é obrigatório!")
	private LocalDate dataNasc;

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	} 
	
	
	
}
