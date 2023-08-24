package com.romina.waterbnb.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.romina.waterbnb.models.User;
import com.romina.waterbnb.repositories.UserRepo;

@Service
public class UserService {

	//INYECCION DE DEPENDENCIAS
	private final UserRepo userRepo;
	
	//CONSTRUCTOR
	public UserService(UserRepo uRe) {
		this.userRepo = uRe;
	}

	//ENCONTRAR USUARIO A TRAVÉS DE EMAIL                    
	public User encontrarPorEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	//ENCONTRAR USUARIO A TRAVÉS DEL ID
	public User encontrarUserPorId(Long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	//REGISTRAR AL USUARIO
	public User registroUsuario(User user, BindingResult resultado) {

		User usuarioRegistrado = userRepo.findByEmail(user.getEmail());

		if (usuarioRegistrado != null) {
			resultado.rejectValue("email", "Matches", "Correo electronico ya existe");
		}
		if (!user.getPassword().equals(user.getPasswordConfirmation())) {
			resultado.rejectValue("password", "Matches", "Password no coincide");
		}
		if (resultado.hasErrors()) {
			return null;
		}
		//CREAR PASSWORD HASHEADO CON SALT
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);

	}

	//AUTENTICACION DEL USUARIO (LOGIN)
	public boolean authenthicateUser(String email, String password, BindingResult resultado) {

		User usuarioRegistrado = userRepo.findByEmail(email);

		if (usuarioRegistrado == null) {
			resultado.rejectValue("email", "Matches", "Email no valido");
			return false;
		} else {
			if (BCrypt.checkpw(password, usuarioRegistrado.getPassword())) {
				return true;
			} else {
				resultado.rejectValue("password", "Matches", "Password no valido");
				return false;
			}
		}
	}

}