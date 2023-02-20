package com.construccionesayn.apiregistrotrabajadores.models.service;

import java.util.List;
import java.util.Optional;
import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;

public interface ITrabajadorService {

	public List<Trabajador> findAll();

	public Trabajador save(Trabajador trabajador);

	public Trabajador findById(Long id);

	public void delete(Long id);
	
	Optional<Trabajador> findByEmail(String email);
	
	Optional<Trabajador> findByDni(String dni);

}
