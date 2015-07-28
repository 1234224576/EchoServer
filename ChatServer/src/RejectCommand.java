import java.io.IOException;


public class RejectCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String key;
	
	public RejectCommand(ChatUserHandler handler,String key) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.key = key;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		String result =   new String();
		if(key.equals("NOTSTR344739")){
			result = handler.userdata.showRejectList();
			_showConsole(this.handler, result);
			return;
		}
		
		ChatUserHandler user = UserManager.getInstance().serchUserName(key);
		if(user == null){
			try {
				handler.out.write("Error-指定されたユーザは存在しません");
				handler.out.write("\r\n");
				handler.out.flush();
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(!handler.userdata.isReject(user.userdata)){
			this.handler.userdata.addReject(user.userdata);
		}else {
			this.handler.userdata.delReject(user.userdata);
		}
		result = handler.userdata.showRejectList();
		_showConsole(this.handler, result);

	}
	
	private void _showConsole(ChatUserHandler handler,String result){
		try {
			handler.out.write(result);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
