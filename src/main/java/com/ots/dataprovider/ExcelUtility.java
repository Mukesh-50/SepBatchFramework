package com.ots.dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	static XSSFWorkbook wb;
	
	public static Object[][] getData(String sheetName)
	{
		try 
		{
			wb=new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx"));
			
		} catch (IOException e) 
		{
			System.out.println("Could not read excel "+e.getMessage());
		}

		XSSFSheet sheet=wb.getSheet(sheetName);
		
		int rows=sheet.getPhysicalNumberOfRows();
		
		int column=sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object [][]arr=new Object[rows-1][column];
		
		for(int i=1;i<rows;i++)
		{
			
			for(int j=0;j<column;j++)
			{
				
				arr[i-1][j]=getCellData(sheetName, i, j);
				
			}
			
		}
		
		return arr;
	}
	
	public static Object[][] getData(String sheetName,String excelName)
	{
		try 
		{
			wb=new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"/TestData/"+excelName+".xlsx"));
			
		} catch (IOException e) 
		{
			System.out.println("Could not read excel "+e.getMessage());
		}

		XSSFSheet sheet=wb.getSheet(sheetName);
		
		int rows=sheet.getPhysicalNumberOfRows();
		
		int column=sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object [][]arr=new Object[rows-1][column];
		
		for(int i=1;i<rows;i++)
		{
			
			for(int j=0;j<column;j++)
			{
				
				arr[i-1][j]=getCellData(sheetName, i, j);
				
			}
			
		}
		
		return arr;
	}
	
	public static String getCellData(String sheetName,int row,int column)
	{
		XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
		
		CellType cellType=cell.getCellType();
		
		String data="";
		
		if(cellType==CellType.STRING)
		{
			data=cell.getStringCellValue();
		}
		else if(cellType==CellType.BOOLEAN)
		{
			boolean value=cell.getBooleanCellValue();
			
			data=String.valueOf(value);
		}
		else if(cellType==CellType.NUMERIC)
		{
			double value=cell.getNumericCellValue();
			
			data=String.valueOf(value);
		}
		else if(cellType==CellType.BLANK)
		{
			data="";
		}
		
		
		return data;
	}
	
}
