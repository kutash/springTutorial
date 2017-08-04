package com.kutash.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

	public void create(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().save(user);
	}

    public boolean exists(String username) {
        Long count = (Long) session().createQuery("select count (*)  from User where username=:username").setParameter("username",username).uniqueResult();
        return count !=0;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return session().createQuery("from User").list();

    }

    public User getUser(String username) {

        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root)
                .where(cb.equal(root.get("username"), username));
        User result = session().createQuery(query).getSingleResult();
        return result;
    }
}
