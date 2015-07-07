import java.io.IOException;
public class GroupsCommand implements CommandInterFace {
	private ChatUserHandler handler;
	public GroupsCommand(ChatUserHandler handler) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		String result = UserManager.getInstance().listGroup();
		try {
			handler.out.write(result);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
