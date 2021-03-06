/**
 * Created by liron_d on 22/05/2016.
 */

import java.util.List;

import com.myRemax.controller.login.LoginController;
import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.hibernate_model.UsersEntity;
import com.myRemax.service.AssetManager;
import com.myRemax.service.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;

public class Main {


    public static void main(String[] args) {
        //getAssetByAgentName();
//        insertAssetTest();
        getAssetByAgentName("שה");
//        getAssetByAgentName("מש אטס");
//        checkSubString("משה אטס");
//        checkSubString("משה בן לולו");
//        checkSubString("משה ");
    }


    public void getUser() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");

        UserManager userManager =
                (UserManager) ctx.getBean("userManagerImpl");
        UsersEntity user = new UsersEntity();
        user.setUserid(10);
        user.setUsername("stamUser6");
        user.setPassword("stamUser303");

        userManager.insertUser(user);

        System.out.println("User inserted!");
        try {
            user = userManager.getUser("stamUser6");
        } catch (Exception ex) {
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

    public static void getAssetByAgentName(String name) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        AssetManager assetManager =
                (AssetManager) ctx.getBean("assetManagerImpl");
        UserManager userManager =
                (UserManager) ctx.getBean("userManagerImpl");

        try {
            Integer agentID = userManager.getUserByName(name);

//            List<AssetsEntity> a = assetManager.("moshe123");
            System.out.println(agentID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void insertAssetTest() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        AssetManager assetManager =
                (AssetManager) ctx.getBean("assetManagerImpl");
        UserManager userManager =
                (UserManager) ctx.getBean("userManagerImpl");

        try {
            AssetsEntity a = new AssetsEntity();
            a.setNum_Address("5");
            a.setStreet("testToday");
            a.setCity("test");

//            List<AssetsEntity> a = assetManager.getAssetsByAgent("moshe123");
            Integer assetID = assetManager.insertAsset(a);
            System.out.println(assetID.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void checkSubString(String name) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        AssetManager assetManager =
                (AssetManager) ctx.getBean("assetManagerImpl");
        UserManager userManager =
                (UserManager) ctx.getBean("userManagerImpl");

        int index = name.indexOf(' ');
        if (index != -1 && name.length()-(index+1)>0) {
            String fn = name.substring(0, index);
            System.out.println(fn);
            String ln = name.substring(index, name.length());
            System.out.println(ln);
        }
        else
            System.out.println("stam");
    }
}
