package at.htl.control;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.io.InputStream;
import java.security.PrivateKey;

import static io.smallrye.jwt.KeyUtils.readPrivateKey;

public class TokenUtils {

    private TokenUtils() {

    }

    public static String generateTokenString(String uid)
            throws Exception{
        PrivateKey pk = readPrivateKey("/private.pem");
        return generateTokenString(pk, "/private.pem", uid);
    }

    public static String generateTokenString(PrivateKey privateKey, String kid, String uid) throws Exception {
        JwtClaims claims = getJwtClaims(uid);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);
        jws.setKeyIdHeaderValue(kid);
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        return jws.getCompactSerialization();
    }

    private static JwtClaims getJwtClaims(String uid) {
        long currentTimeInSecs = currentTimeInSecs();
        long exp = currentTimeInSecs + 60*15; // 15 minutes until token expires

        JwtClaims claims = new JwtClaims();
        claims.setIssuer("https://this-is-totally-our.domain");
        claims.setSubject(uid);
        claims.setIssuedAt(NumericDate.fromSeconds(currentTimeInSecs));
        claims.setClaim(Claims.auth_time.name(), NumericDate.fromSeconds(currentTimeInSecs));
        claims.setExpirationTime(NumericDate.fromSeconds(exp));

        JsonArrayBuilder groupsBuilder = Json.createArrayBuilder();
        groupsBuilder.add("defaultUsers");
        String groups = groupsBuilder.build().toString();
        claims.setClaim(Claims.groups.name(), groups);

        return claims;
    }

    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}
