import java.io.IOException;


public class JoinCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String groupName;
	
	public JoinCommand(ChatUserHandler handler,String gn) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.groupName = gn;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		boolean complate = UserManager.getInstance().joinGroup(this.groupName, this.handler);
		if(!complate){
			try {
				handler.out.write("Error-グループが存在しないか、既に参加しています");
				handler.out.write("\r\n");
				handler.out.flush();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

	}

}
