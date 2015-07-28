import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class PostCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String messeage;

	public PostCommand(ChatUserHandler handler,String mes) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.messeage = mes;
	}
	@Override
	public void actionCommand() {

	}
}
