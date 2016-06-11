import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

/**
 * Created by liron_d on 05/05/2016.
 */
public class JWT_test {
/*
    // We need a signing key, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
    Key key = MacProvider.generateKey();
    String s = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();
    //How easy was that!?
    //Now let's verify the JWT (you should always discard JWTs that don't match an expected signature):

    assert Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject().equals("Joe");
    //You have to love one-line code snippets!

    //But what if signature validation failed? You can catch SignatureException and react accordingly:

    try {
        Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwt);

        //OK, we can trust this JWT

    } catch (SignatureException e) {

        //don't trust the JWT!
    }


    //Read more: http://www.techartifact.com/blogs/2016/02/json-web-token-jwt-implementation-in-java.html#ixzz47nY6zpjo

    public static void main(String[] args) {
        createJWT("15","login",1462461180);
    }
    */
}
