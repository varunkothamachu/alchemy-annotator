package org.apache.uima.alchemy.digester.domain;

public class CategorizationResults extends Results {

  private String category;

  private String score;

  private String url;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

}
