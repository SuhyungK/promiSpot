package com.ssafy.promispot.member.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.promispot.member.controller.MemberController;
import com.ssafy.promispot.member.model.entity.FileEntity;

@Component
public class FileHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(FileHandler.class);
	
	// ��Ʈ ��� �ҷ�����
    private final String rootPath = "C:\\Temp";
//    private final String rootPath = System.getProperty("user.dir");
    
    // ������Ʈ ��Ʈ ��ο� �ִ� ���丮
    private final String fileDir = rootPath + "\\upload\\";
    
    public String getFullPath(String filename) { 
    	return fileDir + filename; 
    }
	
	public FileEntity saveFile(MultipartFile multipartFile) {
		
		// �ۼ��ڰ� ���ε��� ���ϸ�
		String originalFileName = multipartFile.getOriginalFilename();
		
		// �������� �����ϴ� ����. ���ϸ��� �ߺ����� �ʰԲ� UUID�� ���ϰ� ".Ȯ����"�� �״��
        String storeFilename = UUID.randomUUID() + "." + extractExt(originalFileName);
        
        
        File folder = makeFolder();
        
		FileEntity file = new FileEntity(folder.toString()
										, multipartFile.getOriginalFilename()
										, storeFilename
										, multipartFile.getSize());
		
		// ���� ����
		File newFile = new File(folder, storeFilename);
		try {
			multipartFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;		
	}//saveFile

	private File makeFolder() {
		
		// ���� ��¥�� ���� ����
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());
		
		File folder = new File(fileDir);
		
        if(!folder.exists()) {
        	if(folder.mkdir()) {
        		logger.info("files ���� ����");
        	}else {
        		logger.info("files ���� ����");        		
        	}
        }
        
        // File.separator : Windows('\'), Linux, MAC('/')
        folder = new File(folder + File.separator + current_date);
        if(!folder.exists()) {
        	if(folder.mkdir()) {
        		logger.info("��¥ ���� ���� ����");
        	}else {
        		logger.info("��¥ ���� ���� ����");        		
        	}
        }
        
		return folder;
	}//makeFolder

	// Ȯ���� ����
	private String extractExt(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
	}//extractExt

}//FileHandler
