import java.net.*;
import java.io.*;

public class ChatServer {
	private ServerSocket server;
	public void listen(){
		Socket socket = null;
		try{
			server = new ServerSocket(18080);
			System.out.println("Echoサーバをポート18080で起動しました");

			while(true){
				socket = server.accept();
				User user = new User();
				ChatUserHandler handler = new ChatUserHandler(socket,user);
				UserManager u = UserManager.getInstance();
				u.addUser(handler);
				handler.start();
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ChatServer chats = new ChatServer();
		chats.listen();
	}
}
