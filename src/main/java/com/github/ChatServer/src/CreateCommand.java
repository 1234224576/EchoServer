import java.io.*;


public class CreateCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String groupname;
	public CreateCommand(ChatUserHandler handler,String n) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.groupname = n;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		boolean complate = UserManager.getInstance().addGroup(this.groupname, this.handler);
		if(!complate){
			try {
				handler.out.write("Error-グループを作成できませんでした");
				handler.out.write("\r\n");
				handler.out.flush();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

	}

}
