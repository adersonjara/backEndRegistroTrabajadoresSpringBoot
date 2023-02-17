package com.construccionesayn.apiregistrotrabajadores.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public ResponseEntity<?> index() {
		
		List<Trabajador> trabajadores = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			trabajadores = trabajadorService.findAll();
		}catch(DataAccessException e) {
			response.put("mensaje", "No se encontró los recursos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("statuscode", "500");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se econtraron los recursos con éxito!");
		response.put("error", null);
		response.put("statuscode", "200");
		response.put("data", trabajadores);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

	@GetMapping("/trabajadores/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Trabajador trabajador = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			trabajador = trabajadorService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "No se encontró el recurso.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("statuscode", "500");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(trabajador == null) {
			response.put("mensaje", "No se encontró el recurso.");
			response.put("error", "No se encontró el recurso.");
			response.put("statuscode", "404");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Se econtró el recurso con éxito!");
		response.put("error", null);
		response.put("statuscode", "200");
		response.put("data", trabajador);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

	@PostMapping("/trabajadores")
	public ResponseEntity<?> create(@RequestBody Trabajador trabajador) {
		
		Trabajador trabajadorNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			trabajadorNuevo = trabajadorService.save(trabajador);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el recurso.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("statuscode", "500");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El recurso se registró correctamente!");
		response.put("error", null);
		response.put("statuscode", "201");
		response.put("data", trabajadorNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/trabajadores/{id}")
	public ResponseEntity<?> update(@RequestBody Trabajador trabajador, @PathVariable Long id) {
		
		Trabajador trabajadorActual = this.trabajadorService.findById(id);
		Trabajador trabajadorActualizado = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(trabajadorActual == null) {
			response.put("mensaje", "No se pudo encontrar el recurso.");
			response.put("error", "No se encontró el recurso.");
			response.put("statuscode", "404");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			trabajadorActual.setNombres(trabajador.getNombres());
			trabajadorActual.setApellidos(trabajador.getApellidos());
			trabajadorActual.setEmail(trabajador.getEmail());
			trabajadorActual.setCelular(trabajador.getCelular());
			trabajadorActual.setDireccion(trabajador.getDireccion());
			trabajadorActual.setEdad(trabajador.getEdad());
			trabajadorActual.setDni(trabajador.getDni());
			
			trabajadorActualizado = trabajadorService.save(trabajadorActual);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar al recurso.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("statuscode", "500");
			response.put("data", null);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "El recurso se actualizó correctamente!");
		response.put("error", null);
		response.put("statuscode", "201");
		response.put("data", trabajadorActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/trabajadores/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			trabajadorService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al tratar de eliminar el recurso.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("statuscode", "500");
			response.put("data", null);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El recurso se eliminó con éxito!");
		response.put("error", null);
		response.put("statuscode", "200");
		response.put("data", null);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

}
