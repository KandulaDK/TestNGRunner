package functionalLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private static Logger objLgr = LogManager.getLogger(ExcelReader.class.getName());
	private static XSSFWorkbook workbook;
	
	
	public Object[][] readInputDatafromExcel(String filePath, String sheetName) throws IOException {
		Object[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			data = new Object[worksheet.getLastRowNum()][1];
			
			Row headerRow = worksheet.getRow(0);
			for (int i = worksheet.getFirstRowNum() + 1; i < worksheet.getLastRowNum() + 1; i++) {
				HashMap<String, String> dataHash = new HashMap<String, String>();
				Row row = worksheet.getRow(i);
				
				for (int j = headerRow.getFirstCellNum(); j < headerRow.getLastCellNum(); j++) {
					dataHash.put(headerRow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
				}
				data[i - 1][0] = dataHash;
			}
			objLgr.info("Test Data identification completed for sheet name - " + sheetName);
		} catch (FileNotFoundException fileExp) {
			objLgr.error("File not found");
		}
		return data;
	}

	public void writeDatatoExcel(String filePath, String sheetName, String dataInput) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			for (int i = worksheet.getFirstRowNum() + 1; i < worksheet.getLastRowNum() + 1; i++) {
				Row row = worksheet.getRow(i);
				for (int j = 0; j < dataInput.split(",").length; j++) {
					String value = dataInput.split(",")[j];
					if(row.getCell(0).getStringCellValue().equalsIgnoreCase(value.split("-")[0]) && row.getCell(1).getStringCellValue().equalsIgnoreCase(value.split("-")[1]) && row.getCell(2).getStringCellValue().equalsIgnoreCase(value.split("-")[2]))
						row.getCell(row.getLastCellNum() - 1).setCellValue((String)"Yes");
				}
			}
			
			FileOutputStream out = new FileOutputStream(new File(filePath));
	        workbook.write(out);
	        out.close();
		} catch (FileNotFoundException fileExp) {
			objLgr.error("File not found");
		}
	}
	
	public void markExecutionBasedOnModule(String filePath, String sheetName, String dataInput, String dc) throws IOException {
		int dcColumnIndex = 0;
		int changeDCIndex = 0;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			Row firstRow = worksheet.getRow(0);
			for(int r = firstRow.getFirstCellNum(); r <firstRow.getLastCellNum(); r++) {
				if(firstRow.getCell(r).getStringCellValue().equalsIgnoreCase("DC")) {
					changeDCIndex = r;
				}
				if(!dc.equalsIgnoreCase("All")) {
					if(firstRow.getCell(r).getStringCellValue().equalsIgnoreCase(dc)) {
						dcColumnIndex = r;
						break;
					}	
				}
			}
			
			for (int i = worksheet.getFirstRowNum() + 1; i < worksheet.getLastRowNum() + 1; i++) {
				Row row = worksheet.getRow(i);
				if(!dataInput.equalsIgnoreCase("All")) {
					if(row.getCell(0).getStringCellValue().equalsIgnoreCase(dataInput)) {
						if(dc != null && !dc.equals("") && !dc.toLowerCase().contains("all")) {
							if(row.getCell(dcColumnIndex).getStringCellValue().equalsIgnoreCase("Y")) {
								row.getCell(changeDCIndex).setCellValue((String)dc);
								row.getCell(row.getLastCellNum() - 1).setCellValue((String)"Yes");
							}else
								row.getCell(row.getLastCellNum() - 1).setCellValue((String)"No");
						}else
							row.getCell(row.getLastCellNum() - 1).setCellValue((String)"Yes");
					}else
						row.getCell(row.getLastCellNum() - 1).setCellValue((String)"No");
				}else {
					if(dc != null && !dc.equals("") && !dc.toLowerCase().contains("all")) {
						if(row.getCell(dcColumnIndex).getStringCellValue().equalsIgnoreCase("Y")) {
							row.getCell(changeDCIndex).setCellValue((String)dc);
							row.getCell(row.getLastCellNum() - 1).setCellValue((String)"Yes");
						}else
							row.getCell(row.getLastCellNum() - 1).setCellValue((String)"No");
					}else
						row.getCell(row.getLastCellNum() - 1).setCellValue((String)"Yes");
				}
			}	
			FileOutputStream out = new FileOutputStream(new File(filePath));
	        workbook.write(out);
	        out.close();
		} catch (FileNotFoundException fileExp) {
			objLgr.error("File not found");
		}
	}
}
