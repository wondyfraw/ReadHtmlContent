package com.html;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveLink {

	public void fileWriter(List<String> linkList) {
		try {
			FileWriter fw = new FileWriter("link.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			for (int i = 0; i < linkList.size(); i++) {
				out.println(linkList.get(i));
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
