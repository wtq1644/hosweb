package com.hliedu.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hliedu.tools.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * -
 * <p>
 * - 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class BaseServiceImpl {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Object UUIDLock = new Object();

    protected String createUUIDString() {
        synchronized (UUIDLock) {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }


    protected void startPage(Map<String, Object> map){
        Integer page = (Integer) map.get("page");
        if(page == null)page = 1;

        Integer size = (Integer) map.get("rows");
        if(size == null)size = 5;

        PageHelper.startPage(page, size);
    }

    protected <T> PageInfo<T> getPageInfo(List<T> list){
        if(ListUtil.isNotEmpty(list)){
            return new PageInfo<T>(list);
        }
        return new PageInfo<T>();
    }

}
