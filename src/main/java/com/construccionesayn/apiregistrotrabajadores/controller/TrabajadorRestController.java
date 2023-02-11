package com.construccionesayn.apiregistrotrabajadores.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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

import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;
import com.construccionesayn.apiregistrotrabajadores.models.service.ITrabajadorService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TrabajadorRestController {

	@Autowired
	private ITrabajadorService trabajadorService;

	@GetMapping("/trabajadores")
	public List<Trabajador> index() {
		return this.trabajadorService.findAll();
	}

	@GetMapping("/trabajadores/{id}")
	public Trabajador show(@PathVariable Long id) {
		return this.trabajadorService.findById(id);
	}

	@PostMapping("/trabajadores")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajador create(@RequestBody @Valid Trabajador trabajador) {
		trabajador.setCreateAt(new Date());
		this.trabajadorService.save(trabajador);
		return trabajador;
	}

	@PutMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajador update(@RequestBody @Valid Trabajador trabajador, @PathVariable Long id) {
		Trabajador trabajadorActual = this.trabajadorService.findById(id);
		trabajadorActual.setNombres(trabajador.getNombres());
		trabajadorActual.setApellidos(trabajador.getApellidos());
		trabajadorActual.setEmail(trabajador.getEmail());
		trabajadorActual.setCelular(trabajador.getCelular());
		trabajadorActual.setDireccion(trabajador.getDireccion());
		trabajadorActual.setEdad(trabajador.getEdad());
		this.trabajadorService.save(trabajadorActual);
		return trabajadorActual;
	}

	@DeleteMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Trabajador trabajadorActual = this.trabajadorService.findById(id);
		this.trabajadorService.delete(trabajadorActual);
	}

}
