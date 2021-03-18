package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)//se usa cuando los objetos tienen una relación padre-hijo
/**
 * Model: Es donde esta toda la lógica del negocio, la representación de tod
 * el sistema incluido la interacción con una base de datos, si es que
 * el programa asi lo requiere.
 * */

public class Employee {

	private String empName;
	private String empId;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override// forzar al compilador a comprobar en tiempo de compilación que estás sobrescribiendo correctamente un método, y de este modo evitar errores en tiempo de ejecución
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + "]";
	}

}