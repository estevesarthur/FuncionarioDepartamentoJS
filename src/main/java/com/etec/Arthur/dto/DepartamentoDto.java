package com.etec.Arthur.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.etec.Arthur.model.Departamento;

public class DepartamentoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	Long id;
	String descricao;
	
	public DepartamentoDto() {
		
	}

	public DepartamentoDto(Departamento departamento) {
		this.descricao = departamento.getDescricao();
	}

	public DepartamentoDto(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Departamento converterParaObjeto() {
		return new Departamento();
	}

	public static List<DepartamentoDto> converterParaListaObjeto(List<Departamento> departamento) {
		return departamento.stream().map(DepartamentoDto::new).collect(Collectors.toList());
	}
	
}
