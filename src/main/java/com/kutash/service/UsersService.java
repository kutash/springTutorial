package com.kutash.service;


import com.kutash.dao.Message;
import com.kutash.dao.MessageDao;
import com.kutash.dao.User;
import com.kutash.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Transactional
@Service("usersService")
public class UsersService {

	private UsersDao usersDao;

    @Autowired
	private MessageDao messageDao;

    @Autowired
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String name){
	    return usersDao.exists(name);
    }

    @Secured("ROLE_ADMIN")
    public List<User> getUsers() {
	    return usersDao.getUsers();
    }

    public void sendMessage(Message message){
        messageDao.create(message);
    }

    public User getUser(String username){
        if (username == null){
            return null;
        }
        return usersDao.getUser(username);
    }

    public List<Message> getMessages(String username) {
        return messageDao.getMessages(username);
    }
}
