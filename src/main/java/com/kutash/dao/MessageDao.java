package com.kutash.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
@Component("messageDao")
public class MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Message> getMessages() {

        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaQuery<Message> query = cb.createQuery( Message.class );
        query.from( Message.class );
        return session().createQuery( query ).getResultList();
    }

    public List<Message> getMessages(String username) {

        Query query1 = session().createQuery("FROM Message where username=:username");
        query1.setParameter("username", username);
        return query1.getResultList();
    }

    public Message getMessage(int id) {

        TypedQuery<Message> typedQuery = session().createQuery(
                "select p " +
                        "from Message p " +
                        "where p.id like :id", Message.class
        );
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    public void delete(int id){
        Query query1 = session().createQuery("DELETE Message where id = :id");
        query1.setParameter("id", id);
        query1.executeUpdate();
    }

    public void create(Message message){
        session().saveOrUpdate(message);
    }
}
