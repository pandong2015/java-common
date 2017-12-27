package com.pandong.common.units;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
  private static MessageDigest md5 = null;

  static {
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("no md5 algorythm found");
    }
  }

  public static byte[] getMd5(String data) {
    return getMd5(data.getBytes());
  }

  public static byte[] getMd5(byte[] data) {
    if (md5 == null) {
      throw new NullPointerException("MessageDigest is null!");
    }
    md5.reset();
    md5.update(data);
    return md5.digest();
  }

  public static String getMd5Str(String data) {
    return toString(getMd5(data));
  }

  public static String getMd5Str(byte[] data) {
    return toString(getMd5(data));
  }

  public static byte[] getFileMd5(String file) throws IOException {
    return getFileMd5(Paths.get(file));
  }

  public static byte[] getFileMd5(Path file) throws IOException {
    if (md5 == null) {
      throw new NullPointerException("MessageDigest is null!");
    }
    byte[] buffer = new byte[8192];
    try (DigestInputStream dis = new DigestInputStream(Files.newInputStream(file), md5)) {
      while (dis.read(buffer) != -1) ;
    }
    return md5.digest();
  }

  public static String getFileMd5Str(String file) throws IOException {
    return toString(getFileMd5(Paths.get(file)));
  }

  public static String getFileMd5Str(Path file) throws IOException {
    return toString(getFileMd5(file));
  }

  private static String toString(byte[] data){
    return new BigInteger(1, data).toString(16);
  }
}
