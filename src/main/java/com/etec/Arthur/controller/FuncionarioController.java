package com.etec.Arthur.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

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

import com.etec.Arthur.dto.FuncionarioDto;
import com.etec.Arthur.model.Funcionario;
import com.etec.Arthur.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private FuncionarioService funcionarioService;
	
	public FuncionarioController(FuncionarioService funcionarioService){
		// TODO Auto-generated constructor stub
		this.funcionarioService = funcionarioService;
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Funcionario>> listaFuncionarios(String nome) {
		if(nome == null) {
			
			List<Funcionario> listFunc = funcionarioService.listarFuncionarios();
			return new ResponseEntity<List<Funcionario>>(listFunc, HttpStatus.OK);
		} else {
			List<Funcionario> listFunc = funcionarioService.buscarPorNome(nome);
			return new ResponseEntity<List<Funcionario>>(listFunc, HttpStatus.OK);
		}
	}
	
	@CrossOrigin
	@PostMapping 
	public ResponseEntity<Funcionario> salvar(@RequestBody FuncionarioDto form) {
		try {
			Funcionario funcionario = funcionarioService.salvar(form.converterParaObjeto());
			return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		}catch(EntityExistsException e){
			return new ResponseEntity<Funcionario>(HttpStatus.CONFLICT);
		}
	}
	
	@CrossOrigin
	@PutMapping
	public ResponseEntity<Funcionario> Atualizar(@RequestBody FuncionarioDto form) {
		if(form.getId()==null) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}
		Optional<Funcionario> oldFuncionario = funcionarioService.buscarPorId(form.getId());
		if(oldFuncionario.isPresent()) {
			try {
				Funcionario funcionario = oldFuncionario.get();
				funcionario.setNome(form.getNome());
				funcionario.setSalario(form.getSalario());
				funcionario.setDepartamento(form.getDepartamento());
				funcionarioService.alterar(funcionario);
				return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
			} catch (EntityNotFoundException e){
				return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@DeleteMapping
	public ResponseEntity<Funcionario> Delete(Long id) {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
		if(funcionario.isPresent()) {
			funcionarioService.deletar(funcionario.get());
			return new ResponseEntity<>(HttpStatus.OK);		
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}
