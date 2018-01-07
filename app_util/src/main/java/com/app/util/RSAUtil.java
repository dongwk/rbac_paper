package com.app.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


/**
 * RSAUtil 
 */
public class RSAUtil {
	/**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * UTF-8编码
     */
    private static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 签名算法
     */
    public enum SIGNATURE_ALGORITHM{
    	SHA1WithRSA("SHA1WithRSA"),
    	MD5withRSA("MD5withRSA");
    	public static SIGNATURE_ALGORITHM DEFAULT = SHA1WithRSA;
    	
    	private final String algorithm;
    	
    	SIGNATURE_ALGORITHM(String algorithm){
    		this.algorithm = algorithm;
    	}
    }
    
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception{
        KeyPairGenerator keyPairGen;
		keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /**
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey) throws Exception{
    	return sign(data, privateKey, SIGNATURE_ALGORITHM.DEFAULT);
    }
    
    /**
     * 
     * @param data
     * @param privateKey
     * @param sAlgorithm
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey, SIGNATURE_ALGORITHM sAlgorithm) throws Exception{
    	return sign(data, privateKey, sAlgorithm.algorithm);
    }
    
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     * 
     * @param data 参数
     * @param privateKey 私钥(BASE64编码)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey, String algorithm) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
		keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateK);
        signature.update(dataBytes);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @param sAlgorithm 签名算法
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception{
    	return verify(data, publicKey, sign, SIGNATURE_ALGORITHM.DEFAULT);
    }
    
    /**
     * <p>
     * 校验数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @param sAlgorithm 签名算法
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(String data, String publicKey, String sign, SIGNATURE_ALGORITHM sAlgorithm) throws Exception{
    	return verify(data, publicKey, sign, sAlgorithm.algorithm);
    }
    
    /**
     * <p>
     * 校验数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(String data, String publicKey, String sign, String algorithm) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
		keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicK);
        signature.update(dataBytes);
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        byte[] encryptedDataBytes = Base64.decodeBase64(encryptedData);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedDataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedDataBytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedDataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, CHARSET_UTF_8);	
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     */
    public static String decryptByPublicKey(String encryptedData, String publicKey) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        byte[] encryptedDataBytes = Base64.decodeBase64(encryptedData);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
        
		keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedDataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedDataBytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedDataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, CHARSET_UTF_8);

    }

    /**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     */
    public static String encryptByPublicKey(String data, String publicKey) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
        byte[] dataBytes = data.getBytes(CHARSET_UTF_8);
		keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     * 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     */
    public static String encryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory;
        byte[] dataBytes = data.getBytes(CHARSET_UTF_8);
		keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     * 
     * @param keyMap 密钥对
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     * 
     * @param keyMap 密钥对
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
    
    /**
     * 根据文件获取公钥
     */
    public static String getPubKeyFromCRT(String crtFileName) throws Exception {
    	InputStream is = new FileInputStream(crtFileName);
        CertificateFactory cf = CertificateFactory.getInstance("x509");
        Certificate cerCert = cf.generateCertificate(is);
        return Base64.encodeBase64String(cerCert.getPublicKey().getEncoded());
    }
    
    /**
     * 通过PFX文件获得私钥
     *
     * @param //文件路径
     * @param //PFX密码
     * @return PrivateKey
     */
    public static String getPviKeyFromPFX(String strPfx, String strPassword)
            throws Exception {
        PrivateKey prikey = null;
        char[] nPassword = null;
        if ((strPassword == null) || strPassword.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = strPassword.toCharArray();
        }
        KeyStore ks = getKsformPfx(strPfx, strPassword);
        String keyAlias = getAlsformPfx(strPfx, strPassword);
        prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        return Base64.encodeBase64String(prikey.getEncoded());
    }

    /**
     * 通过PFX文件获得KEYSTORE
     *
     * @param //文件路径
     * @param //PFX密码
     * @return KeyStore
     */
    public static KeyStore getKsformPfx(String strPfx, String strPassword)
            throws Exception {
        FileInputStream fis = null;

        KeyStore ks = KeyStore.getInstance("PKCS12");
        fis = new FileInputStream(strPfx);
        // If the keystore password is empty(""), then we have to set
        // to null, otherwise it won't work!!!
        char[] nPassword = null;
        if ((strPassword == null) || strPassword.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = strPassword.toCharArray();
        }
        ks.load(fis, nPassword);
        if (null != fis) {

            fis.close();

        }
        return ks;

    }
    
    /**
     * 通过PFX文件获得别名
     *
     * @param  //文件路径
     * @param //PFX密码
     * @return 别名
     */
    public static String getAlsformPfx(String strPfx, String strPassword)
            throws Exception {
        String keyAlias = null;
        KeyStore ks = getKsformPfx(strPfx, strPassword);
        Enumeration<String> enumas = ks.aliases();
        keyAlias = null;
        // we are readin just one certificate.
        if (enumas.hasMoreElements()) {
            keyAlias = (String) enumas.nextElement();
        }
        return keyAlias;
    }

    
	public static void main(String[] args) throws Exception {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSY3vttcxy2RHRSeN+A9piLoB3rQa5TA5vQZo/G1hWWEzj4O65H9mKwbLYpgjsV3ewP32EFXecPDqsRvxYXvjxeYD8p4xjfCC4dJbHMM8OxvtNCGlKam2mqKiY4J+IAhaXJWYyjrEu4ZM8lFV/UsqEg6VrxZSmRVWhoPQe0+F1owIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALGgEfxKuaOuluTFDMT/M2qbPeDnvfiYq9FcF6FDGD1Dz1cq1/OoDW9Rc6v3VOEDH7TsiJ4p/eq8Eug7qHKGhuqYZCBTX4DfRQ06kJKCvFyFiRMaCU4KPRnuExt2jrK2C6DNz4b9+hy5FQuN1rhwK76sh5kSDPSP5chIY6e4IjR9AgMBAAECgYEAi/BAOzPj7UCvQKy90rX8HBESPw5UbHZbZyXWuAXUIvA5ecMXP682Zm0VZHYHOP7tC+0N9Q3ALh7eBnh5zUCPKROWigKAQFaPn6GVI75aWJgv/olvBFHK/fKK1azqc6xmN2xkuj4La95K+w4F8LDmvUeuK5fZMq0EviwbJD/GvgECQQDcb5L6pQCM4nbCslg864CtqxqKOby/Q9Nul+PM9eyXfoEhYTyLEOpieXY3Cfg5b01+VPRz05exJwkEjdQuFAZJAkEAzkhWU1zMNRy/AA4d7zI+CsE4S/7HyfxKjloYhsUd54at0jYXgT3fY74gCzFZNW4Si/08vpE1fcQqW237EjYslQJBAK3QXHi+enycZUCIGAORPtmxvS+Y7zdxZ9ogckM+9ttNqMyFO5P8NpSHaatkdRH0rYobutVJ1qadPQMWQmRC7eECQBDbwJJXhyjgDIwIm1GtHXzrhZpQXCuZvs4bdt2sxmDhlNsVKJ6bW+Je62d+xPek40cljw1T+LcqjPuj5Vz7qHECQGO9pGn6t0Q8vZYtRp9QHRHvXr+vgytxa7SqRyLMmvh3HSDXQh2K4AalKMb6JbnxyjjmwFLSif+ZpaAz7gVQmkQ=";
		
		String bizContent = "{\"phone\":\"13311111111\", \"userKey\":123, \"isReg\":1, \"name\":\"13311111111\"}";
		
        //加密
        String encryptData = RSAUtil.encryptByPublicKey(bizContent, publicKey);

        //签名
		String signSource = "bizContent="+encryptData+"&timestamp="+System.currentTimeMillis();  
        String sign = RSAUtil.sign(signSource, privateKey); 
		String param = signSource+"&sign="+sign;

        System.out.println("加密前:         "+bizContent);
        System.out.println("签名前:         "+signSource);
        System.out.println("最终参数:       "+param);
        System.out.println("校验:       "+RSAUtil.verify(signSource, publicKey, sign));
	}
}