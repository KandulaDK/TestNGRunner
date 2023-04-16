package functionalLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ReusableAction {

//	private static Logger objLgr = LogManager.getLogger(ReusableAction.class.getName());
	public WebDriver webDriver = null;
	public ExtentTest test = null;
	
	public ReusableAction(WebDriver webDriver, ExtentTest test) {
		this.webDriver = webDriver;
		this.test = test;
	}

	public ReusableAction() {
		
	}
	/*Method to generate Random Number using current Date Time concept
	Created By - Sanjib Mridha : Date - 26-MAY-2021*/
	public String generateRandomNumber (int length) {
		DateTimeFormatter dtTmFor = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter tmFor = DateTimeFormatter.ofPattern("HHmmssSSS");
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime time = LocalDateTime.now();
		String randNum = System.nanoTime() + dtTmFor.format(dateTime);
		String randTime = tmFor.format(time);

		if (randNum.length() > length) {
			randNum = randNum.substring(0, length);
			randNum = String.valueOf(Long.parseLong(randNum) + Long.parseLong(randTime));
			randNum = randNum.substring(0, length);
		}
		else if (randNum.length() < length) {
			do
				randNum = randNum + randTime;
			while (randNum.length() < length);
			if (randNum.length() > length)
				randNum = randNum.substring(0, length);
		}		
		return randNum;
	}

	public HashMap<String, String> toHashMap (String input, String nodeSeparator, String keyValueSeparator) {
		HashMap<String, String> converted_Data = new HashMap<String, String>();
		String[] nodes = input.split(nodeSeparator);
		for (int i = 0; i < nodes.length; i++) {
			String key = nodes[i];
			String value = "";
			if(nodes[i].contains(keyValueSeparator)) {
				key = nodes[i].split(keyValueSeparator)[0].trim();
				value = nodes[i].split(keyValueSeparator)[1].trim();
			}
			converted_Data.put(key, value);
		}
		return converted_Data;
	}
	
	public String readFile (String path) {
		String data = "";
		try {
			data = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		/*
		 * try { FileReader fileReader = new FileReader(path); int i;
		 * while((i=fileReader.read())!=-1) data = data + (char)i; fileReader.close();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return data;
	}
	
	public String readTextFile(String filePath) {
		String data = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			data = sb.toString().replace("\r\n", ",");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public int getRandomNumber(int min, int max) {
		int value =  min + (int)(Math.random() * ((max - min) + 1));
		if(value == 0)
			value = 1;
		return value;
	}
	
	public float getRandomDecimalNumber(int min, int max) {
		float value =  min + (int)(Math.random() * ((max - min) + 1));
		if(value == 0)
			value = 1;
		return value;
	}
	
}
