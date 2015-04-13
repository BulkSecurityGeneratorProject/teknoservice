package org.mce.jhipsterwsdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee{
	
	private String name;
	private String surname;
	private List<String> skills = new ArrayList<String>();
	
	public Employee(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	} 

	public static class SurnameAscendingComparator implements Comparator<Employee> {

		public int compare(Employee thiz, Employee other) {
			return thiz.surname.compareTo(other.getSurname());
		}
	}
	
}

