package com.util;

import java.util.List;

/**
 * 封装搜索结果Bean
 *
 */
public class SearchResult<T>{
	//文档
	private List<T> results;
	//数据库记录
	private List<T> records;
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	private PageBean pageBean;
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

}
