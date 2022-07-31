package Demo03;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class Example01 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        try {
            File inputFile = new File("C:\\project\\Web\\FirstWeb\\src\\Demo03\\test.xml");

            SAXReader reader = new SAXReader();                 //创建dom4j的读取文件类，来读取xml文件
            Document document = reader.read(inputFile);

            Element classElement = document.getRootElement();//Element类是获取根元素内的元素

            System.out.println("Root element :" + classElement.getName());
            //jaxen下载地址
            //http://www.java2s.com/Code/Jar/j/Downloadjaxen112jar.htm
            //@id=’02’的意思是定位到id为02的元素属性，以遍历的形式输出出来。
            List<Node> nodes = document.selectNodes("/root/part[@id='02']");


            System.out.println("--------------------");

            for (Node node : nodes) {
                System.out.println("标签名=:" + node.getName());
                System.out.println("姓名:" + node.selectSingleNode("name").getText());
                System.out.println("年龄:" + node.selectSingleNode("age").getText());
                System.out.println("性别:" + node.selectSingleNode("sex").getText());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
