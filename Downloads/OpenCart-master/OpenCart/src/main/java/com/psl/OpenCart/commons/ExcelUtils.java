package com.psl.OpenCart.commons;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.psl.OpenCart.commons.LoggersExample;

import java.util.regex.*;


public class ExcelUtils {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath , String sheetName) 
	{
		try
		{
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static String readFirstname() throws Exception
	{
		
		String s1 = sheet.getRow(1).getCell(0).getStringCellValue();
		LoggersExample.logger.info(s1);
		return s1;
		
	}
	
	public static String readLastname() throws Exception
	{
		
		String s1 = sheet.getRow(1).getCell(1).getStringCellValue();
		LoggersExample.logger.info(s1);
		return s1;
		
	}
	
	public static String readEmail() throws Exception
	{
		
		String s1 = sheet.getRow(1).getCell(2).getStringCellValue();
		LoggersExample.logger.info(s1);
		return s1;
		
	}
	
	public static String readTelephone() throws Exception
	{
	DataFormatter formatter = new DataFormatter();
	String s1 = formatter.formatCellValue(sheet.getRow(1).getCell(3));
	//Double s1 = sheet.getRow(1).getCell(3).getNumericCellValue();
	//String s= Double.toString(s1);
	//String s1 = sheet.getRow(1).getCell(3).getStringCellValue();
	LoggersExample.logger.info(s1);
	return s1;
	}
	
	public static String readPass() throws Exception
	{
		
		String s1 = sheet.getRow(1).getCell(4).getStringCellValue();
		LoggersExample.logger.info(s1);
		return s1;
	}
	
	public static String readConfirmPass() throws Exception
	{
		
		String s1 = sheet.getRow(1).getCell(5).getStringCellValue();
		LoggersExample.logger.info(s1);
		return s1;
	}
	
	

}
