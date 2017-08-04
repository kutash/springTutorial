package com.kutash.service;

import com.kutash.dao.Offer;
import com.kutash.dao.OffersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("offersService")
public class OffersService {

    private OffersDAO offersDAO;

    @Autowired
    public void setOffersDAO(OffersDAO offersDAO) {
        this.offersDAO = offersDAO;
    }

    public List<Offer> getCurrent(){
        return offersDAO.getOffers();
    }

    @Secured("ROLE_USER")
    public void create(Offer offer){
        offersDAO.create(offer);
    }

    public List<Offer> getMyOffers(String username) {
        return offersDAO.getOffers(username);
    }


    @Transactional
    public void delete(int id) {
        offersDAO.delete(id);
    }

    public Offer getOffer(int id){
       return offersDAO.getOffer(id);
    }

    public void updateOffer(Offer offer) {
        offersDAO.update(offer);
    }
}
