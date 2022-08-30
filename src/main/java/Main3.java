import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.JwksVerificationKeyResolver;
import org.jose4j.lang.JoseException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class Main3 {
    public static void main(String[] args) throws JoseException, MalformedClaimException, NoSuchAlgorithmException, InvalidJwtException {
        //
        // JSON Web Token is a compact URL-safe means of representing claims/attributes to be transferred between two parties.
        // This example demonstrates producing and consuming a signed JWT
        //

        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);

        // Give the JWK a Key ID (kid), which is just the polite thing to do
        rsaJsonWebKey.setKeyId("k1");

//        System.out.println(rsaJsonWebKey);
//        System.out.println(rsaJsonWebKey.getPrivateKey());
//        System.out.println(rsaJsonWebKey.getKey());
//        System.out.println(rsaJsonWebKey);

        KeyPairGenerator kgen = KeyPairGenerator.getInstance("RSa");
        System.out.println(kgen);
        kgen.initialize(2048);
        KeyPair keyPair = kgen.genKeyPair();
        System.err.println(keyPair.getPrivate());
        System.err.println(keyPair.getPublic());


//        PrivateKey privateKey = new PrivateKey();

//        rsaJsonWebKey.setPrivateKey("RSA");

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Issuer");  // who creates the token and signs it
        claims.setAudience("Audience"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("subject"); // the subject/principal is whom the token is about
        claims.setClaim("email", "mail@example.com"); // additional claims/attributes about the subject can be added

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims



        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        jws.setPayload(claims.toJson());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        String keyId = rsaJsonWebKey.getKeyId();
        System.err.println(keyId);
        jws.setKeyIdHeaderValue(keyId);

        // The JWT is signed using the private key
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
//        jws.setPayload("")

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt = jws.getCompactSerialization();


        // Now you can do something with the JWT. Like send it to some other party
        // over the clouds and through the interwebs.
        System.out.println("JWT: " + jwt);

//----------------------------------------------------------------------------------------------------------------------------------







//
//        PublicKey publicKey = getPublicKey(getPEMPublicKeyString());
//
//        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
//                .setRequireExpirationTime()
//                .setVerificationKey(publicKey)
//                .setExpectedAudience("test")//用于验证签名是否合法，可以设置多个，且可设置必须存在项，如果jwt中不包含这些内容则不通过
//                .build();
//
//        return jwtConsumer.processToClaims(token);


//
//        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
//        // be used to validate and process the JWT.
//        // The specific validation requirements for a JWT are context dependent, however,
//        // it typically advisable to require a (reasonable) expiration time, a trusted issuer, and
//        // and audience that identifies your system as the intended recipient.
//        // If the JWT is encrypted too, you need only provide a decryption key or
//        // decryption key resolver to the builder.
//        JwtConsumerBuilder jwtConsumerBuilder = new JwtConsumerBuilder();
//        jwtConsumerBuilder.setRequireExpirationTime();
//        jwtConsumerBuilder.setAllowedClockSkewInSeconds(30);
//        jwtConsumerBuilder.setRequireSubject();
//        jwtConsumerBuilder.setExpectedIssuer("Issuer");
//        jwtConsumerBuilder.setExpectedAudience("Audience");
//        jwtConsumerBuilder.setVerificationKey(rsaJsonWebKey.getKey());
//        jwtConsumerBuilder.setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
//                AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256);// the JWT must have an expiration time
//        // allow some leeway in validating time based claims to account for clock skew
//        // the JWT must have a subject claim
//        // whom the JWT needs to have been issued by
//        // to whom the JWT is intended for
//        // verify the signature with the public key
//        JwtConsumer jwtConsumer = jwtConsumerBuilder // which is only RS256 here
//                .build(); // create the JwtConsumer instance
//
//        try {
//            //  Validate the JWT and process it to the Claims
//            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
//            System.out.println("JWT validation succeeded! " + jwtClaims);
//        } catch (InvalidJwtException e) {
//
//        }

        // There's also a key resolver that selects from among a given list of JWKs using the Key ID
        // and other factors provided in the header of the JWS/JWT.
        JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(rsaJsonWebKey);
        System.out.println(jsonWebKeySet.toJson());
        JwksVerificationKeyResolver jwksResolver = new JwksVerificationKeyResolver(jsonWebKeySet.getJsonWebKeys());
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                // ... other set up of the JwtConsumerBuilder ...
                .setVerificationKeyResolver(jwksResolver)
                // ...
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        System.out.println("JWT validation succeeded! " + jwtClaims);
    }

}
