package com.romina.waterbnb.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comentarios")
public class ComentarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Por favor ingresa un mensaje")
	@Column(columnDefinition = "text")
	@Size(min = 3, message = "Comentario debe tener minimo 3 caracteres")
	private String comentario;

	@Min(value = 1, message = "Debe seleccionar rating")
	@Max(value=5)
	private int rating;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// Relaciones tabla intermedia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id")
	private WaterModel evento;

	public ComentarioModel() {

	}

	public ComentarioModel(User usuario, WaterModel evento, String comentario) {
		this.autor = usuario;
		this.evento = evento;
		this.comentario = comentario;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

	public WaterModel getEvento() {
		return evento;
	}

	public void setEvento(WaterModel evento) {
		this.evento = evento;
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