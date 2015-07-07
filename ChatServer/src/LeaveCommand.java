import java.io.IOException;


public class LeaveCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String groupName;
	
	public LeaveCommand(ChatUserHandler handler,String gn) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.groupName = gn;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		boolean complate = UserManager.getInstance().leaveGroup(this.groupName, this.handler);
		if(!complate){
			try {
				handler.out.write("Error-あなたはこのグループのメンバーではありません");
				handler.out.write("\r\n");
				handler.out.flush();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

	}

}
