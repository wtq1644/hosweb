package com.hliedu.mybatis.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果集对象
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class QueryResult<T> implements Serializable {

    private static final long serialVersionUID = 2511608306011396012L;

    //记录数量
    private long total;

    //结果集
    private List<T> rows = new ArrayList<T>();

    //分页对象
    private PageTools pageTools;

    public long getTotal() {
        if(null!=getPageTools()){
            setTotal(getPageTools().getRecordCount());
        }
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    public PageTools getPageTools() {
        return pageTools;
    }
    public void setPageTools(PageTools pageTools) {
        this.pageTools = pageTools;
    }
    public List<T> getList() {
        return rows;
    }
    public void setList(List<T> list) {
        this.rows = list;
    }

}
