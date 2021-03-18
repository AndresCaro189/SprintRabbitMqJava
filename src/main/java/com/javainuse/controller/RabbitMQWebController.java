package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;
import com.javainuse.service.RabbitMQSender;

@RestController//Crear un Spring REST Service
@RequestMapping(value = "/javainuse-rabbitmq/") //capturar una petición
/**
 * Controlador (Controller): Este componente es el que responde a la interacción (eventos)
 * que hace el usuario en la interfaz y realiza las peticiones al modelo para pasar estos a la vista.
 * */
public class RabbitMQWebController {

	@Autowired//Sustituye la declaración de los atributos del bean en el xml.
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")//simplificar el manejo de los diferentes métodos
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
	Employee emp=new Employee(); //Se refiere a una entidad o id
	emp.setEmpId(empId);
	emp.setEmpName(empName);
		rabbitMQSender.send(emp);

		return "Hola Mundo de Papas y Papayas :D";
	}

}