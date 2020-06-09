package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Util.ReadFileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class InitController {

    @RequestMapping("dynamic/init.json")
    @ResponseBody
    public String initJson() throws IOException {
        String str = ReadFileUtil.readString("static/api/init.json","UTF-8");
        return str;
    }
}
