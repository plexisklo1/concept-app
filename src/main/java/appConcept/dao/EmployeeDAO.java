package appConcept.dao;

import java.util.ArrayList;
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
	
	public EmployeeDAO () {
		System.out.println("employeeDAO created");
	}
	
	public Employee getEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Employee> query = session.createQuery("FROM Employee emp WHERE emp.id=:empId", Employee.class);
		query.setParameter("empId", id);
		Employee emp = query.getSingleResult();
		session.getTransaction().commit();
		System.out.println(emp.toString());
		System.out.println("returning employee from id");
		return emp;
	}
	
	public int saveEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(emp);
		session.getTransaction().commit();
		return emp.getId();
	}
	
	public void removeEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee emp = getEmployee(id);
		session.beginTransaction();
//		Query<Employee> query = session.createQuery("FROM Employee emp WHERE emp.id=:id",Employee.class);
//		query.setParameter("id", id);
//		Employee emp = query.getSingleResult();
		session.delete(emp);
		session.getTransaction().commit();
	}
	
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employee> list = new ArrayList<>();
		Query<Employee> query = session.createQuery("FROM Employee emp", Employee.class);
		list = query.getResultList();
		session.getTransaction().commit();
		System.out.println(list.toString());
		return list;
	}
	
	public List<Employee> getEmpTeam(Team team) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employee> list = new ArrayList<>();
		Query<Employee> query = session.createQuery("FROM Employee emp WHERE emp.team=:teamId", Employee.class);
		query.setParameter("teamId", team.getId());
		list = query.getResultList();
		session.getTransaction().commit();
		return list;
	}
	
	
}
