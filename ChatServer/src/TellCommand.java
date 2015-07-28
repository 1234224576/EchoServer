import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TellCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String name; //グループネームかユーザーネーム
	private String message;
	public TellCommand(ChatUserHandler handler,String name,String mes) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.name = name;
		this.message = mes;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		String resultMes = new String();
		
		//引数がuser nameの場合ここで送信される
		ArrayList<ChatUserHandler> allUsers = UserManager.getInstance().getAllUsers();
		Iterator it = allUsers.iterator();
		while(it.hasNext()){
			ChatUserHandler uh = (ChatUserHandler)it.next();
			//送信者ではなく、reject対象でもなかったら送信される
			if(uh.userdata.name.equals(this.name) && !uh.userdata.isReject(handler.userdata)){
				String mes = handler.userdata.name+ "/" + this.message;
				_showConsole(uh, mes);	
				resultMes += uh.userdata.name;
			}
		}
		
		//引数がgroup nameであった場合ここ以下で送信される
		ArrayList<ChatUserHandler> groupUsers = UserManager.getInstance().getGroupUsers(this.name);
		
		//もしBANリストに登録されているなら送信不可能
		if(UserManager.getInstance().isBanList(this.name, this.handler.userdata.name)){
			_showConsole(this.handler, "Error-BANされています");	
			return;
		}
		
		if(groupUsers != null){
			it = groupUsers.iterator();
			while(it.hasNext()){
				ChatUserHandler uh = (ChatUserHandler)it.next();
				//送信者ではなく、reject対象でもなかったら送信される
				if(!uh.userdata.name.equals(this.handler.userdata.name) && !uh.userdata.isReject(handler.userdata)){
					String mes = handler.userdata.name+ "/" + this.message;
					_showConsole(uh, mes);	
					resultMes += uh.userdata.name +",";
				}
			}
		}
		
		if(resultMes.isEmpty()){
			resultMes = "no one receive message";
		}
		_showConsole(this.handler, resultMes);
	}
	
	private void _showConsole(ChatUserHandler handler,String mes){
		try {
			handler.out.write(mes);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
