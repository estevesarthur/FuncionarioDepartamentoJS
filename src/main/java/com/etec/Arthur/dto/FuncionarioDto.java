package com.etec.Arthur.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.etec.Arthur.model.Departamento;
import com.etec.Arthur.model.Funcionario;

public class FuncionarioDto implements Serializable {
	private static final long serialVersionUID = 1L;
	Long id;
	String nome;
	Double salario;
	Double comissao;
	Departamento departamento;
	
	public FuncionarioDto() {
		
	}
	
	public FuncionarioDto(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.salario = funcionario.getSalario();
		this.departamento = funcionario.getDepartamento();
	}
	
	public FuncionarioDto(String nome, Double salario, Double comissao, Departamento departamento) {
		this.nome = nome;
		this.salario = salario;
		this.comissao = comissao;
		this.departamento = departamento;
	}

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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Funcionario converterParaObjeto() {
		return new Funcionario(nome, salario, comissao, departamento);
	}

	public static List<FuncionarioDto> converterParaListaObjeto(List<Funcionario> funcionario){
		return funcionario.stream().map(FuncionarioDto::new).collect(Collectors.toList());
	}
}
