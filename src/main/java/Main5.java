import org.jose4j.base64url.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Main5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("rsa");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println(publicKey);//从输出结果可以知道是PKCS#8格式的
        System.out.println(privateKey);//从输出结果可以知道是PKCS#8格式的

        System.out.println();

        String pubKeyStr = new String(Base64.encode(publicKey.getEncoded())); //pkcs8格式
        String priKeyStr = new String(Base64.encode(privateKey.getEncoded())); //pkcs8格式
        System.out.println(pubKeyStr);//从输出结果可以知道是PKCS#8格式的
        System.out.println(priKeyStr);//从输出结果可以知道是PKCS#8格式的

    }
}
