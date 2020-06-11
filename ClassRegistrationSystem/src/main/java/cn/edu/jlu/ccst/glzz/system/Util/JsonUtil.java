package cn.edu.jlu.ccst.glzz.system.Util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    JSONObject jsonObject =new JSONObject();

    public JsonUtil(int status,String msg) {
        jsonObject.put("status",status);
        jsonObject.put("msg",msg);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void put(String str, Object obj){
        jsonObject.put(str,obj);
    }


}
