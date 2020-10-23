package appConcept.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="teams")
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message = "Has to be defined.")
	@Size(min = 1, max = 20, message = "Has to be between 1 and 20 characters long.")
	@Pattern(regexp = "[a-zA-z 0-9.-]+", message = "Use only letters, numbers, spaces, dots and dashes.")
	@Column(name="name")
	private String name;
	
	@NotNull(message = "Has to be defined.")
	@Size(min=1,max=30, message = "Has to be between 1 and 30 characters long.")
	@Pattern(regexp = "[a-zA-z 0-9.-]+", message = "Use only letters, numbers, spaces, dots and dashes.")
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="team", cascade = {CascadeType.DETACH,CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})	
	private List<Employee> employeeList=new ArrayList<>();;

	public Team() {}

	public Team(int id, String name, String description, List<Employee> employeeList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.employeeList = employeeList;
	}

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	@Override
	public String toString() {
		return "Name: "+name+", Description: "+description;
	}
	
}
