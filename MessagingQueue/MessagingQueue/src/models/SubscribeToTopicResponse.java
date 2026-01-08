package models;

public class SubscribeToTopicResponse {
    public boolean isSuccess;
    public String message;
    public SubscribeToTopicResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
