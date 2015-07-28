import java.io.*;
import java.net.*;

public class ChatUserHandler extends Thread {
	Socket socket;
	BufferedReader in;
	BufferedWriter out;
	Command com;
	public User userdata;

	public ChatUserHandler(Socket sock,User u) {
		this.socket = sock;
		this.userdata = u;
	}
	void open() throws IOException{
		InputStream socketIn = this.socket.getInputStream();
		this.in = new BufferedReader(new InputStreamReader(socketIn));
		OutputStream socketOut = this.socket.getOutputStream();
		this.out = new BufferedWriter(new OutputStreamWriter(socketOut));
		this.com = new Command(this);

	}

	void close(){
		if(this.in != null){ try{ this.in.close(); } catch(IOException e){ } }
		if(this.out != null){ try{ this.out.close(); } catch(IOException e){ } }
		if(this.socket != null){ try{ this.socket.close(); } catch(IOException e){ } }
	}

	String receive() throws IOException{
		String line = in.readLine();
		return line;
	}

	public void run(){
		try {
			open();
			while(true){
				String mes = receive();
				com.setCommand(mes);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
