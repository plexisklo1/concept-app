package appConcept.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Team getTeam(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Team> query = session.createQuery("FROM Team team WHERE team.id=:id", Team.class);
		query.setParameter("id", id);
		Team team = query.getSingleResult();
		session.getTransaction().commit();
		return team;
	}
	
	public List<Team> getTeams() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Team> query = session.createQuery("FROM Team", Team.class);
		List<Team> list = query.getResultList();
		session.getTransaction().commit();
		return list;
	}

	public Team saveTeam(Team team) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(team);
		session.getTransaction().commit();
		return team;
	}

	public void removeTeam(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Team> query = session.createQuery("FROM Team team WHERE team.id=:idteam", Team.class);
		query.setParameter("idteam", id);
		Team team = query.getSingleResult();
		session.remove(team);
		session.getTransaction().commit();
	}
}
