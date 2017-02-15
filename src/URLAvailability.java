import sun.net.www.protocol.http.HttpURLConnection;

import java.awt.*;
import java.net.URL;

public class URLAvailability {
    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;

    public static void main(String []args) {
        URLAvailability urlAvailability = new URLAvailability();
        urlAvailability.isConnect("http://219.224.166.16:8888/");
    }
    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
     * @param urlStr 指定URL网络地址
     * @return URL
     */
    public synchronized URL isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return null;
        }
        while (true) try {
            url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            state = con.getResponseCode();
            System.out.println(counts + "= " + state);
            if (state == 200) {
                Frame frame = new Frame("success!!!!");
                Runtime.getRuntime().exec("explorer http://219.224.166.16:8888/");
                frame.setSize(300, 400);
                frame.setLocation(500, 500);
                frame.setLayout(new FlowLayout());
                frame.setVisible(true);
                break;
            }
            if (state == 504) {
                Exception ex = null;
                throw ex;
            }
            break;
        } catch (Exception ex) {
            counts++;
            System.out.println("URL is wrong!     " + counts);
            urlStr = null;
            continue;
        }
        return url;
    }
}
