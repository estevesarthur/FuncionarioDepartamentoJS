package com.etec.Arthur.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

//import antlr.collections.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.Arthur.dto.DepartamentoDto;
import com.etec.Arthur.model.Departamento;
import com.etec.Arthur.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	private DepartamentoService departamentoService;
	
	public DepartamentoController(DepartamentoService departamentoService) {
		// TODO Auto-generated constructor stub
		this.departamentoService = departamentoService;
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Departamento>> listaDepartamentos(String descricao){
		if(descricao == null) {
			List<Departamento> listDep = departamentoService.listarDepartamentos();
			return new ResponseEntity<List<Departamento>>(listDep, HttpStatus.OK);
		} else {
			List<Departamento> listDep = departamentoService.buscarPorDescricao(descricao);
			return new ResponseEntity<List<Departamento>>(listDep, HttpStatus.OK);
		}
	}
	
	@CrossOrigin
	@PostMapping 
	public ResponseEntity<Departamento> salvar(@RequestBody DepartamentoDto form) {
		try {
			Departamento departamento = departamentoService.salvar(form.converterParaObjeto());
			return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
		}catch(EntityExistsException e){
			return new ResponseEntity<Departamento>(HttpStatus.CONFLICT);
		}
	}
	
	@CrossOrigin
	@PutMapping
	public ResponseEntity<Departamento> Atualizar(@RequestBody DepartamentoDto form) {
		if(form.getId()==null) {
			return new ResponseEntity<Departamento>(HttpStatus.NOT_FOUND);
		}
		Optional<Departamento> oldDepartamento = departamentoService.buscarPorId(form.getId());
		if(oldDepartamento.isPresent()) {
			try {
				Departamento departamento = oldDepartamento.get();
				departamento.setDescricao(form.getDescricao());
				departamentoService.alterar(departamento);
				return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
			} catch (EntityNotFoundException e){
				return new ResponseEntity<Departamento>(HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@DeleteMapping
	public ResponseEntity<Departamento> Delete(Long id) {
		Optional<Departamento> departamento = departamentoService.buscarPorId(id);
		if(departamento.isPresent()) {
			departamentoService.deletar(departamento.get());
			return new ResponseEntity<>(HttpStatus.OK);		
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}

