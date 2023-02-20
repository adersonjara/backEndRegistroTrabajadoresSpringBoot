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
	
	@NotEmpty(message = "Los nombres son requeridos.")
	@Size(min = 2,max = 120, message = "Los nombres deben tener entre 2 y 120 caracteres.")
	@Column(nullable = false,length = 120)
	private String nombres;
	
	@NotEmpty(message = "Los apellidos son requeridos.")
	@Size(min = 2,max = 120, message = "Los apellidos deben tener entre 2 y 120 caracteres.")
	@Column(nullable = false,length = 120)
	private String apellidos;
	
	@NotEmpty(message = "El email es requerido.")
	@Email(message = "El email debe ser válido.")
	@Size(min = 5,max = 255, message = "El email debe tener entre 5 y 255 caracteres.")
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(length = 9)
	@Size(min = 9,max = 9, message = "El celular deben tener 9 caracteres.")
	private String celular;
	
	@Column(length = 3)
	@Size(min = 1,max = 3, message = "La edad debe ser válida.")
	private String edad;
	
	@Size(min = 4,max = 255, message = "La direccion debe tener entre 4 y 255 caracteres.")
	private String direccion;
	
	@NotEmpty(message = "El dni es requerido.")
	@Column(unique = true,nullable = false,length = 8)
	@Size(min = 8,max = 8, message = "El dni deben tener 8 caracteres.")
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
