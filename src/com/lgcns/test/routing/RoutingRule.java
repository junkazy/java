package com.lgcns.test.routing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoutingRule {

	public String routing(String proxy, String ServicePath) {

		String result = null;

		List<String> fileList = readFile(proxy);
		for (String file : fileList) {

			String[] fileArr = file.split("#");
			if (ServicePath != null && ServicePath.equals(fileArr[0])) {

				String fileName = fileArr[1];
				if (fileName.startsWith("Proxy-")) {
					result = routing(fileName, ServicePath);

				} else if (fileName.startsWith("Service-")) {
					List<String> contentList = readFile(fileName);
					for (String content : contentList) {
						result = content;
					}
				}

			}

		}

		return result;
	}

	private List<String> readFile(String path) {
		List<String> list = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}
