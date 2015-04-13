package org.mce.jhipsterwsdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestHashset {
	
	
	public static void main(String[] args){
		
		Set<Employee> employeesSet = new HashSet<Employee>();
		
		Set<Employee> employeesTreeSet = new TreeSet<Employee>(new Employee.SurnameAscendingComparator());
		
		Employee mauro = new Employee("Mauro","Camelo");
		Employee marco = new Employee("Marco","Ceccarini");
		Employee michele= new Employee("Micheal","Summer");
		
		// aggiunge i bean nel hashset
		employeesSet.add(mauro);
		employeesSet.add(marco);
		employeesSet.add(michele);
		
		// aggiunge i bean nel treeset
		employeesTreeSet.add(mauro);
		employeesTreeSet.add(marco);
		employeesTreeSet.add(michele);
		
		System.out.println("\nHashset..:");
		for (Employee employee : employeesSet) {
			System.out.println(employee.getName()+" "+employee.getSurname());
		}
		
		// tenta di aggiungere ma la chiave e' gia presente e nn permettte duplicati
		employeesSet.add(mauro);
		
		System.out.println("\nHashset..:");
		for (Employee employee : employeesSet) {
			System.out.println(employee.getName()+" "+employee.getSurname());
		}
		
		System.out.println( "\nremoving mauro..."+employeesSet.remove(mauro));
		
		
		System.out.println( "\ncontains michele..."+employeesSet.contains(michele));
		
		
		//  Orinda la lista secondo il comparator
		List<Employee> employeesList = new ArrayList<Employee>(employeesSet); 
		Collections.sort(employeesList, new Employee.SurnameAscendingComparator());
		
		System.out.println("\nList..:");
		for (Employee employee : employeesList) {
			System.out.println(employee.getName()+" "+employee.getSurname());
		}
		
		// printa il treeset ordinato secondo il comparator
		System.out.println("\nTreeset..:");
		for (Employee employee : employeesTreeSet) {
			System.out.println(employee.getName()+" "+employee.getSurname());
		}
		
	}
	
	

}
