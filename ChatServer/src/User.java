import java.io.IOException;
import java.util.*;
public class User {
	public String name;
	public String groupName = null;
	public ArrayList<User> rejectList = new ArrayList<User>();
	
	public User() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = _createUndefinedName();
	}
	
	private String _createUndefinedName(){
		UserManager u = UserManager.getInstance();
		String name = "undefined" + u.getUserNum();
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void addReject(User user){
		rejectList.add(user);
	}
	
	public void delReject(User user){
		rejectList.remove(user);
	}
	
	public boolean isReject(User key){
		return rejectList.contains(key);
	}
	
	public String showRejectList(){
		Iterator it = rejectList.iterator();
		String result = new String();
		while(it.hasNext()){
			User user = (User)it.next();
			result += user.name + ",";
		}
		return result;
	}
	
}
