package com.base.IO;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 11:59 2020/8/29
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class Reader {
    public static void main(String[] args) throws IOException {
        String fileName ="C:\\Users\\zhouxiang\\Desktop\\test.txt";
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //输出编码格式,并非文件的
        System.out.println("字符集："+fileReader.getEncoding());

        String line =bufferedReader.readLine();

        while (line!=null){
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileReader.close();

    }
    @Test
    public void addCharsetTest() throws IOException {
        String fileName ="C:\\Users\\zhouxiang\\Desktop\\test.txt";
        FileReader fileReader = new FileReader(fileName);

        String chartSetName = this.getChartSetName(fileName);
        System.out.println("ChartSetName:---"+this.getChartSetName(fileName));


        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(new File(fileName)),chartSetName);

        BufferedReader bufferedReader1 = new BufferedReader(inputReader);

        String line =bufferedReader1.readLine();

        while (line!=null){
            System.out.println(line);
            line = bufferedReader1.readLine();
        }

        inputReader.close();
        bufferedReader1.close();

    }
    /*
     * // todo 功能描述: 获取文件编码-->基于统计学原理的，不保证完全正确
     */
    private  String getChartSetName(String path){
        String charsetName = null;
      /*  //这里依赖的 jar 包 是我单独下载的，没有用pom依赖，相关可参考此文章
        // https://blog.csdn.net/wuseyukui/article/details/45799207?utm_medium=distribute.pc_relevant_download.none-task-blog-blogcommendfrombaidu-2.nonecase&depth_1-utm_source=distribute.pc_relevant_download.none-task-blog-blogcommendfrombaidu-2.nonecas
        File file = new File(path);
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        try {
            charset = detector.detectCodepage(file.toURI().toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null) {
            charsetName = charset.name();
        } else {
            charsetName = "UTF-8";
        }*/
        return charsetName;
    }


    @Test
    public void FileStream(){
        try {
            Stream lines = Files.lines(Paths.get("C:\\Users\\zhouxiang\\Desktop\\test.txt"), Charset.defaultCharset());
            Object[] objects = lines.toArray();
            System.out.println(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
