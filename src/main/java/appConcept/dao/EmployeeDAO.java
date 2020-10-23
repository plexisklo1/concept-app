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
		Employee emp = getEmployee(id);
		int teamId = emp.getTeam().getId();
		System.out.println("checkpoint1");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Team> queryTeam = session.createQuery("FROM Team team WHERE team.id=:teamId",Team.class); 
		queryTeam.setParameter("teamId", teamId);
		Team team = queryTeam.getSingleResult();
		System.out.println("checkpoint2");
		session.getTransaction().commit();
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Employee> queryEmp = session.createQuery("FROM Employee emp WHERE emp.team=:team", Employee.class);
		queryEmp.setParameter("team", team);
		List<Employee> listEmp = queryEmp.getResultList();
		System.out.println("checkpoint3");
		session.getTransaction().commit();
		
//		for (Iterator iterator=team.getEmployeeList().iterator();iterator.hasNext();) {
		for (Iterator<Employee> iterator=listEmp.iterator();iterator.hasNext();) {
			if (iterator.next()==queryTeam) {
				iterator.remove();
				System.out.println("good so far");
				break;
			}
			
		}
		System.out.println("checkpoint4");
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(team);
		
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
