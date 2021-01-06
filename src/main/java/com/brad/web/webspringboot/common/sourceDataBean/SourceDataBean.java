package com.brad.web.webspringboot.common.sourceDataBean;

import java.io.Serializable;


/**
 * 
* <p>Title:SourceDataBean </p>
* <p>Description:
* [{
	"head":{
		"token":"登陆令牌",
		"userName":"用户名",
		"userId":"用户ID",
		"systemCode":"系统编号",
		"sourceId":"业务ID",
		"callType":"业务ID关联的接口类型",
		"jobId":"流水号",
		"sync":"同步异步选项",
		"fromTime":"前台发送的时间",
		"toTime":"后台发送的时间",
		"code":"后台信息错误的编号",
		"msg":"后台信息错误的详细信息"	
	},
	"main":{
		"dataList":{
			"dataSource":{
				"list":["业务数据结果集"],
				"pageNum":"第几页",
				"total":"总记录数",
				"pageSize":"每页记录数",
				"pages":"总页数",
				"size":"当前页的数量"
			},
			"desc":{
				"name":"描述value"
			},
			"dataSourceId":"数据源的ID"			
		},
		"common":{
			"query":{"name":"查询条件value"}
		},
		"other":{
			"name":"特殊处理的value"
		}
			
		
	}

}]
*  
*  </p>
* <p>Company: 前后台交互数据bean</p>
* @author liyiye
 */
public class SourceDataBean<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7207313141313718041L;

	private String code
			;
	
	private String msg;
	

	private DataSource<T> dataSource;
	
	private TotalRow totalRow;
	
	//private List<T> data;
	
	public TotalRow getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(TotalRow totalRow) {
		this.totalRow = totalRow;
	}

	private Integer count;

	public void setMessage(String code,String msg){
		this.code = code;
		this.msg= msg;
	}
	

	public String getErrcode() {
		return code;
	}

	public void setErrcode(String code) {
		this.code = code;
	}

	public String getErrmsg() {
		return msg;
	}

	public void setErrmsg(String msg) {
		this.msg = msg;
	}

	public DataSource<T> getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource<T> dataSource) {
		this.dataSource = dataSource;
	//	this.data = dataSource.getList();
		this.count = dataSource.getTotal();
	}

	public void setDataSource(DataSource<T> dataSource,String noData) {
		this.dataSource = dataSource;
		this.count = dataSource.getTotal();
	}
//	public List<T> getData() {
//		return data;
//	}
//
//	public void setData(List<T> data) {
//		this.data = data;
//	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCode() {
		if(this.code.equals("200")){
			return "0";
		}else{
			return "1";
		}

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}