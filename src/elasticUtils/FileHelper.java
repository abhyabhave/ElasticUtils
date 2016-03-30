package elasticUtils;

import java.io.File;

import org.apache.log4j.Logger;

public class FileHelper {
	
	static Logger logger = Logger.getLogger("FileHelper");
	
	public FileHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean fileExists(File file) {
		
		if(!file.exists()) {
			logger.error("The file does not exist. Or you do not have permissions to access the file");
			return false;
		}
		return true;
	}

}
