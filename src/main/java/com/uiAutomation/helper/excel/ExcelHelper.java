package com.uiAutomation.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class ExcelHelper {
	
	private static final Logger log = LoggerHelper.getLogger(ExcelHelper.class);
	
	/**
	 * 
	 * @param excelLocation
	 * @param sheetName
	 * @return
	 */
	public Object[][] getExcelData(String excelLocation, String sheetName){
		
		try{
			Object dataSets[][] = null;
			File file = new File(excelLocation);
			FileInputStream fis = new FileInputStream(file);
			
			//Create Workbook instance
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//Get Sheet Name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//Get number of active rows in sheet
			int totalRows = sheet.getLastRowNum();
			
			//Get number of active cells/columns in row
			int totalColumns = sheet.getRow(0).getLastCellNum();
			
			dataSets = new Object[totalRows][totalColumns];
			
			//Iterate through each row one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i=0;
			while(rowIterator.hasNext()){
				// For Every row, we need to iterate over the columns
				Row row = rowIterator.next();
				
				//Iterate through each cell one by one
				Iterator<Cell> cellIterator = row.cellIterator();
				int j=0;
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					
					switch(cell.getCellTypeEnum()){
					case STRING: 
						dataSets[i][j] = cell.getStringCellValue();
						break;
					
					case NUMERIC:
						dataSets[i][j] = cell.getNumericCellValue();
						break;
					
					case BOOLEAN:
						dataSets[i][j] = cell.getBooleanCellValue();
						break;
						
					case FORMULA:
						dataSets[i][j] = cell.getCellFormula();
						break;
						
					default:
						System.out.println("No matching enum data type found.");
						break;						
					}
				//	j++;
				}
			//	i++;
			}
			return dataSets;

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param excelLocation
	 * @param sheetName
	 * @param testCaseName
	 * @param testStatus
	 */
	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus){
		try{
			File file = new File(excelLocation);
			FileInputStream fis = new FileInputStream(file);
			
			//Create Workbook instance
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//Get Sheet Name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//Get number of active rows in sheet
			int totalRows = sheet.getLastRowNum()+1;
			for(int i=1; i<totalRows; i++){
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if(ce.contains(testCaseName)){
					r.createCell(1).setCellValue(testStatus);
					fis.close();
					log.info("Results updated...");
					FileOutputStream out= new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		ExcelHelper ex = new ExcelHelper();
		String excelLocation = ResourceHelper.getResourcePath("/src/main/resources/configFile/testData.xlsx");
/*		Object[][] data = ex.getExcelData(excelLocation, "Login");
		System.out.println(data.length);
		for(int i = 0; i <data.length; i++){
			for(int j = 0; j < data.length; j++){
				System.out.println(data[i][j]);
			}
		}
*/
		ex.updateResult(excelLocation, "TestScripts", "Login", "FAIL");
		ex.updateResult(excelLocation, "TestScripts", "Registration", "PASS");
		ex.updateResult(excelLocation, "TestScripts", "Add To Cart", "PASS");
		ex.updateResult(excelLocation, "TestScripts", "Logout", "PASS");
	}

}
