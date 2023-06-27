package com.hzapt.common.utils;

import com.hzapt.common.exceptions.MyException;
import net.lingala.zip4j.ZipFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩工具
 */
public class ZipUtil {

    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩成ZIP 方法1
     *
     * @param fileDir          原文件夹路径
     * @param zipDir           压缩文件夹路径
     * @param zipName          压缩包名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws MyException 压缩失败会抛出运行时异常
     */
    public static void toZip2(String fileDir, String zipDir, String zipName, boolean KeepDirStructure) {
        ZipOutputStream zos = null;
        try {
            FileOutputStream out = new FileOutputStream(zipDir + "/" + zipName);
            zos = new ZipOutputStream(out);
            File sourceFile = new File(fileDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        } catch (Exception e) {
            throw new MyException("zip error from ZipUtils");
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos,
                                 String name, boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),
                                KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @param path
     * @param response
     * @throws Exception
     */
    public static void downLoadZip2(String fileName, String path,
                                    HttpServletResponse response) throws Exception {
        File file = new File(path);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes("ISO8859-1"), StandardCharsets.UTF_8));
        response.setContentLength((int) file.length());
        response.setContentType("application/zip");// 定义输出类型
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
     * 使用给定密码解压指定压缩文件到指定目录
     *
     * @param inFile 指定Zip文件
     * @param outDir 解压目录
     * @param passwd 解压密码
     */
    public static boolean unzip(String inFile, String outDir, String passwd) {
        File file = new File(inFile);
        return unzip(file, outDir, passwd);
    }

    /**
     * 使用给定密码解压指定压缩文件到指定目录
     *
     * @param inFile 指定Zip文件
     * @param outDir 解压目录
     * @param passwd 解压密码
     */
    public static boolean unzip(File inFile, String outDir, String passwd) {
        File outDirectory = new File(outDir);
        if (!outDirectory.exists()) {
            outDirectory.mkdir();
        }
        try {
            new ZipFile(inFile, passwd.toCharArray()).extractAll(outDir);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
