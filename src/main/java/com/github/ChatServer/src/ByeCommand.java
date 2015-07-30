import java.io.IOException;
public class ByeCommand implements CommandInterFace {
	private ChatUserHandler handler;
	public ByeCommand(ChatUserHandler handler) {
		
        // TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
	}
	@Override
	public void actionCommand() {
		UserManager um = UserManager.getInstance();
		um.deleteUser(handler);
		handler.close();
	}

}
