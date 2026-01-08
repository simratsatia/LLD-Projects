package models;

public class PublishMessageToTopicResponse {
    public boolean isSuccess;
    public String message;

    public PublishMessageToTopicResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
