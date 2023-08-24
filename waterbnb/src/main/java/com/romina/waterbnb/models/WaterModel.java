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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "piscinas")
public class WaterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = " Por favor ingresa un direccion")
	@Size(min = 3, message = "dIRECCION debe ser mayor a 3 caracteres")
	private String direccion;

	@NotBlank(message = " Selecciona el tamaño")
	private String poolsize;

	@Min(value = 1, message = " Agrega un precio por noche")
	private double costo;

	// @DecimalMin("1.0")
	// @DecimalMax("5.0")
	private Double rating;

	@NotBlank(message = " Por favor ingresa un direccion")
	private String description;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// rELACION 1:N hacia Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User organizador;

	// Relacion n:n hacia mensajes
	@OneToMany(mappedBy = "evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ComentarioModel> comentarios;

	public WaterModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoolsize() {
		return poolsize;
	}

	public void setPoolsize(String poolsize) {
		this.poolsize = poolsize;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getOrganizador() {
		return organizador;
	}

	public void setOrganizador(User organizador) {
		this.organizador = organizador;
	}

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}