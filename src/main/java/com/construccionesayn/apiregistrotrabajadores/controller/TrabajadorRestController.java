package com.construccionesayn.apiregistrotrabajadores.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	public ResponseEntity<?> create(@Valid @RequestBody Trabajador trabajador, BindingResult result) {
		
		Trabajador trabajadorNuevo = null;
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> errores = new HashMap<>();
		
		if(trabajadorService.findByEmail(trabajador.getEmail()).isPresent()) {
			result.rejectValue("email", "error.trabajador","El email ya está registrado");
		}
		
		if(trabajadorService.findByDni(trabajador.getDni()).isPresent()) {
			result.rejectValue("dni", "error.trabajador","El dni ya está registrado");
		}
		
		if(result.hasErrors()) {
			
			/* Una forma de agregar los errores
			 * List<String> errores = new ArrayList<>(); for(FieldError err:
			 * result.getFieldErrors()) { errores.add(err.getDefaultMessage()); }
			 * response.put("error", errores); 
			 * return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			 */
			
			/* Forma N° 2 */			
			/*
			 * List<String> errores = result.getFieldErrors() .stream() .map(err ->
			 * err.getDefaultMessage()) .collect(Collectors.toList());
			 */
			
//			Forma 03 Para validar en el front end campo por campo
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), err.getDefaultMessage());
			});
			
			response.put("mensaje", "Valide correctamente la información del recurso.");
			response.put("error", errores);
			response.put("statuscode", "400");
			response.put("data", null);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			
		}
		
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
	public ResponseEntity<?> update(@Valid @RequestBody Trabajador trabajador, BindingResult result, @PathVariable Long id) {
		
		Trabajador trabajadorActual = this.trabajadorService.findById(id);
		Trabajador trabajadorActualizado = null;
		
		 //System.out.println("trabajadorActual:    "+);
		 //System.out.println("trabajadorParametro: "+trabajador.getEmail());
		
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> errores = new HashMap<>();
		
		if(trabajadorService.findByEmail(trabajador.getEmail()).isPresent() && !trabajadorActual.getEmail().equals(trabajador.getEmail()) ) {
			result.rejectValue("email", "error.trabajador","El email ya está registrado");
		}
		
		if(trabajadorService.findByDni(trabajador.getDni()).isPresent() && !trabajadorActual.getDni().equals(trabajador.getDni()) ) {
			result.rejectValue("dni", "error.trabajador","El dni ya está registrado");
		}
		
		if(result.hasErrors()) {
//			List<String> errores = result.getFieldErrors()
//									.stream()
//									.map(err -> err.getDefaultMessage())
//									.collect(Collectors.toList());
			
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), err.getDefaultMessage());
			});
			
			response.put("mensaje", "Valide correctamente la información del recurso.");
			response.put("error", errores);
			response.put("statuscode", "400");
			response.put("data", null);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			
		}
		
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
