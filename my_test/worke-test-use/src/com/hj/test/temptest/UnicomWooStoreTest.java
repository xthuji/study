package com.hj.test.temptest;

import org.junit.Before;
import org.junit.Test;

import com.hj.test.tools.HttpUtils;

public class UnicomWooStoreTest {
	//localhost
	public static String pay_url = "http://localhost:8080/momr/third/unicomStore/userPayWap?fee=10&orderId=14080522006634&callBackUrl=http%3A%2F%2Fah2.zhangyue.com%2Fzybook3%2Fapp%2Fapp.php%3Fca%3DRecharge.Finish%26fee%3D10%26phone%3D18696320577%26showtitle%3D1%26usr%3Di271985430%26rgt%3D7%26p1%3Dnull%26p2%3D108717%26p3%3D6700%26p4%3D501603%26p5%3D16%26p6%3DIJIGABBDIHBACDDBDJHF%26p7%3DIGEDJFACICGJEBC%26p9%3D1%26p15%3Dvivo%2BY17T%26p16%3Dvivo%2BY17T%26p19%3Direader_2.7.0%26p21%3D3%26p23%3DALPS.JB5.MP.V1.6%26zysid%3D0218a19dde4ac50add69723a5cb965a6%26zysign%3DOrbLmvcwo4iNFvSjoVpp0Hlnxo8oD38bMUt2%252FbpIkHpzujHDUcJJBCxtAlu4di8S4%252BhjTBAMdMiS9JlNCcSeyA%253D%253D%26orderId%3D14080522006634%26rechargeType%3Dsms.wooWap%26smsType%3Dunicom%26spNum%3DWooWap&channel=108717&version=6700&loginName=i271985430&_php_=/zybook3/";
	public static String validate_url = "http://localhost:8080/momr/third/unicomStore/notifyReceive?serviceid=validateorderid";
	public static String notify_url = "http://localhost:8080/momr/third/unicomStore/notifyReceive";

			
	private void gray() {
		//3200
		pay_url = "http://pay.ireader.com:32000/momr/third/unicomStore/userPayWap?fee=10&orderId=14080522006634&callBackUrl=http%3A%2F%2Fah2.zhangyue.com%2Fzybook3%2Fapp%2Fapp.php%3Fca%3DRecharge.Finish%26fee%3D10%26phone%3D18696320577%26showtitle%3D1%26usr%3Di271985430%26rgt%3D7%26p1%3Dnull%26p2%3D108717%26p3%3D6700%26p4%3D501603%26p5%3D16%26p6%3DIJIGABBDIHBACDDBDJHF%26p7%3DIGEDJFACICGJEBC%26p9%3D1%26p15%3Dvivo%2BY17T%26p16%3Dvivo%2BY17T%26p19%3Direader_2.7.0%26p21%3D3%26p23%3DALPS.JB5.MP.V1.6%26zysid%3D0218a19dde4ac50add69723a5cb965a6%26zysign%3DOrbLmvcwo4iNFvSjoVpp0Hlnxo8oD38bMUt2%252FbpIkHpzujHDUcJJBCxtAlu4di8S4%252BhjTBAMdMiS9JlNCcSeyA%253D%253D%26orderId%3D14080522006634%26rechargeType%3Dsms.wooWap%26smsType%3Dunicom%26spNum%3DWooWap&channel=108717&version=6700&loginName=i271985430&_php_=/zybook3/";
		validate_url = "http://pay.ireader.com:32000/momr/third/unicomStore/notifyReceive?serviceid=validateorderid";
		notify_url = "http://pay.ireader.com:32000/third/unicomStore/notifyReceive";
	}
	private void prod() {
		//2900
		pay_url = "http://pay.ireader.com:29000/momr/third/unicomStore/userPayWap?fee=10&orderId=14080522006634&callBackUrl=http%3A%2F%2Fah2.zhangyue.com%2Fzybook3%2Fapp%2Fapp.php%3Fca%3DRecharge.Finish%26fee%3D10%26phone%3D18696320577%26showtitle%3D1%26usr%3Di271985430%26rgt%3D7%26p1%3Dnull%26p2%3D108717%26p3%3D6700%26p4%3D501603%26p5%3D16%26p6%3DIJIGABBDIHBACDDBDJHF%26p7%3DIGEDJFACICGJEBC%26p9%3D1%26p15%3Dvivo%2BY17T%26p16%3Dvivo%2BY17T%26p19%3Direader_2.7.0%26p21%3D3%26p23%3DALPS.JB5.MP.V1.6%26zysid%3D0218a19dde4ac50add69723a5cb965a6%26zysign%3DOrbLmvcwo4iNFvSjoVpp0Hlnxo8oD38bMUt2%252FbpIkHpzujHDUcJJBCxtAlu4di8S4%252BhjTBAMdMiS9JlNCcSeyA%253D%253D%26orderId%3D14080522006634%26rechargeType%3Dsms.wooWap%26smsType%3Dunicom%26spNum%3DWooWap&channel=108717&version=6700&loginName=i271985430&_php_=/zybook3/";
		validate_url = "http://pay.ireader.com:29000/momr/third/unicomStore/notifyReceive?serviceid=validateorderid";
		notify_url = "http://pay.ireader.com:29000/third/unicomStore/notifyReceive";
	}
	
	@Before
	public void init() throws Exception {
//		gray();
		prod();
	}
	
	@Test
	public void testName() throws Exception {
		String result = HttpUtils.httpGet(pay_url);
		System.out.println(result);
	}
	@Test
	public void testName1() throws Exception {
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><checkOrderIdReq><orderid>000000000014080522006634</orderid><signMsg>7fc3653b91c6b95480ad5b18a49188ef</signMsg><usercode>1552eabe729bcb2b1fbd98b6f733601b</usercode><provinceid>4060</provinceid><cityid>728</cityid></checkOrderIdReq>";
		
		String result = HttpUtils.httpPost(validate_url, data, "utf-8", false, 5000);
		System.out.println(result);
	}
	@Test
	public void testName2() throws Exception {
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><callbackReq><orderid>000000000014080522006634</orderid><ordertime>20140805235956</ordertime><cpid>86000606</cpid><appid>9000612220131223112442717900</appid><fid>00016947</fid><consumeCode>131223019766</consumeCode><payfee>1000</payfee><payType>2</payType><hRet>0</hRet><status>00000</status><signMsg>1b7a0e685315a2158aafbcafd4d89995</signMsg></callbackReq>";
		
		String result = HttpUtils.httpPost(notify_url, data, "utf-8", false, 5000);
		System.out.println(result);
	}
}
