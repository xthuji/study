package com.lucene.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.lucene.servlet.Constants;

/**
 * Servlet implementation class CreateIndexServlet
 */
public class CreateIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		//创建索引
			createIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.setAttribute("msg", "索引创建成功！");
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
 	public void createIndex () throws Exception {
 		//存储建立索引的文件夹路径
 		Directory directory = FSDirectory.open(new File(Constants.INDEX_STORE_PATH));
 		//分词器版本
 		Analyzer ana = new StandardAnalyzer(Constants.version);
 		IndexWriterConfig config = new IndexWriterConfig(Constants.version, ana);
 		
 		config.setOpenMode(OpenMode.CREATE);
 		IndexWriter writer = new IndexWriter(directory, config);
 		
 		//读取要查询的文件，并建立索引文件
 		File[] files = new File(Constants.INDEX_FILE_PATH).listFiles();
 		
 		if (files.length > 0) {
 			long time1 = System.currentTimeMillis();
 			for (int i = 0; i < files.length; i++) {
 				Document document = getDocument(files[i]);
 				writer.addDocument(document);
 			}
 			long time2 = System.currentTimeMillis();
 			System.out.println("创建了: " + writer.numDocs() + " 索引");
 			System.out.println("一共花了: " + (time2 - time1) + " 毫秒");
 		}
 		writer.close();
 	}

     // 将要建立索引的文件构造成一个Document对象，并添加一个域"content"
 		private Document getDocument(File f) throws Exception {
 			Document doc = new Document();

 			//字符串 StringField LongField TextField
 			Field pathField = new StringField("filepath", f.getAbsolutePath(), Store.YES);
 			
 			BufferedReader reader = new BufferedReader(new InputStreamReader(
 					new FileInputStream(f), "GBK"));
 			String lineContent = "";//读取一行显示
 			StringBuffer sbstr = new StringBuffer(500);
 			while ((lineContent = reader.readLine() )!= null) {
 				sbstr.append(lineContent);
 			}
 			reader.close();
 			
 			Field contenField = new TextField("content", sbstr.toString(), Store.YES);
 			//添加字段
 			doc.add(contenField);
 			doc.add(pathField);
 			return doc;
 		}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
