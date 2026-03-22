package genricUtilites;

import java.io.FileInputStream;
import java.util.Properties;

public class Propertiesfileutility {
	public   String toReadDataFrompropertiesfile(String Key) throws Throwable {
		FileInputStream fis =new FileInputStream("./src/test/resources/config.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String value = prop.getProperty(Key);
		fis.close();
		return value;
	}

}
