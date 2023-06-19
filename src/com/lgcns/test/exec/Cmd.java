package com.lgcns.test.exec;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Cmd {

	public static String getProcessOutput(List<String> cmdList) throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(cmdList);
		Process process = builder.start();
		InputStream psout = process.getInputStream();
		byte[] buffer = new byte[1024];
		psout.read(buffer);
		return (new String(buffer));
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String output = getProcessOutput(Arrays.asList("add_2sec.exe", "2", "3"));
		System.out.println(output);
	}

}
