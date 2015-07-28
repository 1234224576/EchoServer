import java.util.*;

public class Command {
	private String command;
	private ChatUserHandler handler;
	private Map<String, CommandInterFace> comMap; //コマンドを格納するマップ
	private String[] comText; //入力されてきたコマンド
	private String[] args = new String[3]; //コマンドの引数を格納

	public Command(ChatUserHandler handler){
		this.handler = handler;

	}
	public void setCommand(String command) {
		if(command == null) return;
		this.command = command;
		_actionCommand();
	}

	private void _actionCommand(){
		comText = this.command.split(" ");
		if(comText.length < 2){args[0] = "NOTSTR";} else {args[0] = comText[1];}
		if(comText.length < 3){args[1] = "NOTSTR";} else {args[1] = comText[2];}

		_initCommandMap();
		if(comMap.containsKey(comText[0])){
			comMap.get(comText[0]).actionCommand();
		}
	}

	private void _initCommandMap(){
		comMap = new HashMap<String,CommandInterFace>();
		comMap.put("help", new HelpCommand(handler));
		comMap.put("name", new NameCommand(handler,args[0]));
		comMap.put("whoami", new WhoamiCommand(handler));
		comMap.put("users", new UsersCommand(handler));
		comMap.put("bye", new ByeCommand(handler));
		comMap.put("post", new PostCommand(handler,args[0]));
		comMap.put("tell", new TellCommand(handler,args[0],args[1]));
	}
}
