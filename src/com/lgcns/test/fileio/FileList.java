package com.lgcns.test.fileio;

import java.io.File;

public class FileList {
	void FileDirList() {
		File directory = new File(".");
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				System.out.println("[" + file.getName() + "]");
			} else {
				System.out.println(file.getName());
			}
		}
	}

}
