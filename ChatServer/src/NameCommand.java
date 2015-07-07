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
		// TODO 自動生成されたメソッド・スタブ
		UserManager manager = UserManager.getInstance();
		if(manager.serchUserName(name) == null){
			this.handler.userdata.setName(this.name);
		}else{
			try {
				handler.out.write("Error-既に名前が使われています");
				handler.out.write("\r\n");
				handler.out.flush();
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	}
}
