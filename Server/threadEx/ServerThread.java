package threadEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread{
	
	Socket socket;
	List<Socket> list;
	
	
	public ServerThread(Socket socket, List<Socket> list) {
		super();
		this.socket = socket;
		this.list = list;
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
			System.out.println("from client" +"("+socket.getPort()+") : " + str);
			
			for(int i = 0 ; i < list.size(); i++) {
				Socket sendSocket = list.get(i);
				if(sendSocket.equals(socket)) continue;
				// 송신(send)
				PrintWriter writer = new PrintWriter(
						sendSocket.getOutputStream()
						);
				
				writer.println(str);
				writer.flush(); // 주의! 빠지면 절대 안됨
				System.out.println("send to client : " + str);
			}
			
			
			Thread.sleep(300);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Disconnect : "+socket.getPort());
			list.remove(socket);
			
			try {
				for(int i = 0 ; i < list.size(); i++) {
					Socket sendSocket = list.get(i);
					// 송신(send)
					PrintWriter writer;
					
					writer = new PrintWriter(
							sendSocket.getOutputStream()
							);
					
					String str = "Disconnect "+socket.getPort();
					writer.println(str);
					writer.flush(); // 주의! 빠지면 절대 안됨
					System.out.println("send to client : " + str);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			return;
		}
		
		
	}
	
	
	
}
