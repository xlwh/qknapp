package com.qing.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * Description：文件处理工具类
 * 
 * @author luoqinglong
 * @date 2013-7-8 下午4:17:41
 * @since 1.0
 **/
public class FileUtils {
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 
	 * Description:上传(拷贝)文件
	 * 
	 * @param uploadFile
	 *            要上传(拷贝) 的源文件
	 * @param filePath
	 *            目标文件路径
	 * @param fileName
	 *            目标文件名
	 * @return
	 * 
	 */
	public static boolean upload(File uploadFile, String filePath, String fileName) {
		boolean flag = false;
		if (uploadFile.length() == 0) {
			return flag;
		}
		File dir = new File(filePath);
		if (!dir.exists())// 判断文件夹是否存在
		{
			dir.mkdirs();
		}
		try {
			FileCopyUtils.copy(uploadFile, new File(filePath + File.separator + fileName));
			flag = true;
		} catch (IOException e) {
			flag = false;
			FileUtils.logger.error("文件拷贝失败!from:[" + uploadFile.getAbsolutePath() + " ],to:[" + filePath + fileName
					+ "]", e);
		}

		return flag;
	}

	public static String getNewFileName(String oldFileName) {
		String newName = StringUtil.GenerateUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
		return newName;
	}

	public static boolean delFile(File file) {
		if (file.exists() && file.isFile()) {
			return file.delete();
		}
		return true;
	}

	/**
	 * 取文件的最后修改时间
	 * @param file
	 * @return
	 */
	public static Date getFileModifyTime(File file) {
		Long time = file.lastModified();
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(time);
		return cd.getTime();
	}

	/**
	 * 删除文件夹下dayNum天前的文件
	 * @param filepath
	 * @param dayNum
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean deletefile(String filepath, int dayNum) throws FileNotFoundException, IOException {
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				if (DateTimeUtils.diffDate(DateTimeUtils.getCurrentTimestamp(), FileUtils.getFileModifyTime(file)) > dayNum) {
					file.delete();
				}
				//文件夹
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						if (DateTimeUtils.diffDate(DateTimeUtils.getCurrentTimestamp(),
								FileUtils.getFileModifyTime(readfile)) > dayNum) {
							readfile.delete();
						}
					} else if (readfile.isDirectory()) {
						FileUtils.deletefile(filepath + "\\" + filelist[i], dayNum);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) {
		//		String path = "D:\\tools\\java\\apache-tomcat-7.0.34\\webapps\\androidApp\\wsi_survey.apk";
		//		File file = new File(path);
		//		System.out.println("===========" + getFileModifyTime(file));
		try {
			FileUtils.deletefile("D:\\tools\\java\\apache-tomcat-7.0.34\\webapps\\test", 7);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
