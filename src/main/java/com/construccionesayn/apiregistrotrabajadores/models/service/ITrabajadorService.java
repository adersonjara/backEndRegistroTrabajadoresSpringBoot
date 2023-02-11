package com.construccionesayn.apiregistrotrabajadores.models.service;

import java.util.List;

import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;

public interface ITrabajadorService {

	public List<Trabajador> findAll();

	public void save(Trabajador trabajador);

	public Trabajador findById(Long id);

	public void delete(Trabajador trabajador);

}
