package com.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkController {

	private int linkCounter;
	private List<String> linkList;
	private List<String> tempLinkList;
	private int flag;
	private SaveLink saveLink;

	public LinkController() {
		linkCounter = 0;
		flag = 0;
		tempLinkList = new ArrayList<>();
		linkList = new ArrayList<>();
		saveLink = new SaveLink();
	}

	public void readContent(String urlLink) {
		HtmlContentReader hcr = new HtmlContentReader(urlLink);
		BufferedReader br = hcr.readHtmlContent();
		int count = 0;
		String inputLine;
		try {
			while ((inputLine = br.readLine()) != null) {
				count++;
				HtmlLinkExtraction htmlLinkExtraction = new HtmlLinkExtraction(inputLine);
				// htmlLinkExtraction.setSourceHtml(inputLine);

				// Runnable task = htmlLinkExtraction;
				// Thread thread = new Thread(task);
				htmlLinkExtraction.start(); // create and start the thread
				// thread.start();
				htmlLinkExtraction.run();

				ArrayList<HTMLLinkElement> listHtmlLinkElemnt = new ArrayList<HTMLLinkElement>();
				listHtmlLinkElemnt = htmlLinkExtraction.getElements();

				if (listHtmlLinkElemnt != null) {
					HTMLLinkElement htmlLinkElemet = new HTMLLinkElement();
					for (int i = 0; i < listHtmlLinkElemnt.size(); i++) {
						htmlLinkElemet = listHtmlLinkElemnt.get(i);
						if (htmlLinkElemet.getLinkAddress() != null) {

							String linkelem;
							String[] links = htmlLinkElemet.getLinkAddress().split("\"");
							if (links[1].contains("//en.wikipedia")) {
								linkelem = "https:" + links[1];
							} else if (links[1].contains("//www")
									&& (!links[1].contains("https://") && !links[1].contains("http://"))) {
								linkelem = "https:" + links[1] + "wiki/MediaWiki";
							} else if (links[1].contains("https://") || links[1].contains("http://")
									|| links[1].contains("https://www") || links[1].contains("http://www")) {
								linkelem = links[1];
							} else
								linkelem = "https://en.wikipedia.org" + links[1];

							linkCounter++;
							linkList.add(linkelem);
							if (flag == 0)
								tempLinkList.add(linkelem); //

							System.out.println(linkelem);
						}
					}
				}
			}

			br.close();

			if (tempLinkList != null) {
				if (flag == 0)
					flag = 1;
				// Files.write(Paths.get("link.txt"), linkList);
				saveLink.fileWriter(linkList);
				linkList.removeAll(linkList);
				String name = tempLinkList.get(0); // Clear the previous link
				tempLinkList.remove(tempLinkList.get(0));
				if (name != null) {
					readContent(name); // for each link make recursive call to read the comntent
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Print total link count
		System.out.print("LIne = " + count);
		System.out.println("Count= " + linkCounter);
		linkList.removeAll(linkList);
		String finalCount = "Total count = " + linkCounter;
		linkList.add(finalCount);
		saveLink.fileWriter(linkList); // finally write the rate to the file
	}

}
