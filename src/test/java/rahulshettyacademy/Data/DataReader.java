package rahulshettyacademy.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getJasonDataToMap(String path) throws IOException
	{
		String jasonContent = FileUtils.readFileToString(new File(path));
		
		//converting string in hash map with the help of object mapper class
		
		ObjectMapper obj=new ObjectMapper();
		
		List<HashMap<String,String>> data= obj.readValue(jasonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
}
