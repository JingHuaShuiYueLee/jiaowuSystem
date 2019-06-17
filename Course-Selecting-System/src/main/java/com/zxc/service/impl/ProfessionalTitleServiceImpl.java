package com.zxc.service.impl;

import com.zxc.dao.TitleDao;
import com.zxc.model.ProfessionalTitle;
import com.zxc.service.ProfessionalTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalTitleServiceImpl implements ProfessionalTitleService {//教师职称

    @Autowired
    private TitleDao titleDao;

    @Override
    public List<ProfessionalTitle> queryAllTitles() {
        return titleDao.queryAllTitles();
    }

    @Override
    public String queryPNameByPId(int pId) {
        return titleDao.queryPNameByPId(pId);
    }
}
