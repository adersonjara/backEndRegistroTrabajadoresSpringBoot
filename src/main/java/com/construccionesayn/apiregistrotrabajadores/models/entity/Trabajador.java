package com.construccionesayn.apiregistrotrabajadores.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "trabajadores")
public class Trabajador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 2,max = 120)
	@Column(nullable = false,length = 120)
	private String nombres;
	
	@NotEmpty
	@Size(min = 2,max = 120)
	@Column(nullable = false,length = 120)
	private String apellidos;
	
	@NotEmpty
	@Email
	@Size(min = 2,max = 255)
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(length = 9)
	@Size(min = 9,max = 9)
	private String celular;
	
	@Column(length = 3)
	@Size(min = 1,max = 3)
	private String edad;
	
	@Size(min = 4,max = 255)
	private String direccion;
	
	@NotEmpty
	@Column(unique = true,nullable = false,length = 8)
	@Size(min = 8,max = 8)
	private String dni;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private Date createAt;

	public Trabajador() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
