package com.html;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlLinkExtraction implements Runnable {

	private Matcher mTag, mLink;
	private Pattern pTag, pLink;
	private ArrayList<HTMLLinkElement> elements;
	private String sourceHtml;
	private Thread thread;
	private String linkName;

	private static final String HTML_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	public HtmlLinkExtraction(String sourceHtml) {
		pTag = Pattern.compile(HTML_TAG_PATTERN);
		pLink = Pattern.compile(HTML_HREF_TAG_PATTERN);
		this.sourceHtml = sourceHtml;
	}

	public HtmlLinkExtraction() {

	}

	@Override
	public void run() {
		elements = new ArrayList<HTMLLinkElement>();
		mTag = pTag.matcher(sourceHtml);

		while (mTag.find()) {
			String href = mTag.group(1); // get the values of href
			String linkElem = mTag.group(2); // get the text of link Html Element

			mLink = pLink.matcher(href);
			while (mLink.find()) {
				String link = mLink.group(1);
				HTMLLinkElement htmlLinkElement = new HTMLLinkElement();
				htmlLinkElement.setLinkAddress(link);
				htmlLinkElement.setLinkElement(linkElem);

				this.elements.add(htmlLinkElement);
			}
		}

	}

	public void start() {

		thread = new Thread(thread);
		thread.start();

	}

	public ArrayList<HTMLLinkElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<HTMLLinkElement> elements) {
		this.elements = elements;
	}

	public String getSourceHtml() {
		return sourceHtml;
	}

	public void setSourceHtml(String sourceHtml) {
		this.sourceHtml = sourceHtml;
	}

	public static void stop() {
		Thread.currentThread().interrupt();
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

}
