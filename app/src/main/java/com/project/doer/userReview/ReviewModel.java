package com.project.doer.userReview;

import com.google.gson.annotations.SerializedName;

public class ReviewModel {
    @SerializedName("id")

    private Integer id;
    @SerializedName("reviewer_id")

    private Integer reviewerId;
    @SerializedName("reviewee_id")

    private Integer revieweeId;
    @SerializedName("task_id")

    private Integer taskId;
    @SerializedName("marks")

    private Integer marks;
    @SerializedName("review")

    private String review;
    @SerializedName("rating")

    private Integer rating;
    @SerializedName("performance_note")

    private String performanceNote;
    @SerializedName("deadline")

    private String deadline;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("modified_date")

    private Object modifiedDate;
    @SerializedName("status")

    private Boolean status;

    public ReviewModel(Integer reviewerId, Integer revieweeId, Integer taskId, Integer marks,
                       String review, Integer rating, String performanceNote, String deadline) {
        this.reviewerId = reviewerId;
        this.revieweeId = revieweeId;
        this.taskId = taskId;
        this.marks = marks;
        this.review = review;
        this.rating = rating;
        this.performanceNote = performanceNote;
        this.deadline = deadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Integer getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(Integer revieweeId) {
        this.revieweeId = revieweeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPerformanceNote() {
        return performanceNote;
    }

    public void setPerformanceNote(String performanceNote) {
        this.performanceNote = performanceNote;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
