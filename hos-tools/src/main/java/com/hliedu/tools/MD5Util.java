package com.hliedu.tools;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

public class MD5Util {
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 
	
	public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
	

	/**
	 * 加盐MD5 szq add by 2019/6/11
	 */
	public static String saltMD5(String password) {
		String salt = genSalt();
 		password = MD5(password + salt);
 		char[] cs = new char[48];
 		for (int i = 0; i < 48; i += 3) {
 			cs[i] = password.charAt(i / 3 * 2);
 			char c = salt.charAt(i / 3);
 			cs[i + 1] = c;
 			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
 		}
		return new String(cs);
	}


	/**
	 * @return
	 */
	private static String genSalt() {
		Random r = new Random();
 		StringBuilder sb = new StringBuilder(16);
 		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
 		int len = sb.length();
 		if (len < 16) {
 			for (int i = 0; i < 16 - len; i++) {
 				sb.append("0");
 			}
 		}
 		String salt = sb.toString();
		return salt;
	}

	/**
	 * 校验
	 */
	public static boolean saltMD5Verify(String password, String md5) {
 		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return MD5(password + salt).equals(new String(cs1));
	}
	
	
	public static String MD5Encode(String origin, String charsetname) {  
        String resultString = null;  
        try {  
            resultString = new String(origin);  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            if (charsetname == null || "".equals(charsetname)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
			}
        }  
        catch (Exception exception) {  
        }  
        return resultString;  
    }
	
	
	private static String byteArrayToHexString(byte b[]) {  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++)  
            resultSb.append(byteToHexString(b[i]));  
  
        return resultSb.toString();  
    }
	
	
	private static String byteToHexString(byte b) {  
        int n = b;  
        if (n < 0) {
			n += 256;
		}
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    } 
	
	public static void main(String[] args) {
		System.out.println(saltMD5("123456"));
//		UUID randomUUID = UUID.randomUUID();
//		String replace = randomUUID.toString().replace("-", "");
//		System.out.println(replace.toUpperCase());
	}

}
