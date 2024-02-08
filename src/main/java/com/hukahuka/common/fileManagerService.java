package com.hukahuka.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {

	// 학원용
	public static final String FILE_UPLOAD_PATH = "D:\\jeonsoohyun\\7_project\\new_stiker\\stiker_workspace\\images/";
	
	// 집
	
	
	// input: File 원본, userLoginId(폴더명)		output: 이미지 경로
	public String saveFile(String loginId, MultipartFile file) {
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null;
		}
		
		try {
			byte[] btyes = file.getBytes();
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
			Files.write(path, btyes); // 파일 업로드
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// http://localhost/images/aaaa_1234354/gg.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
	
}
