package cn.interestingshop.utils;

import java.io.Serializable;

public class Pager implements Serializable{
	private int currentPage;//当前页
	private int rowCount;//总条数
	private int rowPerPage;//每页显示条数
	private int pageCount;//总页数
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public Pager(int rowCount, int rowPerPage, int currentPage) {
		this.rowCount = rowCount;
		this.rowPerPage = rowPerPage;

		// 修复：确保当前页码至少为1
		this.currentPage = Math.max(currentPage, 1);

		// 修复：确保每页条数至少为1
		this.rowPerPage = Math.max(rowPerPage, 1);

		// 计算总页数
		if (this.rowCount <= 0) {
			this.pageCount = 0;
		} else {
			this.pageCount = (int) Math.ceil((double) this.rowCount / this.rowPerPage);
			// 确保当前页不超过总页数
			this.currentPage = Math.min(this.currentPage, this.pageCount);
		}
		}

	public int getRowCount() {
		return rowCount;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}
}
