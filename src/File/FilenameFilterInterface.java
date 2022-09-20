package File;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

class JavaFilenameFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}
}

public class FilenameFilterInterface {
	public static void main(String[] args) {
		File file = new File("./src/File");

//		System.out.println(Arrays.toString(file.list()));
		
		JavaFilenameFilter filter = new JavaFilenameFilter();
		
		for(String path : file.list(filter)) {
			System.out.println(path);
		}
	}
}
