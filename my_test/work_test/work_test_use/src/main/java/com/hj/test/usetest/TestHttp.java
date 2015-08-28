package com.hj.test.usetest;

import java.net.URLDecoder;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.hj.test.tools.HttpClientTools;
import com.hj.test.tools.UrlMapConvert;

public class TestHttp {

	@Test
	public void testGet() throws Exception {
		String url = "http://pay.ireader.com:32000/momr/third/chinaMobileMM/pay?orderId=14073121118052&usr=i319858460&fee=5&bizType=PPV&phone=18893287009&callbackUrl=http%3A%2F%2Fah2.zhangyue.com%2Fzybook3%2Fapp%2Fapp.php%3Fca%3DRecharge.Finish%26fee%3D5%26phone%3D18893287009%26showtitle%3D1%26usr%3Di319858460%26rgt%3D7%26p1%3D140628193216636871%26p2%3D108817%26p3%3D6300%26p4%3D501603%26p5%3D12%26p6%3DIJIGABBCIIHAAJIGEECI%26p7%3DIGACBJACDDJFICA%26p9%3D1%26p15%3DT9508%26p16%3DT9508%26p19%3Direader_2.3.0%26p21%3D1%26p22%3D4.2.2%26p23%3DT9508_V2.00%26rechargeType%3Dsms.mobile_mm%26spNum%3Dyidongmm%26smsType%3Dmobile%26orderId%3D14073121118052";
		String response = HttpClientTools.sendGet(url);
		System.out.println(response);
	}

	@Test
	public void testPostA() throws Exception {
//		String url = "http://localhost:8080/momr/third/chinaMobileMM/confirmPay";
//		String param = "<?xml version=\"1.0\" encoding=\"gbk\"?><VertifyUserState2APReq><APTransactionID>1512446323</APTransactionID><APId>10959</APId><ServiceId>20472</ServiceId><ServiceType>32</ServiceType><ChannelId>10960</ChannelId><APContentId>ireader</APContentId><APUserId>MTIzNDU2</APUserId><OrderType>0</OrderType><Actiontime>2014-06-23 11:45:03</Actiontime><method /><signMethod>DSA</signMethod><sign>MCwCFB8BWXRqHEwptTKhTpNhuYp+s9wMAhRjD9u/bhIQI2PkdwSf4tN459ohng==</sign><Msisdn>23412056497</Msisdn><Province>10</Province><Backup1 /><Backup2 /></VertifyUserState2APReq>";
//		String url = "http://pay.ireader.com:32000/momr/third/chinaMobileMM/confirmPay";
//		String param = "<?xml version=\"1.0\" encoding=\"gbk\"?><VertifyUserState2APReq><APTransactionID>14073121149347</APTransactionID><APId>20798</APId><ServiceId>20801</ServiceId><ServiceType>32</ServiceType><ChannelId>20809</ChannelId><APContentId>ireader</APContentId><APUserId>aTE4MTE4NzA5Mg==</APUserId><OrderType>0</OrderType><Actiontime>2014-07-31 01:07:44</Actiontime><method /><signMethod>DSA</signMethod><sign>MCwCFFMpADgYa/Cs+Ny7pscE6c46WgwXAhQ6S/FJPAatbBA9G/rhB6TjrI6xig==</sign><Msisdn>23785559458</Msisdn><Province>931</Province><Backup1 /><Backup2 /></VertifyUserState2APReq>";
		String url = "http://pay.ireader.com:32000/momr/third/chinaMobileMM/notifyReceive";
		String param = "<?xml version=\"1.0\" encoding=\"gbk\"?><ServiceWebTransfer2APReq><APTransactionID>14073121149347</APTransactionID><APId>20798</APId><ServiceId>20801</ServiceId><ServiceType>32</ServiceType><ChannelId>20809</ChannelId><APContentId>ireader</APContentId><APUserId>aTE4MTE4NzA5Mg==</APUserId><OrderType>0</OrderType><Actiontime>2014-07-31 01:07:44</Actiontime><ServiceAction>1</ServiceAction><method /><signMethod>DSA</signMethod><sign>MCwCFGfEetdBMvZa5WJjwRGjW14anRUzAhRBPiKcRdP43HuL29+kZqeC1U3RIQ==</sign><Msisdn>23785559458</Msisdn><Province>931</Province><Backup1 /><Backup2 /></ServiceWebTransfer2APReq>";
						
		String response = HttpClientTools.sendPost(url, param);
		System.out.println(response);
	}
	
	@Test
	public void testJson() throws Exception {
		String str = "notify_id=IM20140731000144518&partner_code=20242209&partner_order=14073121111589&orders=%E6%8E%8C%E9%98%85%E9%98%85%E9%A5%BC102024220914073121111589&pay_result=OK&sign=BPCKXrM2rMQbKp2SxpCkQd8mkW9Skl3PTWxXVZI8Vo1KM5rEYqZLVp6CiFsc335WtOkdqKW0ZIiPTwYukb7kIgrc6UUOE9oka6zwiMqqU8%2FGj9agwbUYJ9NTODYicqqLymHc9FscgMuiUuZcZeqehtXCQTtj6DAivG1j4MWOZS4%3D&payChannel=dnapay&amount=10&systemOrder=20140731000058742520";
		str = URLDecoder.decode(str);
		Map<String, String> map = UrlMapConvert.url2Map(str);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
	}
}
