/*
 * Copyright 2023 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ultimatetek.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jamil
 */
public class PswdSecurity {

    private static final Logger LOGGER = LoggerFactory.getLogger(PswdSecurity.class);
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA256";
//    public static final String SECRET_KEY = "ordrMngMnt";

    public static String getSHAHash(String givenText) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(givenText.getBytes());

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex.getMessage());
        }
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    public static String calculateRFC2104HMAC(String data, String key) throws java.security.SignatureException {
        String result;
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
            // base64-encode the hmac
            result = Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

//    public static void main(String args[]) throws SignatureException {
//     //System.out.println(RemSecurity.calculateRFC2104HMAC("Remittance","Remittance"));
//        System.out.println(calculateRFC2104HMAC("test123", ProjectConst.SECRET_KEY));
//        //System.out.println(checkPasswordPolicy("HSz950IiSqkWrMC+KBIAFqRJ5Dapq7YlcxoAYXmCuJA=", calculateRFC2104HMAC("test1235", ProjectConst.SECRET_KEY)));
////with help of salt
////        String salt = generateSalt();
////        String secretKey = generateSecretKey(salt);
////        String encrpted = calculateRFC2104HMAC("123", secretKey);
////        System.out.println("Salt: " + salt);
////        System.out.println("Hashed Password: " + encrpted);
//    }
    
    //BAle1TfgxidhoSNWWreXH1kmV5a3I9c0OkM+rWLY+rQ=

    public static boolean checkPasswordPolicy(String givenPassword) {
        Pattern p = Pattern.compile(".*[+=].*");
        Matcher m = p.matcher(givenPassword);
        return m.find();
//        return givenPassword.equals(encrytedPassword);
    }

    public static String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

    public static String generateSecretKey(String salt) {
        // Combine salt with master secret key
        String combinedKey = ProjectConst.SECRET_KEY + salt;
        return getSHAHash(combinedKey);
    }

    public static boolean checkPassword(String password, String hashedPassword, String secretKey) throws SignatureException {
        String generatedHash = calculateRFC2104HMAC(password, secretKey);
        return hashedPassword.equals(generatedHash);
    }

}
