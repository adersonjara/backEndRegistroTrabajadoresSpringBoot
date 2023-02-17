package com.construccionesayn.apiregistrotrabajadores.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.construccionesayn.apiregistrotrabajadores.models.dao.ITrabajadorDao;
import com.construccionesayn.apiregistrotrabajadores.models.entity.Trabajador;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

	@Autowired
	private ITrabajadorDao trabajadorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	@Transactional
	public Trabajador save(Trabajador trabajador) {
		return trabajadorDao.save(trabajador);

	}

	@Override
	@Transactional(readOnly = true)
	public Trabajador findById(Long id) {
		return trabajadorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		trabajadorDao.deleteById(id);
	}

}
