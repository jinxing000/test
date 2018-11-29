package com.ef.golf.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    public static byte[] initSecretKey() {

        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128);
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    private static Key toKey(byte[] key) {
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    private static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
    /** 加密 */
    public static Map jia(String data) {
        byte[] key = initSecretKey();
       // Key k = toKey(key); //生成秘钥
        try {
            Map<String,Object>map = new HashMap<>();
            byte[] encryptData = encrypt(data.getBytes(), key);//数据加密
            System.out.println("加密前数据: string:"+data);
            System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
            map.put("encryptData",parseByte2HexStr(encryptData)+","+parseByte2HexStr(key));
            //map.put("k",k);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 解密 */
    public static byte[] jie(byte[] data,byte[] key) {
        try {
            byte[] decryptData = decrypt(data, key);//数据解密
            return decryptData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**将二进制转换成16进制
     * @param buf
     * @return
     */

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {

       /*Map<String,Object>map = AESCoder.jia("123");
       String bytes = (String)map.get("encryptData");
       String[] strArr=bytes.split(",");*/

      /* Key k = (Key) map.get("k");*/
       System.out.println(new String(AESCoder.jie(parseHexStr2Byte("8DB6914EF25F553B2AB44A445F2C6946"),parseHexStr2Byte("1029326CC2B0F719851400F76B874B4C"))));
       System.out.println("======");
       /*byte[] key = initSecretKey();
        System.out.println(parseByte2HexStr(key));
       System.out.println("key："+showByteArray(key));
       //Key k = toKey(key); //生成秘钥
       String data ="ABC";
       System.out.println("加密前数据: string:"+data);
       System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
       System.out.println();
      // byte[] encryptData = encrypt(data.getBytes(), k);//数据加密

        byte[] encryptData = encrypt(data.getBytes(), key);

       System.out.println("加密后数据: byte[]:"+showByteArray(encryptData));
//       System.out.println("加密后数据: hexStr:"+Hex.encodeHexStr(encryptData));
       System.out.println();
       byte[] decryptData = decrypt(encryptData, key);//数据解密
       System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
       System.out.println("解密后数据: string:"+new String(decryptData));*/
   }
}