import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class PostCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String messeage;
	
	public PostCommand(ChatUserHandler handler,String mes) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.messeage = mes;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		boolean complate = false;
		String resultMes = new String();
		ArrayList<ChatUserHandler> allUsers = UserManager.getInstance().getAllUsers();
		Iterator it = allUsers.iterator();
		while(it.hasNext()){
			ChatUserHandler uh = (ChatUserHandler)it.next();
			//送信者ではなく、reject対象でない場合送信される
			if(!uh.userdata.name.equals(handler.userdata.name) && !uh.userdata.isReject(handler.userdata)){
				try {
					uh.out.write(handler.userdata.name+ "/" + messeage);
					uh.out.write("\r\n");
					uh.out.flush();
					resultMes += uh.userdata.name + ",";
					complate = true;
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
		}
		
		if(!complate) resultMes = "no one receive message";
		try {
			handler.out.write(resultMes);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
}
