import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TellCommand implements CommandInterFace {
	private ChatUserHandler handler;
	private String name; //グループネームかユーザーネーム
	private String message;
	public TellCommand(ChatUserHandler handler,String name,String mes) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
		this.name = name;
		this.message = mes;
	}
	@Override
	public void actionCommand() {
	
	}

	private void _showConsole(ChatUserHandler handler,String mes){

	}
}
