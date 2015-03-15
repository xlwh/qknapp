/**
 *  Copyright (c) 2011, Eryptogram.java TAIHEIOT and/or its affiliates. All rights reserved.
 *
 *  Licensed under the TAIHEIOT License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.qing.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Title: 分页查询参数<br>
 * Description: 分页查询参数<br>
 * CreateTime: 2012-08-06 16:39<br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 */
public class QueryParameter {

	public Integer pageNo = 1;

	public int pageSize = 15;

	protected String orderBy = null;

	protected boolean autoCount = false;

	/**
	 * 获得每页的记录数量,无默认值.
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 是否已设置每页的记录数量.
	 */
	public boolean isPageSizeSetted() {
		return this.pageSize > -1;
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public Integer getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		if (this.pageNo < 1 || this.pageSize < 1) {
			return -1;
		} else {
			return ((this.pageNo - 1) * this.pageSize);
		}
	}

	/**
	 * 是否已设置第一条记录记录在总结果集中的位置.
	 */
	public boolean isFirstSetted() {
		return (this.pageNo > 0 && this.pageSize > 0);
	}

	/**
	 * 获得排序字段,无默认值.
	 */
	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否已设置排序字段.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(this.orderBy);
	}

	/**
	 * 是否自动获取总页数,默认为false. 注意本属性仅于query by Criteria时有效,query by HQL时本属性无效.
	 */
	public boolean isAutoCount() {
		return this.autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}
}