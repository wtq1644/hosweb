package com.hliedu.mybatis.page;


/**
 * 分页实体
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class PageTools {

    private static final long serialVersionUID = 4967304058994454716L;

    /** 传入：一页几行*/
    private int pageSize = 10;

    /** 传入：开始行*/
    private int startRow;

    /** 返回：当前总行数*/
    private int recordCount;

    /**当前页*/
    private int pageNo = 1;

    /**总共页*/
    private int pageCount = 1;

    /**当前几行*/
    private int recordCountNo = 0;

    public int getPageCount() {
//        pageCount = new Double(
//                java.lang.Math.ceil(((double)getRecordCount())/((double)getPageSize()))).intValue();
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartRow() {
//        startRow = (getPageNo() - 1) * pageSize;
//        startRow = startRow < 0 ? 0 : startRow;
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
//        if(this.getRecordCountNo()>0){
//            setRecordCountNo(this.getRecordCountNo());
//        }
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageNo() {
//        if(pageSize > 0) {
//            pageNo = startRow/pageSize +1;
//        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getRecordCountNo() {
        return recordCountNo;
    }

    public void setRecordCountNo(int recordCountNo) {
        this.recordCountNo = recordCountNo;
//        if(this.getPageSize()>0&&recordCountNo>0){
//            setPageNo(recordCountNo/this.getPageSize()+1);
//        }
    }

}
