package com.gqgx.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求校验工具类
 * 
 * @author liufeng
 * @date 2013-09-01
 */
public class SignUtil {
    /**
     * 记录操作日志
     */
    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    


	/**
	 * 校验签名
	 * 
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce,String wetchtoken) {
		// 对token、timestamp和nonce按字典排序
		String[] paramArr = new String[] { wetchtoken, timestamp, nonce };
		Arrays.sort(paramArr);

		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		logger.info("================signature："+signature.toUpperCase()+"=================");
		logger.info("================echostr  ："+ciphertext+"=================");
		// 将sha1加密后的字符串与signature进行对比
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	
}
