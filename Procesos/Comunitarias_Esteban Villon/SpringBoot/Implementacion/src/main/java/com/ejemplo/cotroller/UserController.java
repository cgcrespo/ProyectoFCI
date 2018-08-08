package com.ejemplo.cotroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.User;
import com.ejemplo.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserRepository userRepository;
	
	//Creacion
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearPersona(@RequestBody User user)
	{
		userRepository.save(user);
	}
	
	//Lectura
	@RequestMapping(value ="/{id}", method = RequestMethod.GET )
	public Optional<User> leerPersona(@PathVariable String id)
	{
		return userRepository.findById(id);
	}
	//Actualizacion
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarPersona(@RequestBody User user)
	{
		userRepository.save(user);
	}
	//Borrar
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void borrarPersona(@PathVariable String id)
	{
		userRepository.deleteById(id);
	}

}
