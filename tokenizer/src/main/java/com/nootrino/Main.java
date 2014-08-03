/**
 *      Copyright © 2014 Kevin Duraj,  All rights reserved.
 * 
 *      Credit Card Tokenizer
 *      Merchant token is generated based on Credit Card Number and 
 *      Current Date to protect Customer and Credit Card information
 * 
 *      Date: August 3, 2014 4:24PM
 *      Written By: Kevin Duraj
 *      
 */

package com.nootrino;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        System.out.println("Credit Card Tokenizer");

        System.out.println("New Merchant Token:\n" + sha256("12345678901" + "20140803"));
    }

    /*--------------------------------------------------------------------------------------------*/
    public static String sha256(String base) {
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
            
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

}
