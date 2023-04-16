package functionalLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebOperation {

	public List<HashMap<String, String>> readWebTable(WebElement headerTable, WebElement dataTable) {
		List<HashMap<String, String>> data_WebTable = new ArrayList<HashMap<String, String>>();
		List<WebElement> headerFields = null;
		int attempt = 0;
		while(attempt < 5) {
			try {
				headerFields = headerTable.findElements(By.xpath("./thead/tr/th"));
				break;
			} catch (Exception exp) {
				exp.printStackTrace();
			}
			attempt++;
		}
		List<WebElement> dataRows = dataTable.findElements(By.xpath("./tbody/tr"));
		for(WebElement row : dataRows) {
			HashMap<String, String> dataRow = new HashMap<String, String>();
			List<WebElement> dataFields = row.findElements(By.xpath("./td"));
			if (headerFields.size() == dataFields.size()) {
				for (int i = 0; i < headerFields.size(); i++) {
					dataRow.put(headerFields.get(i).getAttribute("innerText").replaceFirst(" ", ""), dataFields.get(i).getAttribute("innerText").replaceFirst(" ", ""));
				}
				data_WebTable.add(dataRow);
			}
		}
		return data_WebTable;
	}
}
