package model;

import java.time.LocalDate;

public class Node {
    private int id;
    private String title;
    private String topic;
    private String content;
    private LocalDate createdAt;
    private LocalDate nextReviewDate;

    public Node() {
    }

    public Node(int id, String title, String topic, String content) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.createdAt = LocalDate.now();
        this.nextReviewDate = LocalDate.now();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }
}