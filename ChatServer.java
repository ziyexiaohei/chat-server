import java.io.IOException;
import java.net.URI;

class ChatHandler implements URLHandler {
  String chatHistory = "";
  public String handleRequest(URI url) {
    if (url.getPath().equals("/")) {
      return this.chatHistory;
    }
    // expect /chat?user=<name>&message=<string>
    else if (url.getPath().equals("/chat")) {
      String[] params = url.getQuery().split("&");
      String user = params[0].split("=")[1];
      String message = params[1].split("=")[1];
      this.chatHistory += user + ": " + message + "\n\n";
      return this.chatHistory; 
    }
    return "404 Not Found";
  }
}

class ChatMain {
  public static void main(String[] args) throws IOException {
    Server.start(4000, new ChatHandler());
  }
}
