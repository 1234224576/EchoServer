import java.io.IOException;
public class UsersCommand implements CommandInterFace {
	private ChatUserHandler handler;
	public UsersCommand(ChatUserHandler handler) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		UserManager userManager = UserManager.getInstance();
		String result = userManager.listUser(this.handler);
		
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
