package com.pandong.common.units;

public class HashUtil {

  public static long hashByMD5(String data) {
    return hash(MD5Util.getMd5(data));
  }

  public static long hash(byte[] data) {
    long res = ((long) (data[3] & 0xFF) << 24)
            | ((long) (data[2] & 0xFF) << 16)
            | ((long) (data[1] & 0xFF) << 8)
            | (long) (data[0] & 0xFF);
    return res & 0xffffffffL;
  }
}
