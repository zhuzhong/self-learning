package ch15.d2;


public class TestMethod {
    public TestMethod() { // /xx/weblogic60b2_win.exe
        try {
            String url = "https://downloads.gradle.org/distributions/gradle-1.8-bin.zip";
            url="https://download3.vmware.com/software/wkst/file/VMware-Workstation-Full-10.0.1-1379776.i386.bundle";
            String temp = "C:/Users/sunff/Desktop/test2/";
            String fileName = "Full-10.0.1-1379776.i386.bundle";
            SiteInfoBean bean = new SiteInfoBean(url, temp, fileName, 3);
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