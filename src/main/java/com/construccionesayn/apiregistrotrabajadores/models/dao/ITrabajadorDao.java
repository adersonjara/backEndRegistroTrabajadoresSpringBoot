package com.construccionesayn.apiregistrotrabajadores.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;

public interface ITrabajadorDao extends CrudRepository<Trabajador, Long> {

}
