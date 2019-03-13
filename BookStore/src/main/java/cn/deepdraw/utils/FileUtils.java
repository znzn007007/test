package cn.deepdraw.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

	public String saveImg(MultipartFile uploadFile) {

		String path = ConfigUtils.getProperties("imgPath");
		String name = uploadFile.getOriginalFilename();
		String suffix = name.substring(name.lastIndexOf("."));
		String imgName = UUID.randomUUID().toString() + suffix;
		create(path);

		File img = new File(path + imgName);
		try {

			uploadFile.transferTo(img);
		} catch (IllegalStateException | IOException e) {

			e.printStackTrace();
		}

		return imgName;
	}

	public void create(String path) {

		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {

			file.mkdir();
		}
	}

}
