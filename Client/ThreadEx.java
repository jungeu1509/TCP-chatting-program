package threadEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadEx extends Thread{

	Socket socket;

	public ThreadEx(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				// 수신(recv)
				BufferedReader reader;
				
					reader = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()
									)
							);
				
				String str = reader.readLine();
				System.out.println("from server : " + str);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	
}
