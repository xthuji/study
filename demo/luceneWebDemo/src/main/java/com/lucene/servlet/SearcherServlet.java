package com.lucene.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 * 关键字搜索
 */
public class SearcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearcherServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	request.setCharacterEncoding("utf-8");
    	
    	String keyWord = request.getParameter("keyWord");
    	
    	File indexFile = new File(Constants.INDEX_STORE_PATH);
    	
    	FSDirectory dir = FSDirectory.open(indexFile);

    	IndexReader indexReader = DirectoryReader.open(dir);
    	
    	IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    	
    	Analyzer ana = new StandardAnalyzer(Constants.version);
    	
    	QueryParser queryParser = new QueryParser(Constants.version, "content", ana);
    	
    	ScoreDoc[] scoreDoc = null;
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			Query query = queryParser.parse(keyWord);
			
			TopDocs resultsDocs = indexSearcher.search(query, 100);
			
			scoreDoc = resultsDocs.scoreDocs;
			
			for (int i = 0; i < scoreDoc.length; i++) {
				int doc = scoreDoc[i].doc;
				Document mydoc = indexSearcher.doc(doc);
				String filePath =  mydoc.get("filepath");
				String content = mydoc.get("content");
				map.put(filePath, content.substring(0, 15) + "...");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("resultNum", String.valueOf(scoreDoc.length));
    	request.setAttribute("map", map);
    	//回显
    	request.setAttribute("keyWord", keyWord);
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
