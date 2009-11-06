package org.apache.uima.alchemy.digester.domain;

public class Entity {
	
	private String type;
	private String relevance;
	private String count;
	private String text;
	private Disambiguated disambiguated;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelevance() {
		return relevance;
	}
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
  public void setDisambiguated(Disambiguated disambiguated) {
    this.disambiguated = disambiguated;
  }
  public Disambiguated getDisambiguated() {
    return disambiguated;
  }
	

}
