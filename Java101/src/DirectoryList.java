import java.io.*;
import java.util.*;

public class DirectoryList {
	
	public static void list(File directory) {
		for (File subFile : directory.listFiles()) {
			if (subFile.isDirectory()) {
				list(subFile);
			} else {
				System.out.println(subFile.getPath());
			}
		}
	}
	
	
	public static void find(File startDir, String search) {
		for (File subFile : startDir.listFiles()) {
			if (subFile.isDirectory()) {
				find(subFile, search);
			} else if (subFile.getPath().contains(search)) {
				System.out.println(subFile.getPath());
			}
		}
	}
}
