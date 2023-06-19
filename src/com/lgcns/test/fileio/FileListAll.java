package com.lgcns.test.fileio;

import java.io.File;

public class FileListAll {
	void FileSearchAll(String path) {
		File directory = new File(path);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				FileSearchAll(file.getPath());
			} else {
				System.out.println(file.getName());
			}
		}
	}

}
