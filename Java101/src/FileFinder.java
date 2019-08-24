import java.io.*;
import java.util.*;
public class FileFinder 
{
	public static void find(String directoryName, String searchWord)
	{
		File[] directoryList = DirectoryList.getFileList(directoryName);
		
		if (directoryList == null) // This is a precaution, shouldn't really happen unless the inital call to find gives an invalid directoryName.
			return;
		
		for (File file : directoryList)
		{
			String name = file.getName();
			
			if (name.contains(searchWord))
				System.out.println(name);
			
			if (file.isDirectory())
				find(name, searchWord);
		}
	}

}
