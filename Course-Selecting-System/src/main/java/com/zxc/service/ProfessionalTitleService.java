package com.zxc.service;
import com.zxc.model.Course;
import com.zxc.model.Institution;
import com.zxc.model.ProfessionalTitle;
import com.zxc.model.Student;

import java.util.List;

public interface ProfessionalTitleService {
    List<ProfessionalTitle> queryAllTitles();
    String queryPNameByPId(int pId);
}
