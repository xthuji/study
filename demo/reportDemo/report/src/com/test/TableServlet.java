package com.test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.test.bean.CustomBean;
/**
 * Jaspereport 生成pdf表格
 * @author huji
 *
 */
public class TableServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取模板文件路径
		String path = "WebRoot/reports";  
	    path = request.getSession().getServletContext().getRealPath("jaspereport/template");  
	    String reportName = "test";
	    File file = new File(path + "/" + reportName  + ".jasper");  
	    try {
	        //如果jasper文件不存在，就调用jrxml文件编译生成  
	        //JasperCompileManager.compileReportToFile(String sourceFileName, String destFileName)  
	        if (!file.exists()) {  
	            JasperCompileManager.compileReportToFile(path + "/"+ reportName + ".jrxml",path + "/"+ reportName + ".jasper");  
	        }  
	        //报表数据
	        List list = getDateList();
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);  
	        //此处为关键，将对象列表设为数据源            
	        JRDataSource dataSource  = new JRBeanCollectionDataSource(list);  
	        //因模版上没有传入参数，所以此处第二个参数为空  
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);  
	  
	        OutputStream ouputStream = response.getOutputStream(); 
	        // 设置相应参数，以附件形式保存PDF   
	        response.setContentType("application/pdf");   
	        response.setHeader("Content-Disposition", "attachment; filename=\""  
			         + URLEncoder.encode("表格", "UTF-8")   
			         + ".pdf\"");
	        // 使用JRPdfExproter导出器导出pdf   
	        JRPdfExporter exporter = new JRPdfExporter();  
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
	        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");  
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);   
	        exporter.exportReport();// 导出   
	        ouputStream.close();// 关闭流 
	          
	    } catch (JRException e) {
	    	e.printStackTrace();
	    }  

	}
	private List getDateList(){
		List<CustomBean> list = new ArrayList<CustomBean>();  
	    for(int i = 0 ;i<12;i++){  
	        CustomBean c = new CustomBean();  
	        c.setCity("city:"+i);  
	        c.setId(i+50);  
	        c.setName("name:"+i);  
	        list.add(c);  
	    } 
	    return list;
	}
}