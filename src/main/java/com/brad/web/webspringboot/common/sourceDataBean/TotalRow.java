package com.brad.web.webspringboot.common.sourceDataBean;

import java.io.Serializable;

/**
 *
 */
public class TotalRow  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220569140881988297L;
	public TotalRow(){}


	private String goodCapacity;
	
	public String getGoodCapacity() {
		return goodCapacity;
	}

	public void setGoodCapacity(String goodCapacity) {
		this.goodCapacity = goodCapacity;
	}


}
