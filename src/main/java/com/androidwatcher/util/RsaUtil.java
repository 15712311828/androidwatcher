package com.androidwatcher.util;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {

    public static String data="666666";

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = genKeyPair(1044);

        String key=keyToString(keyPair.getPublic());

        byte[] encryptedBytes=encrypt(data.getBytes(), getPublicKey(key));
        System.out.println("加密后："+new String(Base64.getEncoder().encode(encryptedBytes)));

        key=keyToString(keyPair.getPrivate());

        byte[] decryptedBytes=decrypt(encryptedBytes, getPrivateKey(key));
        System.out.println("加密前："+new String(decryptedBytes));
    }

    public static String keyToString(Key key){
        return new String(Base64.getEncoder().encode(key.getEncoded()));
    }

    @SneakyThrows
    public static KeyPair genKeyPair(int keyLength){
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLength);
        return keyPairGenerator.generateKeyPair();
    }

    @SneakyThrows
    public static byte[] encrypt(byte[] content, PublicKey publicKey){
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    @SneakyThrows
    public static byte[] decrypt(byte[] content, PrivateKey privateKey){
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    @SneakyThrows
    public static PublicKey getPublicKey(String publicKey){
        byte[ ] keyBytes=Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    @SneakyThrows
    public static PrivateKey getPrivateKey(String privateKey){
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

}
