package com.hzapt.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.google.common.collect.Lists;
import com.hzapt.common.exceptions.MyAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyExcelUtil {

    public static void export(Map<String, String> headerAlias, List dataList, String destFilePath, int sheetIndex, String sheetName) {
        MyAssert.isTrue(CollUtil.isNotEmpty(dataList), "数据集合为空！");
        ExcelWriter writer = ExcelUtil.getWriter(destFilePath, sheetName);
        if (CollUtil.isNotEmpty(headerAlias)) {
            //设置是否只保留别名中的字段值，如果为true，则不设置alias的字段将不被输出，false表示原样输出
            writer.setOnlyAlias(true);
            //自定义标题别名
            writer.setHeaderAlias(headerAlias);
        }
        writer.setSheet(sheetIndex);
        writer.write(dataList, true);
        //关闭writer，释放内存
        writer.close();
    }

    public static void exportResp(Map<String, String> headerAlias, List dataList,
                                  String destFilePath, int sheetIndex,
                                  String sheetName, HttpServletResponse response) throws Exception {
        MyAssert.isTrue(CollUtil.isNotEmpty(dataList), "数据集合为空！");
        export(headerAlias, dataList, destFilePath, sheetIndex, sheetName);
        String[] split = destFilePath.split("/");
        downloadFile(split[split.length-1], destFilePath, response);
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @param path
     * @param response
     * @throws Exception
     */
    public static void downloadFile(String fileName, String path,
                                    HttpServletResponse response) throws Exception {
        File file = new File(path);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes("ISO8859-1"), StandardCharsets.UTF_8));
        response.setContentLength((int) file.length());
        response.setContentType("application/x-msdownload;charset=utf-8");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream buff = new BufferedInputStream(fis);
        byte[] b = new byte[1024];// 相当于我们的缓存
        long k = 0;// 该值用于计算当前实际下载了多少字节
        OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备下载
        // 开始循环下载
        while (k < file.length()) {
            int j = buff.read(b, 0, 1024);
            k += j;
            myout.write(b, 0, j);
        }
        myout.flush();
        buff.close();
    }


    /**
     * excel导入工具类
     *
     * @param file 文件
     * @return 返回数据集合
     * @throws Exception
     */
    public static List<Map<String, Object>> importFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        MyAssert.isTrue(StrUtil.isNotEmpty(fileName), "没有导入文件");
        MyAssert.isTrue(fileName.endsWith(".xls")
                || fileName.endsWith(".xlsx"), "文件格式不正确,请上传 .xlsx | .xls");

        List<List<Object>> lineList = new ArrayList<>();
        //读取数据
        ExcelUtil.readBySax(file.getInputStream(), 0, createRowHandler(lineList));
        return getExcelData(lineList);
    }

    /**
     * excel导入工具类
     *
     * @param file 文件
     * @return 返回数据集合
     * @throws Exception
     */
    public static List<Map<String, Object>> importFile(File file) throws Exception {
        String fileName = file.getName();
        MyAssert.isTrue(StrUtil.isNotEmpty(fileName), "没有导入文件");
        MyAssert.isTrue(fileName.endsWith(".xls")
                || fileName.endsWith(".xlsx"), "文件格式不正确,请上传 .xlsx | .xls");

        List<List<Object>> lineList = new ArrayList<>();
//        //读取数据
        InputStream input = new FileInputStream(file);
        ExcelUtil.readBySax(input, 0, createRowHandler(lineList));
        if(CollectionUtils.isNotEmpty(lineList)){
            //如果不为空
            return getExcelData(lineList);
        }
        return Lists.newArrayList();
    }

    /**
     * excel导入工具类
     *
     * @param filePath 文件路径
     * @return 返回数据集合
     * @throws Exception
     */
    public static List<Map<String, Object>> importFile(String filePath) throws Exception {
        MyAssert.isTrue(filePath.endsWith(".xls")
                || filePath.endsWith(".xlsx"), "文件格式不正确,请上传 .xlsx | .xls");
        List<List<Object>> lineList = new ArrayList<>();
        //读取数据
        ExcelUtil.readBySax(filePath, 0, createRowHandler(lineList));
        return getExcelData(lineList);
    }
    
    /**
     * excel导入工具类--剔除前面removeNum行
     *
     * @param filePath 文件路径
     * @param removeNum 移除行数  1为第一行
     * @param columNamesrowNum 标题所在行  0为第一行
     * @return 返回数据集合
     * @throws Exception
     */
    public static List<Map<String, Object>> importFileByStartRow(String filePath,int removeNum,int columNamesrowNum) throws Exception {
        MyAssert.isTrue(filePath.endsWith(".xls")
                || filePath.endsWith(".xlsx"), "文件格式不正确,请上传 .xlsx | .xls");
        List<List<Object>> lineList = new ArrayList<>();
        //读取数据
        ExcelUtil.readBySax(filePath, 0, createRowHandler(lineList));
        return getExcelDataRemoveNrow(lineList,removeNum,columNamesrowNum);
    }


    /**
     * 获取Excel数据
     *
     * @param lineList
     * @return
     */
    private static List<Map<String, Object>> getExcelData(List<List<Object>> lineList) {
        //去除excel中的第一行数据
        MyAssert.isTrue(lineList.size() > 1, "文件没有数据");

        List<Object> columNames = lineList.get(0);
        lineList.remove(0);

        //将数据封装到list<Map>中
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            if (null != lineList.get(i) && ObjUtil.isNotEmpty(lineList.get(i))) {
                Map<String, Object> hashMap = new HashMap<>();
                for (int j = 0; j < columNames.size(); j++) {
                    Object property = lineList.get(i).get(j);
                    String columName = String.valueOf(columNames.get(j));
                    hashMap.put(columName, property);
                }
                dataList.add(hashMap);
            }
        }
        return dataList;
    }
    
    
    /**
     * 获取Excel数据
     *去除前N行数据
     *
     * @param lineList
     * @param removeNum
     * @param columNamesrowNum
     * @return
     */
    private static List<Map<String, Object>> getExcelDataRemoveNrow(List<List<Object>> lineList,int removeNum,int columNamesrowNum) {
        //去除excel中的第一行数据
        MyAssert.isTrue(lineList.size() > 1, "文件没有数据");
        List<Object> columNames = lineList.get(columNamesrowNum);
        
        for(int i=0;i<removeNum;i++) {
        	lineList.remove(0);
        }

        //将数据封装到list<Map>中
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            if (null != lineList.get(i)) {
                Map<String, Object> hashMap = new HashMap<>();
                for (int j = 0; j < columNames.size(); j++) {
                    Object property = lineList.get(i).get(j);
                    String columName = String.valueOf(columNames.get(j));
                    hashMap.put(columName, property);
                }
                dataList.add(hashMap);
            }
        }
        return dataList;
    }

    /**
     * 通过实现handle方法编写我们要对每行数据的操作方式
     */
    private static RowHandler createRowHandler(List<List<Object>> lineList) {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
                //将读取到的每一行数据放入到list集合中
                JSONArray jsonObject = new JSONArray(rowList);
                lineList.add(jsonObject.toList(Object.class));
            }
        };
    }

    public static void doGet(String fileName,String path,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 创建输出流对象
        ServletOutputStream outputStream = response.getOutputStream();

        //以字节数组的形式读取文件
        byte[] bytes = FileUtil.readBytes(path);

        // 设置返回内容格式
        response.setContentType("application/octet-stream");

        response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(fileName,"UTF-8"));

        // 返回数据到输出流对象中
        outputStream.write(bytes);

        // 关闭流对象
        IoUtil.close(outputStream);

    }

}
