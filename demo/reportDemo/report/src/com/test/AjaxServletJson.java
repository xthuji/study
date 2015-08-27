package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * map形式的多条数据
 * @author huji
 *
 */
public class AjaxServletJson extends HttpServlet {
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setContentType("text/json;charset=GBK");
		
		String page = request.getParameter("page");
		System.out.println(page);
		//{"page":"1","total":1,"records":"13","rows":[{"id":"13","cell":["13","2007-10-06","Client 3","1000.00","0.00","1000.00",null]},{"id":"12","cell":["12","2007-10-06","Client 2","700.00","140.00","840.00",null]},{"id":"11","cell":["11","2007-10-06","Client 1","600.00","120.00","720.00",null]},{"id":"10","cell":["10","2007-10-06","Client 2","100.00","20.00","120.00",null]},{"id":"9","cell":["9","2007-10-06","Client 1","200.00","40.00","240.00",null]},{"id":"8","cell":["8","2007-10-06","Client 3","200.00","0.00","200.00",null]},{"id":"7","cell":["7","2007-10-05","Client 2","120.00","12.00","134.00",null]},{"id":"6","cell":["6","2007-10-05","Client 1","50.00","10.00","60.00",""]},{"id":"5","cell":["5","2007-10-05","Client 3","100.00","0.00","100.00","no tax at all"]},{"id":"4","cell":["4","2007-10-04","Client 3","150.00","0.00","150.00","no tax"]},{"id":"3","cell":["3","2007-10-02","Client 2","300.00","60.00","360.00","note invoice 3 & and amp test"]},{"id":"2","cell":["2","2007-10-03","Client 1","200.00","40.00","240.00","note 2"]},{"id":"1","cell":["1","2007-10-01","Client 1","100.00","20.00","120.00","note 1"]}],"userdata":{"amount":3820,"tax":462,"total":4284,"name":"Totals:"}}
		String str = "{\"page\":\"1\",\"total\":1,\"records\":\"13\",\"rows\":[{\"id\":\"13\",\"cell\":[\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]},{\"id\":\"12\",\"cell\":[\"12\",\"2007-10-06\",\"Client 2\",\"700.00\",\"140.00\",\"840.00\",null]},{\"id\":\"11\",\"cell\":[\"11\",\"2007-10-06\",\"Client 1\",\"600.00\",\"120.00\",\"720.00\",null]},{\"id\":\"10\",\"cell\":[\"10\",\"2007-10-06\",\"Client 2\",\"100.00\",\"20.00\",\"120.00\",null]},{\"id\":\"9\",\"cell\":[\"9\",\"2007-10-06\",\"Client 1\",\"200.00\",\"40.00\",\"240.00\",null]},{\"id\":\"8\",\"cell\":[\"8\",\"2007-10-06\",\"Client 3\",\"200.00\",\"0.00\",\"200.00\",null]},{\"id\":\"7\",\"cell\":[\"7\",\"2007-10-05\",\"Client 2\",\"120.00\",\"12.00\",\"134.00\",null]},{\"id\":\"6\",\"cell\":[\"6\",\"2007-10-05\",\"Client 1\",\"50.00\",\"10.00\",\"60.00\",\"\"]},{\"id\":\"5\",\"cell\":[\"5\",\"2007-10-05\",\"Client 3\",\"100.00\",\"0.00\",\"100.00\",\"no tax at all\"]},{\"id\":\"4\",\"cell\":[\"4\",\"2007-10-04\",\"Client 3\",\"150.00\",\"0.00\",\"150.00\",\"no tax\"]},{\"id\":\"3\",\"cell\":[\"3\",\"2007-10-02\",\"Client 2\",\"300.00\",\"60.00\",\"360.00\",\"note invoice 3 & and amp test\"]},{\"id\":\"2\",\"cell\":[\"2\",\"2007-10-03\",\"Client 1\",\"200.00\",\"40.00\",\"240.00\",\"note 2\"]},{\"id\":\"1\",\"cell\":[\"1\",\"2007-10-01\",\"Client 1\",\"100.00\",\"20.00\",\"120.00\",\"note 1\"]}],\"userdata\":{\"amount\":3820,\"tax\":462,\"total\":4284,\"name\":\"Totals:\"}}";
		
		PrintWriter out = response.getWriter();
		out.write(str);// 注意这里向jsp输出的流，在script中的截获方法
		out.close();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}