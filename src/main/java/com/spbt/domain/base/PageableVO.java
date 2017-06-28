package com.spbt.domain.base;

/**
 * 分页查询基础入参
 *
 * @author lihe 2012-9-27 下午3:45:05
 * @see
 */
public class PageableVO extends BaseInfo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 分页索引
     */
    private int pageNo;
    /**
     * 分页大小
     */
    private int pageSize;
    /**
     * 排序方式
     */
    private String sortString;
    
    private boolean loadAll = false;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

	public boolean isLoadAll() {
		return loadAll;
	}

	public void setLoadAll(boolean loadAll) {
		this.loadAll = loadAll;
	}
    
    

}
