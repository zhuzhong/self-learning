package ch15.d2;

public class TestMethod {
    public TestMethod() { // /xx/weblogic60b2_win.exe
        try {
            String url = "http://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.11-win32.zip";
            String temp = "C:/Users/sunff/Desktop/test2/";
            String fileName = "mysql-5.7.11-win32-23.zip";
            SiteInfoBean bean = new SiteInfoBean(url, temp, fileName, 5);
            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestMethod();
    }
}