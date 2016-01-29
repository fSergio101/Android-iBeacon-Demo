package com.radiusnetworks.ibeaconreference.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 27/1/16.
 */
public class Tools {

  public static String generateMd5(String string)
      throws UnsupportedEncodingException, NoSuchAlgorithmException {

      byte[] bytesOfMessage = string.getBytes("UTF-8");
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] thedigest = md.digest(bytesOfMessage);

      StringBuffer sb = new StringBuffer();

      for (int i = 0; i < thedigest.length; ++i) {
        sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1,3));
      }

      return sb.toString();
  }

}
