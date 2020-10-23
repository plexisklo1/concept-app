package appConcept.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EmployeeDAO () {	}
	
	//get Employee based on id/DB PK
	public Employee getEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Employee> query = session.createQuery("FROM Employee emp WHERE emp.id=:empId", Employee.class);
		query.setParameter("empId", id);
		Employee emp = query.getSingleResult();
		session.getTransaction().commit();
		return emp;
	}
	
	//persist Employee
	public int saveEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(emp);
		session.getTransaction().commit();
		return emp.getId();
	}
	
	//remove Employee - delete from List<Employee> belonging to Team
	public void removeEmployee(int id) {
		Employee emp = getEmployee(id);
		int teamId = emp.getTeam().getId();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Team> queryTeam = session.createQuery("FROM Team team WHERE team.id=:teamId",Team.class); 
		queryTeam.setParameter("teamId", teamId);
		Team team = queryTeam.getSingleResult();
		session.getTransaction().commit();
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Employee> queryEmp = session.createQuery("FROM Employee emp WHERE emp.team=:team", Employee.class);
		queryEmp.setParameter("team", team);
		List<Employee> listEmp = queryEmp.getResultList();
		session.getTransaction().commit();
		
		for (Iterator<Employee> iterator=listEmp.iterator();iterator.hasNext();) {
			if (iterator.next()==queryTeam) {
				iterator.remove();
				break;
			}
			
		}
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(team);
		session.delete(emp);
		session.getTransaction().commit();
	}
	
	
	//get all Employee records from DB
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employee> list = new ArrayList<>();
		Query<Employee> query = session.createQuery("FROM Employee emp", Employee.class);
		list = query.getResultList();
		session.getTransaction().commit();
		return list;
	}
	
}
