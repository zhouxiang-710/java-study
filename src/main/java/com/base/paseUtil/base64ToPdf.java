package com.base.paseUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/*
 * 功能描述: <br>
 * @Param: base 64 和 pdf的互转
 * @Return:
 * @Author: zhouxiang
 * @Date: 2021/4/20 16:51
 */
public class base64ToPdf {

    public static void main(String[] args) {
        //将file 转化成 base64 字符串
        File file = new File("C:\\Users\\Lenovo\\Desktop\\肖2true.pdf");
        String s = PDFToBase64(file);
        System.out.println(s);

        //将base64放在本地文本中读取
        String s1 = textToString("C:\\Users\\Lenovo\\Desktop\\99.txt");
        //转化pdf
        base64StringToPdf(s1,
                "C:\\Users\\Lenovo\\Desktop\\10000true.pdf");
    }

//base64字符串的的文本转string
    public static  String textToString(String filePath){
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= null;
        try {
            bf = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        while(true){
            try {
                if (!((s = bf.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }
        return  buffer.toString();
    }

//Base64编码转换为pdf
    public static void base64StringToPdf(String base64Content,String filePath){
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                bis.close();
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //pdf转换为Base64编码
    public static String PDFToBase64(File file) {
        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
