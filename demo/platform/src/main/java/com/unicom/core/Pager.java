package com.unicom.core;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.unicom.po.Po;

/**
 * @Author qibaichao
 * @ClassName Pager
 * @Date Sep 9, 2013
 * @Description:分页查询类
 */
public class Pager extends Po {

	private Map<String, String>	f			= new HashMap<String, String>();
	private Order				o			= new Order();
	private List<Filter>		filtersList	= null;
	private String				pageFlag	= "pageFlag";
	private List				resultList;
	private int					pageNo		= 1;
	private int					pageRows	= 10;
	private int					totalCount	= -1;
	private int					totalPages;

	/**
	 * 添加过滤器,paramKey为request传递的parameter的key,对应页面表单中输入项的name,用于对原有查询进行限定
	 * 
	 * @param paramKey
	 * @param filter
	 */
	public void addF(String paramKey, String value) {
		if (!StringUtils.isEmpty(value)) {
			this.f.put(paramKey, value);
		}
	}

	/**
	 * 得到过滤条件列表
	 * 
	 * @return
	 */
	public List<Filter> getFilterList() {
		if (this.filtersList == null) {
			filtersList = new ArrayList();
			Set<Entry<String, String>> entrySet = this.f.entrySet();
			for (Entry<String, String> entry : entrySet) {
				filtersList.add(new Filter(entry.getKey(), entry.getValue()));
			}
		}
		return filtersList;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 当前页.
	 */
	public int getPageNo() {
		if (pageNo < 1)
			pageNo = 1;
		// if(pageNo>getTotalPages()){
		// pageNo=getTotalPages();
		// }
		return pageNo;
	}

	/**
	 * 设置当前页.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

	}

	/**
	 * 首记录.
	 */
	public int getFirst() {
		return ((getPageNo() - 1) * pageRows) + 1;
	}

	/**
	 * 尾记录.
	 */
	public int getLast() {
		return (getFirst() + pageRows) > getTotalCount() ? getTotalCount()
				: (getFirst() + pageRows - 1);
	}

	public int getTotalPages() {
		int count = (totalCount + pageRows - 1) / pageRows;
		return count;
	}

	public void setTotalPages(int totalPages) {
		if (totalPages == 0)
			totalPages = 1;
		this.totalPages = totalPages;
	}

	/**
	 * 总记录数
	 */
	public void resetTotalCount(int totalCount) {
		if (totalCount < 0) {
			totalCount = 0;
		}
		int count = (totalCount + pageRows - 1) / pageRows;
		setTotalCount(totalCount);
		setTotalPages(count);
		if (getPageNo() > getTotalPages()) {
			setPageNo(getTotalPages());
		}

	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 下一页页号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		}
		else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 上一页页号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		}
		else {
			return pageNo;
		}
	}

	/**
	 * 供后台查询调用 判断最后一页数据大小
	 * 
	 * @return
	 */
	public int getBeginCount() {
		int beginCount = (getPageNo() - 1) * pageRows;
		if (beginCount >= this.getTotalCount()) {
			int modpageSize = this.getTotalCount() % this.pageRows;
			if (modpageSize == 0) {
				modpageSize = this.pageRows;
			}
			beginCount = this.getTotalCount() + 1 - modpageSize;
		}
		return beginCount < 0 ? 0 : beginCount;
	}

	private String	url;

	public String getUrl() {
		if (url == null) {
			StringBuffer sbUrl = new StringBuffer();
			Set<Entry<String, String>> entrySet = this.f.entrySet();
			for (Entry<String, String> entry : entrySet) {
				sbUrl.append("&").append("f[").append(entry.getKey())
						.append("]").append("=")
						.append(URLEncoder.encode(entry.getValue()));
			}
			// sbUrl.append("&").append("pageRows=").append(getPageRows());
			// url = sbUrl.substring(1).toString();
			url = sbUrl.toString();
		}
		return url;
	}

	private String	fullUrl;

	public String getFullUrl() {

		if (fullUrl == null) {
			StringBuffer sbUrl = new StringBuffer();
			if (o.getField() != null && o.getSort() != null)
				sbUrl.append("&o.field=")
						.append(URLEncoder.encode(o.getField()))
						.append("&o.sort=")
						.append(URLEncoder.encode(o.getSort()));

			fullUrl = getUrl() + sbUrl.toString();
		}
		return fullUrl;
	}

	public Map<String, String> getF() {
		return f;
	}

	public void setF(Map<String, String> f) {
		this.f = f;
	}

	public Order getO() {
		return o;
	}

	public void setO(Order o) {
		this.o = o;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

}