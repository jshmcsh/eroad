/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.eroad.server.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author CP
 */
public class Md5 {

    public static String resultPassword;
    public Md5(){}
    public static String convertToMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte by[] = md.digest();

            StringBuffer sb = new StringBuffer("");
            for (int off = 0; off < by.length; off++) {
                int i = by[off];
                if (i < 0) {
                    i += 256;
                }
                if (i < 32) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            resultPassword = sb.toString().substring(0, 32);
				//System.out.println("加密后的结果是:"+resultPassword);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return resultPassword;
    }
}
