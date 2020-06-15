package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Util.CurrentTime;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.*;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Prereq;
import cn.edu.jlu.ccst.glzz.system.generated.Model.SecTimePlace;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Takes;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class ChooseCoursesService {
    @Resource
    TeachesDao teachesDao;
    @Resource
    TakesDao takesDao ;
    @Resource
    PrereqDao prereqDao;
    @Resource
    CourseDao courseDao;
    @Resource
    SecTimePlaceDao secTimePlaceDao;


    public List<Map<String,Object>> getSelectCourses(String student_id,int limit,int page,String class_name,Integer year,String semester){
        Query query=new Query();
        query.join("natural join professor").join("natural join section").join("natural join sec_time_place").join("natural join course").join("natural join classroom").join("natural join time_slot");
        query.eq("year", CurrentTime.getYear()).eq("semester",CurrentTime.getSemester());
        query.page(page,limit);
        if(class_name!=null&&!class_name.equals("")){
            query.like("title",class_name);
        }
        if(year!=null){
            query.eq("year",year);
        }
        if(semester!=null&&!semester.equals("")){
            query.eq("semester",semester);
        }
        List<String> column = Arrays.asList("class_id","course_id","professor_name","title as class_name","credits","course.dept_name","sec_capacity","building","room_number","day","start_time","end_time","time_id");
        List<Map<String,Object>> selectCoursesList=teachesDao.listMap(column,query);
        List<Map<String,Object>> ansList=new ArrayList<>();
        //将相同class_id的合并
        for(Map<String,Object> it:selectCoursesList){
            boolean newone=true;
            //找在不在ansList里
            for(Map<String,Object> ansit:ansList)
            {
                if(ansit.get("class_id")==it.get("class_id")){
                    newone=false;
                    String tem=(String)ansit.get("course_place");
                    tem+="<br>";
                    tem+=(String)it.get("building")+" "+(String)it.get("room_number");
                    ansit.put("course_place",tem);
                    String timetem=(String)ansit.get("course_time");
                    timetem+="<br>";
                    int i_day=(int)it.get("day");
                    String s_day=null;
                    switch (i_day){
                        case 1:s_day="周一";
                            break;
                        case 2:s_day="周二";
                            break;
                        case 3:s_day="周三";
                            break;
                        case 4: s_day="周四";
                            break;
                        case 5: s_day="周五";
                            break;
                        case 6:s_day="周六";
                            break;
                        case 7:s_day="周日";
                            break;
                    }

                    String start_time2=(it.get("start_time").toString());
                    start_time2=start_time2.substring(0,5);
                    String end_time2=(it.get("end_time").toString());
                    end_time2=end_time2.substring(0,5);
                    timetem+=s_day+' '+start_time2+'-'+end_time2;
                    ansit.put("course_time",timetem);
                    ((HashSet<Integer>)ansit.get("time_id")).add((int) (it.get("time_id")));
                    break;
                }
            }
            if(newone){
                Object day=it.remove("day");
                Object start_time=it.remove("start_time");
                Object end_time=it.remove("end_time");
                Object building=it.remove("building");
                Object room_number=it.remove("room_number");
                Object time_id=it.remove("time_id");
                it.put("time_id",new HashSet<Integer>());
                ((HashSet<Integer>)it.get("time_id")).add((int)time_id);
                int i_day=(int)day;
                String s_day=null;
                switch (i_day){
                    case 1:s_day="周一";
                        break;
                    case 2:s_day="周二";
                        break;
                    case 3:s_day="周三";
                        break;
                    case 4: s_day="周四";
                        break;
                    case 5: s_day="周五";
                        break;
                    case 6:s_day="周六";
                        break;
                    case 7:s_day="周日";
                        break;
                }

                String start_time2=(start_time.toString());
                start_time2=start_time2.substring(0,5);
                String end_time2=(end_time.toString());
                end_time2=end_time2.substring(0,5);
                it.put("course_place",(String)building+' '+(String)room_number);
                it.put("course_time",s_day+' '+start_time2+'-'+end_time2);
                ansList.add(it);
            }

        }
        return ansList;
    }

    public int countMember(int class_id){
        Query query=new Query();
        query.eq("class_id",class_id).eq("ismajor",1);
        //System.out.println(takesDao.list(query));
        return takesDao.list(query).size();

    }
//返回未满足的先修课
    public String checkPre(String student_id, String course_id){
        List<Prereq> ansList=new ArrayList<>();
        List<String> preidList=new ArrayList<>();
        List<String> unsatisfiedList=new ArrayList<>();
        String unsatisfiedTitleList = "";
        Query aquery=new Query();
        aquery.eq("course_id",course_id);
        ansList=prereqDao.list(aquery);
        for(Prereq it:ansList){
            preidList.add(it.getPrereqId());
        }
        System.out.println(preidList.size());
        for(String it:preidList) {
            Query bquery = new Query();
            bquery.join("natural join section");
            bquery.eq("student_id",student_id).eq("course_id",it);
            List<Takes> takesList=takesDao.list(bquery);
            boolean flag=false;
            for(Takes takesit:takesList){
                if(takesit.getStatus().equals("scored")){
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                unsatisfiedList.add(it);
            }
        }
        int time=0;
        for(String it:unsatisfiedList){

            Query cquery=new Query();
            cquery.eq("course_id",it);
            if(time==0){
                unsatisfiedTitleList+=courseDao.getByQuery(cquery).getTitle();
                time++;
            }
            else {
                unsatisfiedTitleList+=","+courseDao.getByQuery(cquery).getTitle();
            }
        }


        return unsatisfiedTitleList;
    }
//返回时间冲突的课程title
    public String checkTimePriblem(String student_id,int class_id,List<Map<String, Object>> zhulist){
        HashSet<String> ans=new HashSet<String>();
        Query query=new Query();
        query.eq("class_id",class_id);
        List<SecTimePlace> tem= secTimePlaceDao.list(query);
        for(SecTimePlace it:tem){
            for(Map<String,Object> zhuit:zhulist){
                if(((HashSet<Integer>)(zhuit.get("time_id"))).contains(it.getTimeId())){
                    ans.add(zhuit.get("class_name").toString());
                }
            }

        }
        String toreturn="";
        int time=0;
        for(String it:ans){
            if(time==0) {
                toreturn += it;
                time++;
            }
            else{
                toreturn+=","+it;
            }
        }
        return toreturn;

    }

    public Takes findCourse(String student_id, int class_id){
        Query query=new Query();
        query.eq("student_id",student_id).eq("class_id",class_id);
        Takes tem=takesDao.getByQuery(query);
        return tem;
    }

    public void addCourse(String student_id,int class_id,int ismajor){
        Query query=new Query();
        query.eq("student_id",student_id).eq("class_id",class_id);

        Takes tem=takesDao.getByQuery(query);
        System.out.println(tem);
        if(tem==null) {
            Takes takes = new Takes();
            takes.setStudentId(student_id);
            takes.setClassId(class_id);
            takes.setIsmajor(ismajor);
            takes.setStatus("submit");
            takesDao.save(takes);
        }
        else{
            tem.setIsmajor(ismajor);
            int ans=takesDao.updateByQuery(tem,query);
            System.out.println(ans);
        }
    }




}
