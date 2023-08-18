package com.romina.ideas.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ideas")
public class IdeasModel {

	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String idea;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	//RELACION UNO A MUCHOS (1:N) HACIA USUARIO
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creador;
	
	
	//RELACION MUCHOS A MUCHOS (N:N) HACIA USUARIOS
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="likes",
	joinColumns=@JoinColumn(name="ideas_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> like;
	
	//CONSTRUCTOR VACIO
	public IdeasModel() {
		
	}
	
	
	//GETTERS Y SETTERS
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdea() {
		return idea;
	}
	public User getCreador() {
		return creador;
	}

	public void setCreador(User creador) {
		this.creador = creador;
	}

	public List<User> getLikes() {
		return like;
	}

	public void setLikes(List<User> likes) {
		this.like = likes;
	}

	public void setIdea(String idea) {
		this.idea = idea;
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


}