import java.io.IOException;


public class HelpCommand implements CommandInterFace {
	private ChatUserHandler handler;
	public HelpCommand(ChatUserHandler handler) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.handler = handler;
	}

	@Override
	public void actionCommand() {
		try {
		handler.out.write(
				"hrlp - 処理可能な命令一覧を表示\n"
				+ "name - 名前を設定\n"
				+ "whoami - 現在設定されてる名前を表示\n"
				+ "users - チャットに参加しているメンバの名前を表示\n"
				+ "bye - チャットを終了\n"
				+ "post - 全員にメッセージを送信\n"
				+ "tell - 指定された名前のメンバにのみメッセージを送信\n");
		handler.out.write("\r\n");
		handler.out.flush();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	
	}

}
