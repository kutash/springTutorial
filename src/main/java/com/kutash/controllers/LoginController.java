package com.kutash.controllers;

import com.kutash.dao.FormValidationGroup;
import com.kutash.dao.Message;
import com.kutash.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kutash.service.UsersService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/newaccount")
    public String showNewAccount(Model model) {

        model.addAttribute("user", new User());
        return "newaccount";
    }

    @RequestMapping(value="/createaccount", method=RequestMethod.POST)
    public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {

        if(result.hasErrors()) {
            return "newaccount";
        }

        user.setAuthority("ROLE_USER");
        user.setEnabled(true);

        if(usersService.exists(user.getUsername())){
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newaccount";
        }

        try {
            usersService.create(user);
        } catch (DuplicateKeyException e){
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newaccount";
        }
        return "accountcreated";
    }

    @RequestMapping(value="/logout")
    public String logoutPage () {
        return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(value = "/getmessages", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> getMessages(Principal principal){

        List<Message> messages;
        if(principal == null){
           messages = new ArrayList<Message>();
        } else {
            String username = principal.getName();
            messages = usersService.getMessages(username);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("messages", messages);
        data.put("number", messages.size());
        return data;
    }

    @RequestMapping("/messages")
    public String showMessages() {
        return "messages";
    }
}