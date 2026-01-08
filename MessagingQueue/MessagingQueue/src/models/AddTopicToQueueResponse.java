package models;

public class AddTopicToQueueResponse {
    public boolean isSuccess;
    public String message;
    public AddTopicToQueueResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
