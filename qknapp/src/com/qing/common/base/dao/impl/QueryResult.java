package com.qing.common.base.dao.impl;

import java.util.List;

import com.qing.common.util.Page;

public class QueryResult<T> extends Page<T> {

	/**  
	 * 当前页号  
	 */
	private Integer pageNo = 1;

	/**  
	 * 每页记录数  
	 */
	private int pageSize = 15;
	/**
	 * 唯一ID  用于页面分页标签
	 */
	private Long id;
	/**
	 * 总页数
	 */
	private Integer totalPage = 0;
	/**
	 * 总记录数
	 */
	private Integer totalCount = 0;
	/**
	 * 记录集合
	 */
	private List<T> result;

	private Integer nextPage = 0;
	private Integer prePage = 0;

	/**
	 * 默认构造函数
	 */
	public QueryResult() {
		super();
	}

	/**
	 * 构造函数
	 * @param totalCount  记录总数
	 * @param pageNo       当前页号
	 * @param pageSize     每页记录数
	 * @param result   记录集合
	 */
	public QueryResult(Integer totalCount, Integer pageNo, Integer pageSize, List<T> result) {
		this.setPageSize(pageSize);
		this.setPageNo(pageNo);
		this.result = result;
		this.setTotalCount(totalCount);
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int getNextPage() {
		this.nextPage = this.pageNo >= this.totalPage ? this.totalPage : (this.pageNo + 1);
		return this.nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public int getPrePage() {
		this.prePage = this.pageNo <= 1 ? 1 : this.pageNo - 1;
		return this.prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	@Override
	public Integer getPageNo() {
		this.pageNo = this.pageNo <= this.totalPage ? this.pageNo : this.totalPage;
		return this.pageNo;
	}

	@Override
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	/**
	* 计算总页数.
	*/
	public Integer countTotalPages() {
		if (this.totalCount == -1) {
			return -1;
		}
		int count = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize > 0) {
			count++;
		}
		return new Integer(count);
	}

	@Override
	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		this.setTotalPage(this.countTotalPages());
	}

	@Override
	public List<T> getResult() {
		return this.result;
	}

	@Override
	public void setResult(List<T> result) {
		this.result = result;
	}

}