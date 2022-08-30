import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Main1 {
    public static void main(String[] args) throws MalformedClaimException, JoseException {
        // JSON Web Token是一个紧凑的url安全的方法，表示声明/属性之间的转移双方。

        //这个例子演示了如何生成和使用一个有符号的JWT

        // 生成一个RSA密钥对，它将用于签名和验证打包在JWK中的JWT
         RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);

        // 给JWK一个Key ID (kid)，这只是礼貌的做法
        rsaJsonWebKey.setKeyId("k1");

        // 创建Claims，它将成为JWT的内容
        JwtClaims claims = new JwtClaims();
//        claims.setIssuer("Issuer");  // who creates the token and signs it
//        claims.setAudience("Audience"); // to whom the token is intended to be sent
//        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setClaim("name", "John Doe");
        claims.setClaim("iat", 1516239022);

        //JWT有两种类型(JWS或JWE)
        //在这个例子中它是一个JWS，所以我们创建了一个JsonWebSignature对象。
        JsonWebSignature jws = new JsonWebSignature();

        // JWS的有效载荷是JWT声明的JSON内容
        jws.setPayload(claims.toJson());

        // JWT使用私钥签名
        PrivateKey privateKey = rsaJsonWebKey.getPrivateKey();
        PublicKey publcKey = rsaJsonWebKey.getRsaPublicKey();
        System.out.println(publcKey);
        System.out.println(privateKey);
//        System.out.println(rsaJsonWebKey.getKeyType());
        jws.setKey(privateKey);

        //设置Key ID (kid)头，因为这只是出于礼貌的做法。
        //在这个例子中我们只有一个键，但是使用key ID会有帮助
        //实现一个平滑的键翻转过程
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        //在JWT/JWS上设置完整保护索赔的签名算法
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        //签署JWS，并制作紧凑的系列或完整的JWT/JWS
        //是一个由三个点('.')分隔的字符串
        //表单Header.Payload.Signature中base64url编码的部分
        //如果你想加密它，你可以简单地设置这个jwt作为有效负载
        //设置JsonWebEncryption对象的cty(内容类型)头为"jwt"。
        String jwt = jws.getCompactSerialization();


        //现在你可以用JWT做一些事情了。比如寄给别的派对
        //越过云层，穿过网络。
        System.out.println("JWT: " + jwt);



    }
}
