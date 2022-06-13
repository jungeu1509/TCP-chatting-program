package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import threadEx.ThreadEx;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		
		
		// 1. 접속을 해야할 Server IP(주소)를 설정 -> Socket
		InetSocketAddress sockAddr = new InetSocketAddress("192.168.0.104", 9000);
		
		// 2. Socket 생성
		Socket socket = new Socket();
		
		// 3. Connect
		try {
			socket.connect(sockAddr, 10000);

			// 4. Packet 송수신(send, recv)
			
			InetAddress inetAddr = socket.getInetAddress();
			if(inetAddr != null) {
				System.out.println("server connect success");
			} else {
				System.out.println("server connect fail");
			}
			
			new ThreadEx(socket).start();
			
			while(true) {
			
				String str = "hahahahahahahaha";
				System.out.println("[type message to send] : ");
				str = in.next();
				
				// 송신(send)
				PrintWriter writer = new PrintWriter(
						socket.getOutputStream()
						);
				
				writer.println(str);
				writer.flush(); // 주의! 빠지면 절대 안됨
				System.out.println("send to server : " + str);
//				
//				// 수신(recv)
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(
//								socket.getInputStream()
//								)
//						);
//				
//				str = reader.readLine();
//				System.out.println("from server : " + str);
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
