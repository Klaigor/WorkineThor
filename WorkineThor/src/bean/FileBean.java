package bean;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileBean {

	private Logger logger = Logger.getLogger(FileBean.class.getName());
	private String filePath;
	private String fileName;
	
	public void setFilePath(String filePath) {
		if(filePath.contains("https") || filePath.contains("."))
			this.filePath = filePath;
		else 
			logger.log(Level.WARNING, "this is not a file");
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
}
