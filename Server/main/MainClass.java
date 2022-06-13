package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import threadEx.ServerThread;

public class MainClass {

	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;
		
		ServerSocket serverSocket = new ServerSocket(9000); // 문지기 소켓
		// 버전확인, binding, Listening 다 해준다.
		
		
		ArrayList<Socket> list = new ArrayList<Socket>();
		while(true) {
			System.out.println("connecting...");
			clientSocket = serverSocket.accept();
			list.add(clientSocket); // 클라이언트 정보를담는 소켓
			
			// 접속 client 확인
			System.out.println("client IP : " + clientSocket.getInetAddress()
			+ " / Port : " + clientSocket.getPort());
			
			new ServerThread(clientSocket, list).start();

		}
		
		/*
		while(true) {
		// 수신(recv)
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()
						)
				);
		
		String str = reader.readLine();
		System.out.println("from client : " + str);
		
		str = "hello";
		
		// 송신(send)
		PrintWriter writer = new PrintWriter(
				clientSocket.getOutputStream()
				);
		
		writer.println(str);
		writer.flush(); // 주의! 빠지면 절대 안됨
		System.out.println("send to client : " + str);
		
		}*/
		
//		clientSocket.close();
//		serverSocket.close();

	}

}
