package com.kutash.controllers;


import com.kutash.dao.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kutash.service.UsersService;

import java.util.List;

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping("/")
    public String showHome(){

        logger.info("Showing home page...");
        return "home";
    }

    @RequestMapping("/admin")
    public String showAdmin(Model model){
        List<User> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/denied")
    public String showDenied(){
        return "denied";
    }
}
