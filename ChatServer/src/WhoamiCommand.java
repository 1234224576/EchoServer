import java.io.IOException;


public class WhoamiCommand implements CommandInterFace {
	private ChatUserHandler handler;
	public WhoamiCommand(ChatUserHandler h) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = h;
	}
	@Override
	public void actionCommand() {
		// TODO 自動生成されたメソッド・スタブ
		try {
			handler.out.write(handler.userdata.name);
			handler.out.write("\r\n");
			handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
