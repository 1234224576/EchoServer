import java.io.IOException;
import java.util.*;
public class UserManager {
	private volatile static UserManager uniqueInstance;
	private ArrayList<ChatUserHandler> allUsers = new ArrayList<ChatUserHandler>(); //全ユーザ情報を格納
	private Map<String, List> groupMap = new TreeMap<String, List>(); //各グループのメンバーのlistを格納
	private Map<String, List> groupBanMap = new TreeMap<String, List>(); //各グループのban情報のlistを格納

	//newできないようにコンストラクタはプライベート
	private UserManager() { }

	//共通のインスタンスを返す
	public static UserManager getInstance(){
		if(uniqueInstance == null){
			//初回起動時のみ同期化
			synchronized (UserManager.class) {
				if(uniqueInstance == null){
					uniqueInstance = new UserManager();
				}
			}
		}
		return uniqueInstance;
	}
	//グループを作成
	public boolean addGroup(String groupName,ChatUserHandler handler){
		ArrayList<ChatUserHandler> group = new ArrayList<ChatUserHandler>();
		group.add(handler);
		if(!groupMap.containsKey(groupName)){
			groupMap.put(groupName, group);
			return true;
		}
		return false;
	}
	//グループから脱退
	public boolean leaveGroup(String groupName,ChatUserHandler handler){
		if(groupMap.containsKey(groupName)){
			List<ChatUserHandler> groupList =  groupMap.get(groupName);
			if(groupList.contains(handler)){
				groupList.remove(handler);

				if(groupList.isEmpty()){
					groupMap.remove(groupName);
					groupBanMap.remove(groupName);
				}
				return true;
			}
		}
		return false;
	}
	//グループに参加
	public boolean joinGroup(String groupName,ChatUserHandler handler){
		if(groupMap.containsKey(groupName)){
			List<ChatUserHandler> groupList =  groupMap.get(groupName);
			if(groupList.contains(handler)) return false;
			groupList.add(handler);
			return true;
		}
		return false;
	}
	//グループ一覧を返す
	public String listGroup(){
		String resultStr = new String();
		for (String k : groupMap.keySet()) {
	        resultStr += k +",";
	    }
		return resultStr;
	}
	//グループのメンバー一覧を返す
	public String listGroupMember(String groupName){
		String resultStr = new String();
		if(groupMap.containsKey(groupName)){
			resultStr += "*";
			List<ChatUserHandler> groupList =  groupMap.get(groupName);
			Iterator it = groupList.iterator();
			while(it.hasNext()){
				ChatUserHandler uh = (ChatUserHandler)it.next();
				resultStr += uh.userdata.name +",";
			}
		}
		return resultStr;
	}

	//グループのメンバー情報が格納されたListを返す
	public ArrayList<ChatUserHandler> getGroupUsers(String groupName){
		return (ArrayList<ChatUserHandler>)groupMap.get(groupName);
	}

	//BANリストにユーザーを追加
	public String addBanUser(String groupName,String userName,ChatUserHandler myHandler){
		//グループにBANリストがまだ作られてない場合は新規作成
		if(groupMap.containsKey(groupName) && !groupBanMap.containsKey(groupName)){
			ArrayList<ChatUserHandler> banList = new ArrayList<ChatUserHandler>();
			groupBanMap.put(groupName, banList);
		}

		if(groupBanMap.containsKey(groupName)){
			List<ChatUserHandler> groupList =  groupMap.get(groupName);
			List<ChatUserHandler> banList =  groupBanMap.get(groupName);

			//groupListの0番目の要素が管理者
			if(!groupList.get(0).equals(myHandler)) return "Error-管理者権限がありません";

			ChatUserHandler uh = serchUserName(userName);
			if(!banList.contains(uh) && uh != null){
				banList.add(uh);
				return userName+"さんをBANリストに登録しました";
			}else if(uh != null){
				banList.remove(uh);
				return userName+"さんをBANリストから削除しました";
			}
		}
		return "Error-グループかユーザが存在しません";
	}

	//指定した人物がBANリストに含まれているかどうか
	public boolean isBanList(String groupName,String userName){
		if(groupBanMap.containsKey(groupName)){
			List<ChatUserHandler> banList =  groupBanMap.get(groupName);
			ChatUserHandler uh = serchUserName(userName);
			if(banList.contains(uh) && uh != null){
				return true;
			}
		}
		return false;
	}

	//ユーザ追加
	public void addUser(ChatUserHandler u){
		allUsers.add(u);
	}

	//ユーザ削除
	public void deleteUser(ChatUserHandler key){
		allUsers.remove(key);
	}

	//ユーザを検索して存在するならChatUserHandlerインスタンスを返す
	public ChatUserHandler serchUserName(String key){
		Iterator it = allUsers.iterator();
		while(it.hasNext()){
			ChatUserHandler uh = (ChatUserHandler)it.next();
			if(uh.userdata.name.equals(key)){
				return uh;
			}
		}
		return null;
	}

	//全ユーザの情報を返す
	public String listUser(ChatUserHandler handler){
		Collections.sort(allUsers,new ChatUserHandlerComparator());
		Iterator it = allUsers.iterator();
		String result = new String();
		while(it.hasNext()){
			ChatUserHandler uh = (ChatUserHandler)it.next();
			result += uh.userdata.name + ",";
		}
		return result;
	}

	//全てのユーザハンドラが格納されたリストを返す
	public ArrayList<ChatUserHandler> getAllUsers(){
		return allUsers;
	}
	//ユーザの人数を返す
	public int getUserNum(){
		return allUsers.size();
	}
}
