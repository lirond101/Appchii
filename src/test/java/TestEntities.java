import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.hibernate_model.UsersEntity;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
//import org.springframework.security.core.userdetails.User;

/**
 * Created by liron_d on 26/06/2016.
 */
public class TestEntities {

    @Test
    public void givenBidirectionRelation_whenUsingJacksonReferenceAnnotation_thenCorrect()
            throws JsonProcessingException {
        UsersEntity user = new UsersEntity();
        user.setUserid(100);
        user.setUsername("lironTest");
        AssetsEntity item1 = new AssetsEntity();
        item1.setNum_Address("test1");
        item1.setUsersByAgent(user);
        item1.setAssetid(100);
        AssetsEntity item2 = new AssetsEntity();
        item2.setNum_Address("test2");
        item2.setUsersByAgent(user);
        item2.setAssetid(101);
        ArrayList<AssetsEntity> c = new ArrayList<AssetsEntity>();
        c.add(item1);
        c.add(item2);
        user.setAssetsesByUserid(c);

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(result, containsString("test"));
        assertThat(result, containsString("lironTest"));
//        assertThat(result, not(containsString("userItems")));
    }
}
