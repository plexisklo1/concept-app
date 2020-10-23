package appConcept.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetailDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//get Detail from DB via Employee object
	public Detail getDetails(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Detail> query = session.createQuery("FROM Detail det WHERE det.employee=:emp", Detail.class);
		query.setParameter("emp", emp);
		Detail detail = query.getSingleResult();
		session.getTransaction().commit();
		return detail;
	}
	
	//get Detail from DB via id/DB PK
	public Detail getDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Detail> query = session.createQuery("FROM Detail det WHERE det.id=:id", Detail.class);
		query.setParameter("id", id);
		Detail detail = query.getSingleResult();
		session.getTransaction().commit();
		return detail;
	}
	
	//persist Detail object
	public int saveDetails(Detail detail) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(detail);
		session.getTransaction().commit();
		return detail.getId();
	}
	
	
	//remove Detail object via id/DB PK
	public void removeDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Detail> query = session.createQuery("FROM Detail det WHERE det.id=:iddet",Detail.class);
		query.setParameter("iddet", id);
		Detail detail = query.getSingleResult();
		session.remove(detail);
		session.getTransaction().commit();
	}
	
	
	
}
