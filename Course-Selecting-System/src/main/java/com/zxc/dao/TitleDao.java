package com.zxc.dao;

import com.zxc.model.ProfessionalTitle;

import java.util.List;

public interface TitleDao {
    List<ProfessionalTitle> queryAllTitles();
    String queryPNameByPId(int pId);
}
