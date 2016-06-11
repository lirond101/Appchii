/**
 * Created by liron_d on 22/05/2016.
 */

import java.util.List;

import com.myRemax.hibernate_model.UsersEntity;
import com.myRemax.service.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main( String[] args )
    {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        UserManager userManager =
                (UserManager) ctx.getBean("userManagerImpl");

        UsersEntity user = new UsersEntity();
        user.setUserid(7);
        user.setUsername("stamUser4");
        user.setPassword("stamUser101");

        userManager.insertUser(user);

        System.out.println("User inserted!");
try {
    user = userManager.getUser("stamUser3");
}
catch (Exception ex){
    System.out.println(ex.getMessage());
}
        System.out.println("\nUser fetched by username!"
                + "\nId: " + user.getUserid()
                + "\nUsername: " + user.getUsername()
                + "\nName: " + user.getPassword());

        user = userManager.getUserById(user.getUserid());

        System.out.println("\nUser fetched by ID!"
                + "\nId: " + user.getUserid()
                + "\nUsername: " + user.getUsername()
                + "\nName: " + user.getPassword());

        List<UsersEntity> users = userManager.getUsers();

        System.out.println("\nUser list fetched!"
                + "\nUser count: " + users.size());

    }
}
