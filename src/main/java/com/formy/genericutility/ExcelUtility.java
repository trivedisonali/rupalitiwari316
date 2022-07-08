package com.formy.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * this class is  used to fetch the data from excel sheet
 * @author SONALI TIWARI
 *
 */
public class ExcelUtility {

	/**
	 * This Method is used to Open the Workbook
	 */
	static Workbook wb ;
	public static void openWorkbook(String path) throws EncryptedDocumentException, IOException {
		try {
			FileInputStream fis = new FileInputStream(path);
			wb=WorkbookFactory.create(fis);
			System.out.println("Workbook Sheet Opened Successfully");
		} catch (EncryptedDocumentException e) {
			System.out.println("Please Ensure Excel Path");
		} catch (FileNotFoundException e) {
			System.out.println("Please  Make sure Excel Path Properly");
		}

	}
	/**
	 * This Method is used to Read or Fetch the data from the Workbook Sheet 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */

	public static String readData(String sheetName,int rowNumber,int cellNumber) {

		String cellData = null;
		try {
			cellData = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
			System.out.println("Data Fetched Successfully");
		} catch (Exception e) {
			System.out.println("Please Specify Workbook SheetName or RowNumber or CellNumber ");
		}
		return cellData;
	}


	/**
	 * This Method is used to Fetch the multiple data from the excelSheet
	 * @param sheetName
	 * @return
	 */
	public static Object[][] readMultipleData(String sheetName) {
		Sheet sh = wb.getSheet(sheetName);
		Object[][] TwoDimesionalArray= new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for (int i = 0; i < sh.getLastRowNum();i++) {
			for (int j = 0; j < sh.getRow(0).getLastCellNum(); j++) {
				TwoDimesionalArray[i][j]=sh.getRow(i+1).getCell(j).toString();
			}
		}
		return TwoDimesionalArray;
	}
	/**
	 * This Method is to Write the Data in to the Cell 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellnumber
	 * @param Value
	 * @param path
	 * @throws IOException 
	 */
	public static void writeData(String sheetName,int rowNumber,int columnNumber,String Value,String path) throws IOException {
		try {
			wb.getSheet(sheetName).createRow(rowNumber).createCell(columnNumber).setCellValue(Value);
			System.out.println("Data Written Successfully into the Cell");
		} catch (NullPointerException e) {
			System.out.println("Please Make Sure Sheet Name");
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("Please Make Sure FileOutputStream Excel Path");
		}
		wb.write(fos);			
	}
	/**
	 * This Method is used to Close the Workbook 
	 * @throws IOException
	 */
	public static void closeWorkbook() throws IOException {
		try {
			wb.close();
			System.out.println("Workbook Sheet Closed");
		} catch (NullPointerException e) {
			System.out.println("Workbook Sheet Not Closed");
		}
	}
	
}

