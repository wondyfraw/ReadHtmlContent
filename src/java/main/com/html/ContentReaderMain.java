package com.html;

public class ContentReaderMain {

	public static void main(String[] args) {

		String urlLink = "https://en.wikipedia.org/wiki/Europe";
		LinkController linkController = new LinkController();
		linkController.readContent(urlLink);

	}

}
