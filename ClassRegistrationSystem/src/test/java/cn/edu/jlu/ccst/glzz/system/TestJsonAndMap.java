package cn.edu.jlu.ccst.glzz.system;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJsonAndMap {
    public static void main(String []args) {
        String soap = "1111";
        List<String> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = new HashMap<String, String>();
        list.add(soap);
        list.add(soap);
        jsonObject.put("soap",soap);
        jsonObject.put("soap","2222");
        map.put("soap",soap);
        map.put("soap","3333");

        System.out.println("这是list的输出：" + list);
        System.out.println("这是json的输出：" + jsonObject);
        System.out.println("这是map的输出：" + map);
    }
}