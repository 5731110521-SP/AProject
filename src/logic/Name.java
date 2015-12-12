package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

import render.RenderableHolder;

public class Name {
	private static List<String> line;
	static {
		line = new ArrayList<String>();
		readFile();
	}

	public static int findName(String name) {
		for (int i = 0; i < line.size(); i++)
			if (line.get(i).indexOf(name) >= 0) {
				return getLevel(line.get(i));
			}
		addName(name, 1);
		return 1;
	}

	public static void addName(String name, int lv) {
		line.add(name + ":" + lv);
	}

	public static int getLevel(String line) {
		int index = line.indexOf(":");
		return Integer.parseInt(line.substring(index + 1));
	}

	public static void levelUp(String name) {
		int index = 0;
		for (int i = 0; i < line.size(); i++)
			if (line.get(i).indexOf(name) >= 0) {
				index = i;
				break;
			}
		int level = getLevel(line.get(index));
		line.remove(index);
		addName(name, level);
	}

	public static void readFile() {
		Scanner in = null;
		try {
			in = new Scanner(new File("Name.txt"));
			while (in.hasNext()) {
				line.add(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			createFile();
		}
	}

	public static void createFile() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Name.txt"));
			String str = "";
			for (String s : line) {
				str += s + "\n";
			}
			str = str.trim();
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
