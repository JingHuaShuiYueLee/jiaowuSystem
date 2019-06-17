package com.zxc.service.impl;

import com.zxc.model.Page;
import com.zxc.service.PageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {//分页实现
    @Override
    public Page subList(int page, List list) {
        Page paging=new Page();
        paging.setCurrentPage(page);
        int count = list.size();
        paging.setTotalPage(count % 6 == 0 ? count / 6 : count / 6 + 1);
        paging.setPageSize(6);
        paging.setStar((paging.getCurrentPage() - 1) * paging.getPageSize());//从第几条记录开始
        paging.setDataList(list.subList(paging.getStar(), count - paging.getStar() > paging.getPageSize() ? paging.getStar() + paging.getPageSize() : count));
        return paging;
    }
}
