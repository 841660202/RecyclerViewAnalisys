package example.wxx.com.recyclerviewanalisys.commonAdapter.chat;

public class ChatDataEntity {
    public String chatContent;
    public int isMe;

    public ChatDataEntity(String chatContent, int isMe) {
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