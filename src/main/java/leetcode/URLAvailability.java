package leetcode;

import java.awt.*;
import java.net.URL;
import java.net.URLConnection;

public class URLAvailability {
    private static URL url;
    private static URLConnection con;
    private static int state = -1;

    public static void main(String []args) {
        URLAvailability urlAvailability = new URLAvailability();
        urlAvailability.isConnect("http://219.224.166.16:8888/");
    }
    /**
     * ���ܣ���⵱ǰURL�Ƿ�����ӻ��Ƿ���Ч,
     * ����������������� 5 ��, ��� 5 �ζ����ɹ�����Ϊ�õ�ַ������
     * @param urlStr ָ��URL�����ַ
     * @return URL
     */
    public synchronized URL isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return null;
        }
        while (true) try {
            url = new URL(urlStr);
            con = url.openConnection();
//            state = con.getResponseCode();
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
