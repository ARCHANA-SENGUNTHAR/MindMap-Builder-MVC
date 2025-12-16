package model;

import java.time.LocalDateTime;

public class UserNote {
    private int id;
    private int nodeId;
    private String note;
    private LocalDateTime createdAt;

    public UserNote() {
    }

    public UserNote(int id, int nodeId, String note) {
        this.id = id;
        this.nodeId = nodeId;
        this.note = note;
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}