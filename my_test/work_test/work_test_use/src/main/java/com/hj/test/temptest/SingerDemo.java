package com.hj.test.temptest;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

/**
 * 
 * 数字签名生成与验证demo，包括生成DSA私钥/公钥，生成数字签名，验证数字签名. <li>
 * 订购接口WABP方会将WABP方的公钥，提前提供给各AP，供接口交互时校验WABP发送的数据及签名正确性.<li>
 * 退订接口AP提前将公钥提供给WABP侧，供接口交互时校验AP发送的数据及签名正确性.
 * 
 * @ClassName: SingerDemo
 * @author yanhuajian
 * @Email yanhuajian@aspirecn.com
 * @date:2013-8-20 上午10:11:15
 * @version V1.2
 * @modify 封装生成数字签名、验证数字签名方法，完善注释 yanhuajian 2013-9-6 11:24:15 v1.2
 * 
 */
public class SingerDemo {

	/**
	 * DSA密钥长度，DSA算法的默认密钥长度是1024 密钥长度必须是64的倍数，在512到1024位之间
	 */
	private static final int KEY_SIZE = 1024;
	// 公钥
	private static final String PUBLIC = "DSAPublicKey";
	// 私钥
	private static final String PRIVATE = "DSAPrivateKey";
	// 签名
	private static final String SIGN = "sign";
	// 签名方式
	private static final String SIGN_METHOD = "signMethod";
	// 默认字符编码
	private static final String DEFAULT_CHARSET = "GBK";
	// 默认加密算法
	private static final String DEFAULT_SIGN_METHOD = "DSA";
	/*
	 * 公钥：MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAM1ZZs1uxBO4a1erg5UYE+7GW66B0arRV5js/aPDjBU0BypPC3VzOcv3/tZrG4cTrkFlxjCaQ0oFSuaeCnbDksYBYlJuG5EUCqhXdoKJpj5DJmpNYlb8M6i5McRFSyzPtJVk7soIQf8BbWZpnFaVE9jd0cRBlgSV3fhWL7i8ZUmc
私钥：MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUdkt5tfynQXXIm4Gd1gDgn19jvPo=

	 */
	public static String PUBLIC_KEY="MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGALLjPx8t07+W4Rg2IUuEuA/VpfWToj5PPnr6r21CASiUd03v1f0pFVANujknM1y/h2WBpZZRNmM3CowFz7tRqziIGEFyw9xxYR8cdiq9vUN0Bjrt6z2mppF3IOQggdMtlYX4t4jX+/v3qufDI43xCJbn3MGH66cocCJsyc5aOqG4=";
	public static String PRIVATE_KEY="MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUdkt5tfynQXXIm4Gd1gDgn19jvPo=";

	public static void main(String[] args) throws Exception {
		// 数字签名
		// String signtest =
		// "MCwCFAcNdT/ii1fkWRbzeRH5yXZ/hd1QAhQwO3K4hdrbnTzK+phIQ7NeK93Ebw==";
		// 公钥
		// String publicKeyStr =
		// "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBANXuKL54pUJLAE2thFIudDtTVG+mKdw0qxDDVPeonrsTrx+3MrqkDNbUFUdgeQrW+KSYSydMgfUkNzvx4Pp1ETh7KZfrCYHJr+tMeC3BpzAHW34or4Ge8GDeBt58Yrx828Cc5pyUWASdruthnck5Ch2lzBE1nvd6qKbXhEtWvWt7";

		// 取得公钥/私钥
		Map<String, String> keyMap = genkeys();

		// 模拟数据
		Map<String, String> map = getDataMap("1000000001", "10959", "31357",
				"31", "10960", "1", "1001057441", "0", "23875310451", "21",
				"2013-09-03 09:50:39", null);
		Gson gson = new Gson ();
		System.out.println(gson.toJson(map));
		// 移除非签名参数
		map.remove(SIGN);
		map.remove(SIGN_METHOD);
		// 生成数字签名字符串(用户退订时，将数字签名传给WABP)
		String signStr = buildSign(keyMap.get(PRIVATE), map);
		// 验证数字签名(用于订购时，验证WABP的数字签名)
		boolean isSign = verifySign(signStr, keyMap.get(PUBLIC), map);

		System.out.println(isSign);

	}

	/**
	 * 
	 * 生成数字签名字符串
	 * 
	 * @Title: buildSign
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待校验数据
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:37:30
	 */
	public static String buildSign(String privateKey, Map<String, String> data)
			throws Exception {

		// 按照标准url参数的形式组装签名源字符串
		String stringToSign = map2String(data);
		// 转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);

		// 初始化DSA签名工具
		Signature sg = Signature.getInstance("DSA");
		// 初始化DSA私钥
		sg.initSign((PrivateKey) getPrivateKey(privateKey));
		sg.update(bytesToSign);

		// 得到二进制形式的签名
		byte[] signBytes = sg.sign();
		// 进行标准Base64编码
		byte[] sign = Base64.encodeBase64(signBytes);
		// 转换成签名字符串
		String signContent = new String(sign);

		System.out.println("the sign content is: " + signContent);

		return signContent;
	}

	/**
	 * 
	 * 根据数字签名和公钥验证签名是否正确
	 * 
	 * @Title: verifySign
	 * 
	 * @param sign
	 *            数字签名
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            待校验数据
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:37:55
	 */
	public static boolean verifySign(String sign, String publicKey,
			Map<String, String> data) throws Exception {
		// 将map转换为url参数形式
		String stringToSign = map2String(data);
		System.out.println(stringToSign);
		// 将参数字符串转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);
		System.out.println(bytesToSign);
		// 将数字签名符串转换成二进制
		byte[] signBytes = Base64.decodeBase64(sign.getBytes(DEFAULT_CHARSET));

		// 初始化DSA签名工具
		Signature sg = Signature.getInstance(DEFAULT_SIGN_METHOD);
		// 初始化DSA私钥
		sg.initVerify(getPublicKey(publicKey));
		sg.update(bytesToSign);
		// 验证签名
		boolean status = sg.verify(signBytes);
		System.out.println("verify result：" + status);

		return status;
	}

	/**
	 * 通过公钥字符串初始化DSA的公钥
	 * 
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(String publicKeyStr) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_SIGN_METHOD);
		EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(publicKeyStr.getBytes(DEFAULT_CHARSET)));
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 通过私钥字符串初始化DSA的私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String privateKeyStr)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_SIGN_METHOD);
		EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(privateKeyStr.getBytes(DEFAULT_CHARSET)));
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 初始化密钥对
	 * 
	 * @return Map 甲方密钥的Map
	 * */
	public static Map<String, Key> initKey() throws Exception {
		// 实例化密钥生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(DEFAULT_SIGN_METHOD);
		// 初始化密钥生成器
		keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
		// 甲方私钥
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();

		// 将密钥存储在map中
		Map<String, Key> keyMap = new HashMap<String, Key>();
		keyMap.put(PUBLIC, publicKey);
		keyMap.put(PRIVATE, privateKey);
		return keyMap;

	}

	/**
	 * 
	 * 通过公钥/私钥(KEY)生成对应的字符串
	 * 
	 * @Title: genkeys
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:33:31
	 */
	public static Map<String, String> genkeys() throws Exception {
		// 初始化密钥
		// 生成密钥对
		Map<String, Key> keyMap = initKey();
		// 公钥
		Key publickey = (Key) keyMap.get(PUBLIC);
		Key privatekey = (Key) keyMap.get(PRIVATE);

		String publicStr = new String(Base64.encodeBase64(publickey
				.getEncoded()));
		String privateStr = new String(Base64.encodeBase64(privatekey
				.getEncoded()));
		System.out.println("公钥：" + publicStr);
		System.out.println("私钥：" + privateStr);

		Map<String, String> map = new HashMap<String, String>();
		map.put(PUBLIC, publicStr);
		map.put(PRIVATE, privateStr);
		return map;
	}

	/**
	 * 
	 * 将map转换为url格式字符串
	 * 
	 * @Title: map2String
	 * @param map
	 * @return
	 * @author: yanhuajian 2013-7-21下午7:25:08
	 */
	public static String map2String(Map<String, String> map) {
		if (null == map || map.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		return sb.toString();
	}

	/**
	 * 简单组装map(未做封装)
	 */
	public static Map<String, String> getDataMap(String apTransactionID,
			String apId, String serviceId, String serviceType,
			String channelId, String apContentId, String apUserId,
			String orderType, String msisdn, String province,
			String actiontime, String method) {

		Map<String, String> map = new TreeMap<String, String>(); // 用treeMap按照key做排序
		map.put("APTransactionID", apTransactionID);
		map.put("APId", apId);
		map.put("ServiceId", serviceId);
		map.put("ServiceType", serviceType);
		map.put("ChannelId", channelId);
		map.put("APContentId", apContentId);
		map.put("APUserId", apUserId);
		map.put("OrderType", orderType);
		map.put("Msisdn", msisdn);
		map.put("Province", province);
		map.put("Actiontime", actiontime);
		map.put("method", method);
		map.put("Backup1", null);
		map.put("Backup2", null);

		return map;
	}

}
