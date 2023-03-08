package com.example.projet_e5;

import android.annotation.SuppressLint;
import android.os.Build;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Base64.*;


public class Api {

    private  String Url = "http://www.zeyu.site/api/function/func.php?";
    private String Token = "S8jlgW4doHq1Lh9mfqSu6Q";

    private String Key_AES = "bUYJ3nTV6VBasdJF";

    public String Send_Api(String name_function,String Values){
        String Prepare_Url = this.Url + "func=" + name_function + "&token=" + this.Token;
        System.out.println(encrypt(name_function,this.Key_AES));
        System.out.println(Prepare_Url);
        return null;
    }

    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Encoder encoder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(encoder.encodeToString(crypted));
        }else{
            return "null";
        }
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            Decoder decoder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                decoder = Base64.getDecoder();
            }
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                output = cipher.doFinal(decoder.decode(input));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }

}
