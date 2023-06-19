package com.lgcns.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketP {

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		try {
			Socket socket = listener.accept();
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("test");
			} finally {
				socket.close();
			}
		} finally {
			listener.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Socket s = new Socket("127.0.0.1", 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		System.out.println(answer);
	}

}
