package cn.edu.jlu.ccst.glzz.system.Util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileUtil {
    public static String readString(String pathofResources,String charset ) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(pathofResources);
        String str = IOUtils.toString(new InputStreamReader(classPathResource.getInputStream(),charset));
        return str;
    }
}
