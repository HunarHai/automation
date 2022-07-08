package com.uiAutomation.testScripts.samplePage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsExcelReader {
	public static void main(String[] args) throws Exception{
		File f = new File("main/resources/configFile/testData_xlsx.xlsx");
		//	latestPOMFramework/src/main/resources/configFile/testData.xlsx
		System.out.println("Excel read...");
		
		FileInputStream fis = new FileInputStream(f);
		
		HSSFWorkbook book = new HSSFWorkbook(fis);
		
		HSSFSheet sh = book.getSheet("Login");
	
		HSSFRow row = sh.getRow(1);
		HSSFCell cell = row.getCell(1);
		String cellValue = cell.getStringCellValue();

		System.out.println(cellValue);
		System.out.println("");
		System.out.println("======================");
	
		HSSFCell cell1 = row.getCell(3);
		int cellValue1 = (int) cell1.getNumericCellValue();
		
		HSSFRow row1 = sh.getRow(2);
		HSSFCell cell2 = row1.getCell(3);
		int cellValue2 = (int) cell2.getNumericCellValue();
		
		System.out.println(cellValue1+"	"+cellValue2+" 	"+(cellValue1+cellValue2));
		
		/*
		 * FileInputStream fis = new FileInputStream(f);
		 * 
		 * HSSFWorkbook book = new HSSFWorkbook(fis);
		 * 
		 * HSSFSheet sh = book.getSheet("Mercury");
		 * 
		 * HSSFRow row = sh.getRow(1); HSSFCell cell = row.getCell(1); String cellValue
		 * = cell.getStringCellValue();
		 * 
		 * System.out.println(cellValue); System.out.println("");
		 * System.out.println("======================");
		 * 
		 * HSSFCell cell1 = row.getCell(3); int cellValue1 = (int)
		 * cell1.getNumericCellValue();
		 * 
		 * HSSFRow row1 = sh.getRow(2); HSSFCell cell2 = row1.getCell(3); int cellValue2
		 * = (int) cell2.getNumericCellValue();
		 * 
		 * System.out.println(cellValue1+"	"+cellValue2+" 	"+(cellValue1+cellValue2));
		 */
		
		
		book.close();
	}

}
