import java.io.IOException;
public class MembersCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String groupName;
	
	public MembersCommand(ChatUserHandler handler,String gn) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.groupName = gn;
	}
	
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		String result = UserManager.getInstance().listGroupMember(this.groupName);
		if(result.isEmpty()) result ="Error-グループが存在しません";
		try {
			handler.out.write(result);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
