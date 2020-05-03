/**   
* @Title: FileUpload.java 
* @Package com.wuguan.huate.comm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午6:41:03 
* @version V1.0   
*/
package com.wuguan.huate.comm;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUpload
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午6:41:03
 * 
 */
@Component
public class FileUpload {
	@Value("${file.upload.path}")
	private String resourcePath;

	public String upload(MultipartFile file) throws CustomException {
		// 文件上传路劲
		String uploadPath = file.getOriginalFilename();
		System.err.println("uploadPath : " + uploadPath);
		// 文件后缀
		String suffix = uploadPath.substring(uploadPath.lastIndexOf(".") + 1, uploadPath.length());
		// 重新定义文件名
		String fileName = uploadPath.substring(0, uploadPath.lastIndexOf(".")) + "_" + new Date().getTime()
				+ new Random().nextInt(100) + "." + suffix;
		// 开始上传
		File saveFile = new File(resourcePath + fileName);
		if (!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/image/" + fileName;
	}

	public List<String> uploadBatch(MultipartFile[] file) throws CustomException {
		if (file.length != 0) {
			List<String> list = new ArrayList<String>();
			for (MultipartFile multipartFile : file) {
				String uploadPath = upload(multipartFile);
				list.add(uploadPath);
			}
			return list;
		}
		return null;
	}

	public void delFile(String path) throws CustomException {
		int lastIndexOf = path.lastIndexOf("/");
		String sb = path.substring(lastIndexOf + 1, path.length());
		File file = new File(resourcePath + sb);
		if (file.exists()) {
			file.delete();
		}
	}

}
