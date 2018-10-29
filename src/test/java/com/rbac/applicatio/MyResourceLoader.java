package com.rbac.applicatio;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.URL;

/**
 * @auther ttm
 * @date 2018/10/26 0026
 **/
public class MyResourceLoader extends DefaultResourceLoader {

    @Override
    public Resource getResource(String location) {
        System.out.println("文件路徑地址：" + location);
        //這裡我們對要加載的文件資源進行自定義的解析，我們判斷下文件路徑是否是以“/WEB-INF/”開頭，從而進行處理
        if (location != null &&
                location.startsWith("/WEB-INF/")) {
            try {
                // 由於我的工程是maven工程，所以我的路徑重定向到web應用的路徑下
                URL url = new URL("file:/" + System.getProperty("user.dir")
                        + "/src/main/webapp" + location);
                return new UrlResource(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return super.getResource(location);
    }

}
