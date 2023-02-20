package com.construccionesayn.apiregistrotrabajadores.models.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;

public interface ITrabajadorDao extends CrudRepository<Trabajador, Long> {
	
	Optional<Trabajador> findByEmail(@Param("email") String email);
	
	Optional<Trabajador> findByDni(@Param("dni") String dni);
	
}
