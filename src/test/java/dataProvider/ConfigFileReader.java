package dataProvider;

import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	 private static ConfigFileReader ConfigFileReader;
	public String getReportConfigPath(){
	 String reportConfigPath = properties.getProperty("reportConfigPath");
	 if(reportConfigPath!= null) return reportConfigPath;
	 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
	}
}
