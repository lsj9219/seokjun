package gcmserver;
import java.io.IOException;

import java.util.ArrayList;
 
import java.util.List;
 
 
 
import com.google.android.gcm.server.Message;
 
import com.google.android.gcm.server.MulticastResult;
 
import com.google.android.gcm.server.Result;
 
import com.google.android.gcm.server.Sender;
 
 
 
public class server{
 
public void sendMessage() throws IOException {
 
Sender sender = new Sender("AIzaSyDVK2jOrei8ZgxzSSJVUEbbgAL4yYQVRqg");
 
 
 
String regId = "926497816162";
 
 
 
Message message = new Message.Builder().addData("msg", "push notify").build();
 
 
 
List<String> list = new ArrayList<String>();
 
list.add(regId);
 
 
 
MulticastResult multiResult = sender.send(message, list, 5);
 
 
 
if (multiResult != null) {
 
List<Result> resultList = multiResult.getResults();
 
 
 
for (Result result : resultList) {
 
System.out.println(result.getMessageId());
 
}
 
}
 
}
 
 
 
public static void main(String[] args) throws Exception {
 
server s = new server();
 
s.sendMessage();
 
}
 
}