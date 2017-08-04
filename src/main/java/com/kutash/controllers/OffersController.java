package com.kutash.controllers;

import com.kutash.dao.FormValidationGroup;
import com.kutash.dao.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.kutash.service.OffersService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OffersController {

    private OffersService offersService;

    @Autowired
    public void setOffersService(OffersService offersService) {
        this.offersService = offersService;
    }

    @RequestMapping("/offers")
    public String showHome(Model model, Principal principal){
        Map<String, Object> attributes = new HashMap<String, Object>();
        List<Offer> offers = offersService.getCurrent();
        List<Offer> myOffers;
        if (principal != null) {
            String username = principal.getName();
            myOffers = offersService.getMyOffers(username);
            attributes.put("offersMy", myOffers);
        }
        attributes.put("offers", offers);
        model.addAllAttributes(attributes);
        return "offers";
    }

    @RequestMapping("/offer/{id}")
    public String getOffer(@PathVariable int id, Model model){
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "createoffer";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public String delete(@PathVariable int id){
        offersService.delete(id);
        return "redirect:/offers";
    }

    @RequestMapping("/createoffer")
    public String create(Model model){
        model.addAttribute("offer", new Offer());
        return "createoffer";
    }

    @RequestMapping(value = "/docreate", method= RequestMethod.POST)
    public String doCreate(Model model, @Validated(FormValidationGroup.class) Offer offer, BindingResult result, Principal principal) {

        if(result.hasErrors()) {
            System.out.println("Form does not validate.");
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error: errors) {
                System.out.println(error.getDefaultMessage());
            }
            return "createoffer";
        }
        else {
            String username= principal.getName();
            offer.getUser().setUsername(username);
            if(offer.getId()!= 0) {
                offersService.updateOffer(offer);
            } else {
                offersService.create(offer);
            }
            return "offercreated";
        }

    }


}
