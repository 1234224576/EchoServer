import java.io.IOException;
public class BanCommand implements CommandInterFace {
	
	private ChatUserHandler handler;
	private String groupname;
	private String userName;
	public BanCommand(ChatUserHandler handler,String gn,String un) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.groupname = gn;
		this.userName = un;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		String result = UserManager.getInstance().addBanUser(this.groupname, this.userName,this.handler);

		try {
			handler.out.write(result);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
