import org.jose4j.base64url.Base64;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;

public class Main6 {
    public static void main(String[] args) throws JoseException, MalformedClaimException, InvalidJwtException, IOException {

        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("k1");

        JwtClaims claims = new JwtClaims();
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture(30);
        claims.setClaim("email","mail@example.com"); // additional claims/attributes about the subject can be added

        PrivateKey privateKey = rsaJsonWebKey.getPrivateKey();
        String encode = Base64.encode(privateKey.getEncoded());
        System.out.println("-----BEGIN PRIVATE KEY-----\n" + encode);


        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);

        String jwt = jws.getCompactSerialization();


        System.out.println("JWT: " + jwt);

        Key publicKey = rsaJsonWebKey.getKey();
        encode = Base64.encode(publicKey.getEncoded());
        System.out.println("-----BEGIN PUBLIC KEY-----\n" + encode);


        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(publicKey)
                .build();


        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        System.out.println("JWT validation succeeded! " + jwtClaims);

    }
}
