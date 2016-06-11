import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.hibernate_model.UsersEntity;
import com.myRemax.service.AssetManager;
import com.myRemax.service.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.List;

/**
 * Created by liron_d on 23/05/2016.
 */
public class assetTest {
    public static void main( String[] args )
    {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        AssetManager assetManager =
                (AssetManager) ctx.getBean("assetManagerImpl");

//        UserManager userManager =
//                (UserManager) ctx.getBean("userManagerImpl");
        try {
//        UsersEntity user = userManager.getUserById(2);
//
//        AssetsEntity asset = new AssetsEntity();
//        asset.setUsersByAgent(user);
//        asset.setCity("באר שבע");
//        asset.setStreet("בן יהודה");
//        asset.setNum_Address("19/4");
//        asset.setCust_Tel("0526879545");
//        assetManager.insertAsset(asset);
//
//        System.out.println("Asset inserted!");

            List<AssetsEntity> assetsEntities = assetManager.getAssetByFullAddress("באר שבע", "בן יהודה", "");

            for (int i=0; i<assetsEntities.size(); i++) {
                System.out.println("\nAsset fetched by address!"
                        + "\nId: " + assetsEntities.get(i).getAssetid()
                        + "\nStreet: " + assetsEntities.get(i).getStreet()
                        + "\nCust_num: " + assetsEntities.get(i).getCust_Tel());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

/*
        asset = assetManager.getAsset(asset.getAssetid());

        System.out.println("\nAsset fetched by ID!"
                + "\nId: " + assetTwo.getAssetid()
                + "\nAddress: " + asset.getAddress());

        List<AssetsEntity> assets = assetManager.getAssets();

        System.out.println("\nAsset list fetched!"
                + "\nAsset count: " + assets.size());

        List<AssetsEntity> assets2 = assetManager.getAssetsByAgent(1);

        System.out.println("\nAgent's assets list fetched!"
                + "\nAsset count: " + assets2.size());
*/
    }
}
