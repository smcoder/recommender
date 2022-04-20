package org.smcoder.recommend.business.model.dto;

public class ItemCFRecommendationRequest {

    private int id;

    public ItemCFRecommendationRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
