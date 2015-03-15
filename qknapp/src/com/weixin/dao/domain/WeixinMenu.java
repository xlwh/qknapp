package com.weixin.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TWeixinMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_WEIXIN_MENU")
public class WeixinMenu implements java.io.Serializable {

	private static final long serialVersionUID = 5747463622688508571L;
	// Fields

	private String id;
	private String menuName;
	private Integer sort;
	private String url;
	private Integer open;
	private String fatherId;

	// Constructors

	/** default constructor */
	public WeixinMenu() {
	}

	/** full constructor */
	public WeixinMenu(String menuName, Integer sort, String url, Integer open, String fatherId) {
		this.menuName = menuName;
		this.sort = sort;
		this.url = url;
		this.open = open;
		this.fatherId = fatherId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MENU_NAME", nullable = false, length = 20)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "SORT", nullable = false)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "URL", nullable = false, length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "OPEN", nullable = false)
	public Integer getOpen() {
		return this.open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	@Column(name = "FATHER_ID", nullable = false, length = 32)
	public String getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

}