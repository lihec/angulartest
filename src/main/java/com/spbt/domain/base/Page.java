package com.spbt.domain.base;

/** 
 * 对分页的基本数据进行一个简单的封装 
 */
public class Page {
    /**默认分页大小*/
    private static final int DEFAULT_SIZE = 15;
    /** 页码，默认是第一页 */
    private int pageNo = 1;
    /** 每页显示的记录数，默认是15 */
    private int pageSize = DEFAULT_SIZE;
    /** 总记录数 */
    private int totalRecord;
    /** 总页数 */
    private int totalPage;
    /** 排序信息 */
    private String sortInfo;
    
    private boolean loadAll = false;

    /**
     * 查询参数
     */
    private Object params;

    /**
     * @return the params
     */
    public Object getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Object params) {
        this.params = params;
    }

    public String getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(String sortInfo) {
        this.sortInfo = sortInfo;
    }

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

    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * 添加注释
     * 
     * @author 李贺[of253]
     * @date 2013-12-7 下午4:16:50
     * @param totalRecord
     */
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        // 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        this.totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public boolean isLoadAll() {
		return loadAll&&pageSize<0;
	}

	public void setLoadAll(boolean loadAll) {
		this.loadAll = loadAll;
	}

	//TODO 这里使用构造子方式用flyweight模式会跟好
    /**
     * 从PageableVO获取分页参数组成Page
     * 
     * @author 李贺[of253]
     * @date 2013-12-7 下午4:14:34
     * @param baseListVO
     * @return
     */
    public static Page getInstance(PageableVO baseListVO) {
        Page page = new Page();
        page.setPageNo(baseListVO.getPageNo());
        page.setPageSize(baseListVO.getPageSize());
        page.setSortInfo(baseListVO.getSortString());
        page.setParams(baseListVO);
        page.setLoadAll(baseListVO.isLoadAll());
        return page;
    }
}