package com.gqgx.utils;

import java.util.List;

@SuppressWarnings("hiding")
public class Page<T> {
	private Long total;
    
    private List<T> rows;
    
    private int limit=0;
    
    private int offset = 0;
    
    private String order ="asc";

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
    
}
