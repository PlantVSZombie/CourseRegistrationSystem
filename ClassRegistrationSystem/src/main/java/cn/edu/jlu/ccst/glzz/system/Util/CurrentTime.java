package cn.edu.jlu.ccst.glzz.system.Util;

import java.util.Calendar;

public class CurrentTime {
    static Calendar calendar = Calendar.getInstance();
    // 获取当前年
    static int year = calendar.get(Calendar.YEAR);
    // 获取当前月
    static int month = calendar.get(Calendar.MONTH) + 1;

    public static int getYear() {
        return year;
    }
    public static String getSemester(){
        String semester=null;
        if(month>=1 && month<=5){
            semester="Spring";
        }
        else if(month>=6 && month<=12)
        {
            semester="Autumn";
        }
        return semester;
    }
}
