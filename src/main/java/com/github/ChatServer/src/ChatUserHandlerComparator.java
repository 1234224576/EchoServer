import java.util.Comparator;


public class ChatUserHandlerComparator implements Comparator<ChatUserHandler> {
	public int compare(ChatUserHandler a,ChatUserHandler b){
		if(a.userdata.name.compareTo(b.userdata.name) > 0){
			return 1;
		}else if(a.userdata.name.compareTo(b.userdata.name) == 0){
			return 0;
		}else{
			return -1;
		}
	}
}
