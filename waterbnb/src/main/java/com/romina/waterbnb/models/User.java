package com.romina.waterbnb.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = " Por favor ingresa un nombre")
	@Size(min = 3, max = 30, message = "Nombre debe ser mayor a 3 caracteres y menor a 30")
	private String nombre;

	@NotBlank(message = " Por favor ingresa un apellido")
	@Size(min = 3, max = 30, message = "Apellido debe ser mayor a 3 caracteres y menor a 30")
	private String apellido;

	@NotBlank(message = " Por favor ingresa un correo electronico")
	@Email(message = "El correo ingresado no es correcto")
	private String email;

	@NotNull
	@Min(value = 1, message = "por favor, debe elegir  un rol")
	private Integer rol;

	@NotBlank(message = "Por favor, ingresa el password")
	@Size(min = 8, max = 64, message = "Password debe contener minimo 8 caracteres")
	private String password;

	@Transient
	@NotBlank(message = "Por favor confirma la contrasenia")
	@Size(min = 8, message = "Password debe contener minimo 8 caracteres")
	private String passwordConfirmation;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// Relacion 1:n hacia WaterModel
	@OneToMany(mappedBy = "organizador", fetch = FetchType.LAZY)
	private List<WaterModel> eventosOrganizados;

	// Relacion n:n hacia comentarios
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ComentarioModel> comentarios;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<WaterModel> getEventosOrganizados() {
		return eventosOrganizados;
	}

	public void setEventosOrganizados(List<WaterModel> eventosOrganizados) {
		this.eventosOrganizados = eventosOrganizados;
	}

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}

	// otros getters y setters removidos para resumir.
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}