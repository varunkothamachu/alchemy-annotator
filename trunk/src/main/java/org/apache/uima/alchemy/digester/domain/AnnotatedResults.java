package org.apache.uima.alchemy.digester.domain;

public class AnnotatedResults extends Results {
	
	private String url;
	private String text;
	private String annotatedText;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAnnotatedText() {
		return annotatedText;
	}
	public void setAnnotatedText(String annotatedText) {
		this.annotatedText = annotatedText;
	}

}
