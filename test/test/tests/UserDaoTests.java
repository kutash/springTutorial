/*
package tests;

import static org.junit.Assert.*;

import com.kutash.dao.User;
import com.kutash.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com.kutash.dao-context.xml",
		"classpath:security-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("johnwpurcell1", "hellothere1", "john@caveofprogramming.com1", true, "ROLE_USER");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from users");
    }

    @Test
    public void testCreateRetrieve(){
        usersDao.create(user1);

        List<User> users = usersDao.getUsers();

        assertEquals("Number of users should be 1.", 1, users.size());

        assertTrue("User should exist.", usersDao.exists(user1.getUsername()));

        assertEquals("Created user should be identical to retrieved user", user1, users.get(0));
    }

    @Test
    public void testCreateUser() {
        User user = new User("johnwpurcell", "hellothere", "john@caveofprogramming.com", true, "ROLE_USER");

        usersDao.create(user);

        List<User> users = usersDao.getUsers();

        assertEquals("Number of users should be 1.", 1, users.size());

        assertTrue("User should exist.", usersDao.exists(user.getUsername()));

        assertEquals("Created user should be identical to retrieved user", user, users.get(0));
    }
	
}
*/
