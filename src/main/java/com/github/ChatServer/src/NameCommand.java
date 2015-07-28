import java.io.IOException;


public class NameCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String name;
	public NameCommand(ChatUserHandler h,String name) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = h;
		this.name = name;
	}
	@Override
	public void actionCommand() {
	
	}
}
