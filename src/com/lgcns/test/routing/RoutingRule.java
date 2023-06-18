package com.lgcns.test.routing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoutingRule {

	public String routing(String proxy) {

		String result = null;

		List<String> serviceList = readFile(proxy + ".txt");
		for (String service : serviceList) {
			List<String> contentList = readFile(service);
			for (String content : contentList) {
				result = content;
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
