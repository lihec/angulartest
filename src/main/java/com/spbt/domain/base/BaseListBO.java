package com.spbt.domain.base;

import java.util.List;

/**
 * 列表查询返回结果集
 *
 * @author of518 2012-9-4 下午6:10:30
 * @see
 */
public class BaseListBO extends BaseInfo {

    private static final long serialVersionUID = -7845365502476716789L;

    /**默认分页大小*/
    private static final int DEFAULT_SIZE = 10;
    /**
     * 当前页序号
     */
    private int pageno = 0;

    /**
     * 每页显示数量
     */
    private int pagesize = DEFAULT_SIZE;

    /**
     * 总条数
     */
    private int totalcount = 0;

    /**
     * 列表
     */
    private List<?> datalist = null;

    /**
     * 获取BaseListBO
     * 
     * @author 李贺[of253]
     * @date 2013-12-7 下午4:27:08
     * @param page
     * @param list
     * @return
     */
    public static BaseListBO getInstance(Page page, List<?> list) {
        BaseListBO bo = new BaseListBO();
        bo.setTotalcount(page.getTotalRecord());
        bo.setDatalist(list);
        bo.setPageno(page.getPageNo());
        bo.setPagesize(page.getPageSize());
        return bo;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public List<?> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<?> datalist) {
        this.datalist = datalist;
    }

}
