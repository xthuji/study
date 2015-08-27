package com.test.bean;

import java.io.Serializable;


public class FusionMapping implements Serializable {

	private static final long	serialVersionUID	= 6072299677377752394L;
	/**
	 * 省市code
	 */
	String						codeID				= "";
	/**
	 * map数据关联id
	 */
	String						mapID				= "";
	/**
	 * 省市名
	 */
	String						cnName				= "";
	/**
	 * 省份是模板名称/市是cityCode
	 */
	String						swfName				= "";
	/**
	 * 数据值
	 */
	String						value				= "0";
	/**
	 * 排名值
	 */
	int							sortNum				= 1;

	public FusionMapping() {

	}

	public FusionMapping(String codeID, String mapID, String cnName,
			String swfName) {
		this.codeID = codeID;
		this.mapID = mapID;
		this.cnName = cnName;
		this.swfName = swfName;
	}

	public FusionMapping(String codeID, String mapID, String cnName,
			String swfName, String value) {
		this.codeID = codeID;
		this.mapID = mapID;
		this.cnName = cnName;
		this.swfName = swfName;
		this.value = value;
	}

	public String getCodeID() {
		return codeID;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}

	public String getMapID() {
		return mapID;
	}

	public void setMapID(String mapID) {
		this.mapID = mapID;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getSwfName() {
		return swfName;
	}

	public void setSwfName(String swfName) {
		this.swfName = swfName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
