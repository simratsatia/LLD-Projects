package models;

import entities.Message;
import java.util.List;

public class ConsumeMessagesResponse {
    public boolean isSuccess;
    public String message;
    public List<Message> messages;
    public int newOffset;

    public ConsumeMessagesResponse(boolean isSuccess, String message, List<Message> messages, int newOffset) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.messages = messages;
        this.newOffset = newOffset;
    }
}
