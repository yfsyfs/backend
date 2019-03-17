package com.hikvision.nanchang.doorguardblacklist.common;

import java.util.List;

/**
* 海康威视数字技术股份有限公司 南昌分公司
* @Description: 分页对象
* @author xiexin6  
* @date 2019年3月6日  
*  
* @param <T>
 */
public class Page<T> {

	/**查询第几页*/
	private Integer cur;

	/**每页几条*/
	private Integer size;

	/**总共几页*/
	private Integer tot;

	/**总共几条数据*/
	private Integer sum;

	/**查询结果*/
	private List<T> data;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getCur() {
		return cur == null ? 1 : cur;
	}

	public void setCur(Integer cur) {
		this.cur = cur;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTot() {
		return (sum - 1) / size + 1;
	}

	public void setTot(Integer tot) {
		this.tot = tot;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	* @Description: 取得偏移量   
	* @param @return
	* @return Integer 
	* @throws
	 */
	public Integer getOffset() {
		return (this.getCur() - 1) * size;
	}

}
