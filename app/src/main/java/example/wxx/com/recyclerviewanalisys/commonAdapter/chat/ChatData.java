package example.wxx.com.recyclerviewanalisys.commonAdapter.chat;

public class ChatData {
    public String chatContent;
    public int isMe;

    public ChatData(String chatContent, int isMe) {
        this.chatContent = chatContent;
        this.isMe = isMe;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public int getIsMe() {
        return isMe;
    }

    public void setIsMe(int isMe) {
        this.isMe = isMe;
    }
}