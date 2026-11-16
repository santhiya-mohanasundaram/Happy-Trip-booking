package com.tripease.travelbooking.dto;

public class LikeResponse {

    private String packageId;
    private boolean liked;
    private Integer totalLikes;

    public LikeResponse() {}

    public LikeResponse(String packageId, boolean liked, Integer totalLikes) {
        this.packageId = packageId;
        this.liked = liked;
        this.totalLikes = totalLikes;
    }

    public String getPackageId() { return packageId; }
    public void setPackageId(String packageId) { this.packageId = packageId; }

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }

    public Integer getTotalLikes() { return totalLikes; }
    public void setTotalLikes(Integer totalLikes) { this.totalLikes = totalLikes; }
}
