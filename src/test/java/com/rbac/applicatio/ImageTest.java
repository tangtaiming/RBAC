package com.rbac.applicatio;

import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @auther ttm
 * @date 2018/8/28
 */
@Ignore
public class ImageTest {

    public static InputStream readInputStream(String strurl)
            throws Exception {
        //new一个URL对象
//        URL url = new URL("http://img10.3lian.com/c1/newpic/03/08/26.jpg");
        URL url = new URL(strurl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        return inStream;
    }

    @Test
    public void imgageSteamTest() throws Exception {
        InputStream reader = ImageTest.readInputStream("https://www.guphotos.com/images/P/5/PAF0009B-5/PAF0009B-5-1-f7eb-76cM.jpg");
        BufferedImage bufferedImage = ImageIO.read(reader);
        System.out.println("Show: " + bufferedImage.getHeight());
        System.out.println("Show: " + bufferedImage.getWidth());
    }


}
