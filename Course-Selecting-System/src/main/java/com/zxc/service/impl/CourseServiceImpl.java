package com.zxc.service.impl;

import com.zxc.dao.CourseDao;
import com.zxc.dao.UserDao;
import com.zxc.model.*;
import com.zxc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Course> queryAllById(int id) {//会用到多个dao
        List<Course> course_list= courseDao.queryCourseById(id);//这个老师教的所有课程
        for(Course c:course_list){//设置好每个课程的所有学院名字
            c.setClassLimitInsName(new ArrayList<>());
            List<Integer> limit_list=courseDao.selectInsIdByClassId(c.getClassId());//查询course_limit表
            for(Integer i:limit_list){
                c.getClassLimitInsName().add(courseDao.selectNameByInsId(i));
            }
            c.setTeaName(userDao.selectTeaById(c.getTeaId()).getTeaName());
        }
        return course_list;
    }

    @Override
    public List<String> queryInsNameByCourse(int id) {//放回该门课的对应的所有学院名字
        List<String> insNameList=new ArrayList<>();
        List<Integer> insIdList=courseDao.queryInsIdByCourseId(id);//一门课对应多个学院
        for(int i:insIdList){
            insNameList.add(courseDao.selectNameByInsId(i));
        }
        return insNameList;
    }

    @Override
    public List<Institution> queryAllIns() {
        return courseDao.queryAllIns();
    }

    @Override
    public int insertCourse(String name,String num,String credit,int teaid) {
        Course course=new Course();//new实体对象pojo是在service层new的，先new出来后把值都设置好然后再传给dao，直接调用dao插入数据库即可。
        course.setClassName(name);
        course.setClassNum(Integer.parseInt(num));
        course.setCredit(Double.parseDouble(credit));
        course.setClassChooseNum(0);
        course.setTeaId(teaid);
        courseDao.insertCourse(course);
        return course.getClassId();//返回生成的主键
    }

    @Override
    public void insertInsLimit(String det,int classId) {
        String[] insList=det.split(",");
        for(String in:insList){
            Course_limit course_limit=new Course_limit();
            course_limit.setClassId(classId);
            course_limit.setInsId(Integer.parseInt(in));
            courseDao.insertInsLimit(course_limit);
        }
    }

    @Override
    public Course queryInfoById(int id) {
        return courseDao.queryCourseInfoById(id);
    }

    @Override
    public List<Integer> selectCourseLimit(int classId) {
        return courseDao.selectCourseLimit(classId);
    }

    @Override
    public int updateCourse(String name,String num,String credit,int teaid,int courseid) {
        Course course=new Course();
        course.setTeaId(teaid);
        //course.setClassChooseNum(0);有问题
        course.setClassNum(Integer.parseInt(num));
        course.setCredit(Double.parseDouble(credit));
        course.setClassName(name);
        course.setClassId(courseid);//应该是原来的课程id才对
        courseDao.updateCourse(course);
        return course.getClassId();
    }

    @Override
    public void updateInsLimit(String det, int classId) {//先删除再增加，也是修改的一种实现方式
        String[] insList=det.split(",");
        courseDao.deleteInsLimit(classId);//先删除允许的所有学院
        for(String ins:insList){
            Course_limit course_limit=new Course_limit();
            course_limit.setClassId(classId);
            course_limit.setInsId(Integer.parseInt(ins));
            courseDao.insertInsLimit(course_limit);//然后逐条插入课程-学院限制记录
        }
    }

    @Override
    public void deleteCourse(int id) {//调用dao删除数据库中三张表信息
        courseDao.deleteCourseById(id);//删除course表
        //解除选课表关联
        courseDao.deleteStuByClassId(id);//删除选课表
        //解除学院限制表关联
        courseDao.deleteLimitByClassId(id);//删除course_limit表
    }

    @Override
    public List<Student> queryStuByCourseId(int id) {//学这门课的所有学生
        List<Student> stu_list=new ArrayList<>();
        List<Course_choose> id_list=courseDao.queryStuIdByCourseId(id);
        for(Course_choose i:id_list){
            Student student=userDao.selectStuById(i.getStuId());
            student.setTempScore(i.getScore());//先设置好之后再放进列表中，这个临时分数用来在页面展示的，数据库表中没有临时分数这个字段
            stu_list.add(student);
        }
        return stu_list;
    }

    @Override
    public void updateScore(int classId, int stuId, int score) {
        Course_choose course_choose=new Course_choose();
        course_choose.setStuId(stuId);
        course_choose.setClassId(classId);
        course_choose.setScore(score);
        courseDao.updateScore(course_choose);//在sql中表的字段isScored为1表示老师已经打分，为0表示老师还没打分
    }

    @Override
    public List<Student> queryStuByStuId(int classid, int stuid) {//只有一个学生返回的，返回list的原因是为了分页的paging，因为paging要传入一个list
        List<Student> stu_list=new ArrayList<>();
        List<Course_choose> id_list=courseDao.queryStuIdByCourseId(classid);//很多学生选了这门课
        for(Course_choose i:id_list){
            Student student=userDao.selectStuById(i.getStuId());
            student.setTempScore(i.getScore());
            if(student.getStuId()==stuid){
                stu_list.add(student);
            }
        }
        return stu_list;
    }

    @Override
    public List<Course> queryAllCourse(int stuid){//每个学生的所有课程都不一样，
        List<Course> course_list= courseDao.queryAllCourse();//该course还有3个字段需要填充
        List<Integer> stu_courselist=courseDao.queryCourseIdByStuId(stuid);//这个学生选的所有课程，每个学生都不一样
        for(Course c:course_list){
            c.setClassLimitInsName(new ArrayList<>());
            List<Integer> limit_list=courseDao.selectInsIdByClassId(c.getClassId());//这个课程允许哪些学院选
            for(Integer i:limit_list){
                c.getClassLimitInsName().add(courseDao.selectNameByInsId(i));
            }
            c.setTeaName(courseDao.selectTeaNameByTeaId(c.getTeaId()));
            c.setIsChoose(0);//该课是否已被学生选过
            for(int i:stu_courselist){
                if(c.getClassId()==i){
                    Course_choose cc = new Course_choose();//new这个的目的是构造查询条件，Course_choose是查询条件，是为了知道是否该门课已经老师打分了
                    cc.setStuId(stuid);
                    cc.setClassId(i);
                    c.setIsScored(courseDao.isScored(cc));
                    c.setIsChoose(1);
                    break;
                }
            }
        }
        return course_list;//把所有字段都设置完之后再返回
    }

    @Override
    public Course queryCourse(int id) throws NullPointerException {//id是course的id
        Course course=courseDao.selectCourseByClassId(id);//数据库表course是信息不完整的
        List<Integer> limit_list=courseDao.selectInsIdByClassId(id);
        course.setClassLimitInsName(new ArrayList<>());
        for(Integer i:limit_list){
            course.getClassLimitInsName().add(courseDao.selectNameByInsId(i));//设置好list
        }
        course.setTeaName(courseDao.selectTeaNameByTeaId(course.getTeaId()));
        return course;//这个course是pojo的是完整的，数据库表course是信息不完整的
    }

    @Override
    public void chooseSuccess(int classId, int stuId) {//涉及到两张表
        courseDao.addChooseNum(classId);
        Course_choose course_choose=new Course_choose();
        course_choose.setIsScored(0);
        course_choose.setScore(0);//这句可以不要的，这样score字段为空，成绩为空在页面不显示
        course_choose.setClassId(classId);
        course_choose.setStuId(stuId);
        courseDao.addCourseChoose(course_choose);
    }

    @Override
    public boolean checkStuIns(int classId, int stuId) {//选课的学院限制
        int stu_insId=userDao.selectStuById(stuId).getInsId();
        List<Integer> class_insId=courseDao.queryInsIdByCourseId(classId);
        for(int i:class_insId){
            if(stu_insId==i)//学生所在学院正好在这门课对应的学院里面
                return true;//允许学生选这门课
        }
        return false;
    }

    @Override
    public void deleteCourseChoose(int stuId, int classId) {//涉及到2张表的改变
        courseDao.downChooseNum(classId);//course表的已选人数减少1
        Course_choose course_choose=new Course_choose();//作为查询条件的对象，因为dao方法只能有一个形参
        course_choose.setStuId(stuId);
        course_choose.setClassId(classId);
        courseDao.deleteCourseChoose(course_choose);//选课表删除一条记录，封装成一个对象传进去，因为有多个参数，只能封装成一个对象传进去，mybatis规定dao方法只能有一个形参
    }

    @Override
    public List<Course> queryStuCourse(int stuId) {//这位学生所修的全部课程
        List<Integer> classid_list=courseDao.queryCourseIdByStuId(stuId);
        List<Course> course_list=new ArrayList<>();
        for(int i:classid_list){
            Course course=courseDao.queryCourseInfoById(i);
            course.setTeaName(courseDao.selectTeaNameByTeaId(course.getTeaId()));
            Course_choose course_choose=new Course_choose();//course_choose作为查询条件使用
            course_choose.setClassId(i);
            course_choose.setStuId(stuId);
            int isMarked = courseDao.isScored(course_choose);
            course.setIsScored(isMarked);
            if(isMarked==1) { //如果已经打分了，那么就设置分数，会涉及到数据库速度问题所以要有所判断
                course.setScore(courseDao.selectScore(course_choose));//通过course_choose把分数拿出来
            }
            course_list.add(course);//全部属性都设置好之后再放进去
        }
        return course_list;
    }

    @Override
    public List<Course> queryAllByInsId(int id) {//id是insId，这个学院要修的所有课程
        List<Course> course_list= courseDao.queryAllCourse();
        List<Course> course_Inslist=new ArrayList<>();
        for(Course c:course_list){
            List<Integer> limit_list=courseDao.selectInsIdByClassId(c.getClassId());//查找该课程对应的所有学院id
            for(int li:limit_list){
                if(id==li){
                    course_Inslist.add(c);
                    break;
                }
            }
        }
        for(Course cc:course_Inslist){
            cc.setClassLimitInsName(new ArrayList<>());
            List<Integer> limit_list=courseDao.selectInsIdByClassId(cc.getClassId());
            for(Integer i:limit_list){
                cc.getClassLimitInsName().add(courseDao.selectNameByInsId(i));
            }
            cc.setTeaName(userDao.selectTeaById(cc.getTeaId()).getTeaName());
        }
        return course_Inslist;
    }
    @Override
    public double getAllCredits(int stuId){
       List<Course_choose> ccList = courseDao.queryCourseByStuId(stuId);
       Double sumCredit = 0.0;
       for(Course_choose cc :ccList) {
           if(cc.getScore() >= 60) {
               sumCredit += courseDao.queryCourseInfoById(cc.getClassId()).getCredit();
           }
       }
       return sumCredit;
    }
    @Override
    public double getGPA(int stuId) { //学分绩计算
        Double fenmu = 0.0;
        Double fenzi = 1.0;
        Double credit = 0.0;
        List<Course_choose> ccList = courseDao.queryCourseByStuId(stuId);
        for(Course_choose cc : ccList) {
            if(cc.getIsScored()==1) { //如果老师已经打分了
                credit = courseDao.queryCourseInfoById(cc.getClassId()).getCredit();
                fenmu += credit; //分母是学分总和
                fenzi +=  cc.getScore() * credit;
            }
        }
        if(fenmu < 0.1) {//学生还没有成绩,即老师还没有打分的话
            return 0.0;
        }
        else
            return (fenzi/fenmu);
    }
}
