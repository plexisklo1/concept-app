package appConcept.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="employees")
public class Employee {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Size(min=1, max=20, message="Must be of length between 1 and 20")
	@Pattern(regexp = "[a-zA-Z0-9-]+", message="Can only use letters, numbers and dashes.")
	@Column(name="first_name")
	private String firstName;
	
	@Size(min=1, max=20, message="Must be of length between 1 and 20")
	@Pattern(regexp = "[a-zA-Z0-9-]+", message="Can only use letters, numbers and dashes.")
	@Column(name="last_name")
	private String lastName;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="team_id")
	private Team team;
	
	@OneToOne(mappedBy="employee", fetch=FetchType.EAGER)
	private Detail detail;

	
	public Employee() {}

	public Employee(int id, String firstName, String lastName, Team team, Detail detail) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.team = team;
		this.detail=detail;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return id+" "+firstName+" "+lastName;
	}
	
}
