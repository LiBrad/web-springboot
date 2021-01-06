package com.brad.web.webspringboot.common.sourceDataBean;

import java.io.Serializable;
import java.util.List;

/**
 * 数据列表
 * @author liyiye
 *
 */
public class DataSource<T>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220569140881988297L;
	public DataSource(){}

	private List<T> list;
	private Integer total;
		private Integer pageNum;
	private Integer pageSize;
	
	public DataSource(List<T> list,Integer total,Integer pageNum,Integer pageSize) {
		this.setList(list);
		this.setTotal(total);
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 1;
		}
		this.setPageNum(pageNum);
		this.setPageSize(pageSize);
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
