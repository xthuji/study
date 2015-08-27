package com.hj.test.tools;

import java.io.IOException;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import com.hj.test.usetest.ReadFile;

public class ExcelUtils {
	/**
	 * 报表模板path的取得
	 * 
	 * @param templateName 模板名称
	 * @return 报表模板path
	 */
	public static String getReportTemplatePath(String templateName) {
    	String basePath = new ReadFile().getClass().getClassLoader()
				.getResource("").getPath();
		return basePath + templateName;
	}
    /**
     * 报表出力path的取得
     * 
     * @param reportName 报表名称
     * @return 报表出力path
     */
    public static String getReportOutPath(String reportName) {
    	String basePath = new ReadFile().getClass().getClassLoader()
				.getResource("").getPath();
		return basePath + reportName;
    }
    
    /**
     * 生成报表
     * @param srcFilePath 模板文件
     * @param data 生成的数据
     * @param destFilePath 生成的文件
     * @throws BaseException
     */
    public static void gen(String srcFilePath, @SuppressWarnings("rawtypes") Map data,
            String destFilePath) throws Exception {
        XLSTransformer transformer = new XLSTransformer();
        try {
            transformer.transformXLS(srcFilePath, data, destFilePath);
        } catch (ParsePropertyException e) {
            throw new Exception("生成表格异常ParsePropertyException", e);
        } catch (IOException e) {
            throw new Exception("生成表格异常IOException", e);
        }
    }
    
    /**
     * 导出EXCEL
     * @param templateName
     * @param data
     * @param outpurPath
     * @return
     */
	public static boolean createExcel(String templateName, Map data,
			String outpurPath) {
		try {
			gen(getReportTemplatePath(templateName),
					data, getReportOutPath(outpurPath));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 *  导出EXCEL
	 * @Description:
	 * @param templateName
	 * @param data
	 * @param outpurPath
	 * @param isFullPath   是否完整路径
	 * @return
	 */
	public static boolean createExcel(String templateName, Map data,
	    String outpurPath, boolean isFullPath) {
	    try {
	        if (isFullPath) {
                gen(templateName, data, outpurPath);
            }else {
                gen(getReportTemplatePath(templateName),
                        data, getReportOutPath(outpurPath));
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
}
