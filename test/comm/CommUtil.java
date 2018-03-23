package comm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;


public class CommUtil {
	/** 不要和Properties混用 */
	public static ResourceBundle bun = ResourceBundle.getBundle("config");
	public static Properties pps = new Properties();
	
	static {
		try {
			pps.load(new FileInputStream("test/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getBundle(String key){
		return bun.getString(key);
	}
	
	public static String getProperties(String key) throws Exception{
		return pps.getProperty(key);
	}
	
	public static void setProperties(String key, String value) throws Exception {
		pps.setProperty(key, value);
		pps.store(new FileOutputStream("test/config.properties"), "Update " + key + " name");
	}
	
}
