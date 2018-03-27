package com.html;

public class HTMLLinkElement {

	String linkElement;
	String linkAddress;

	public String getLinkElement() {
		return linkElement;
	}
	public void setLinkElement(String linkElement) {
		this.linkElement = linkElement;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		//this.linkAddress = replaceInvalidChar(linkElement);
		this.linkAddress = linkAddress;
	}

	private String replaceInvalidChar(String linkElement) {
		//linkElement = linkElement.replaceAll("'", "");
		linkElement = linkElement.replaceAll("\"", "");
		return linkElement;
	}

}
