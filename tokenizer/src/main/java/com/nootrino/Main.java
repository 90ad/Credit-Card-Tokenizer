/**
 * Copyright © 2014 Kevin Duraj, All rights reserved.
 *
 * Credit Card Tokenizer Merchant token is generated based on Credit Card Number and Current Date to
 * protect Customer and Credit Card information
 *
 * Date: August 3, 2014 4:24PM Written By: Kevin Duraj
 *
 */
package com.nootrino;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private final static String CREDITCARD = "1343 1771 6357 4580";
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

        line();
        System.out.println("Credit Card Tokenizer");
        String today = current_date();
        line();
        System.out.println("Test Credit Card Number= " + CREDITCARD);
        line();
        System.out.println("Today = " + today);
        System.out.println("Today Merchant Token:\n" + sha256(CREDITCARD + today));
        line();
        String tmr = tomorrow();
        String tomorrow = format_gmt_datetime(tmr);
        System.out.println("Tomorrow = " + tomorrow);
        System.out.println("Tomorrow Merchant Token:\n" + sha256(CREDITCARD + tomorrow));

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

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    private static String current_date() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        
        return dateFormat.format(cal.getTime());

    }
    /*--------------------------------------------------------------------------------------------*/

    private static String tomorrow() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();

        // get tomorrow's date
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        return tomorrow.toString();

    }
    /*--------------------------------------------------------------------------------------------*/
    private static String format_gmt_datetime(String str) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date date = formatter.parse(str);

        String formattedString = formatter.format(date);
        Date newDate = formatter.parse(formattedString);

        formatter = new SimpleDateFormat("yyyyMMdd");
        formattedString = formatter.format(newDate);

        return formattedString;
    }

    private static void line() {
        char[] chars = new char[80];
        Arrays.fill(chars, '-');
        System.out.println(String.valueOf(chars));
    }
    /*--------------------------------------------------------------------------------------------*/
}
