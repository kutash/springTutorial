package com.kutash.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Transactional
@Repository
@Component("offersDao")
public class OffersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Offer> getOffers() {

        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaQuery<Offer> query = cb.createQuery( Offer.class );
        query.from( Offer.class );
        return session().createQuery( query ).getResultList();
    }

    public List<Offer> getOffers(String username) {

        Query query1 = session().createQuery("FROM Offer where username=:username");
        query1.setParameter("username", username);
        return (List<Offer>) query1.getResultList();
    }

    public Offer getOffer(int id) {

        TypedQuery<Offer> typedQuery = session().createQuery(
                "select p " +
                        "from Offer p " +
                        "where p.id like :id", Offer.class
        );
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    public void update(Offer offer) {
        session().update(offer);
    }

    public void delete(int id){
        Query query1 = session().createQuery("DELETE Offer where id = :id");
        query1.setParameter("id", id);
        query1.executeUpdate();

    }

    public void create(Offer offer){
        session().save(offer);
    }

}
