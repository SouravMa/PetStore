package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	 @DataProvider(name= "Data")
	    public String[][] getAllData() throws IOException {
	        String path= System.getProperty("user.dir")+"//testData//Userdata.xlsx";
	        XLUtility xlUtil= new XLUtility(path);
	        int totalRows= xlUtil.getRowCount("Sheet1");
	        int totalCols= xlUtil.getCellCount("Sheet1", 1);
	        String apiData[][]=  new String[totalRows][totalCols];
	        for(int i=1; i<=totalRows; i++){
	            for(int j=0; j<totalCols; j++){
	            	apiData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
	            }
	        }
	        return  apiData;
	    }
	 
	 @DataProvider(name= "UserNames")
	    public String[] getUserNames() throws IOException {
	        String path= System.getProperty("user.dir")+"//testData//Userdata.xlsx";
	        XLUtility xlUtil= new XLUtility(path);
	        int totalRows= xlUtil.getRowCount("Sheet1");
	        String apiData[]=  new String[totalRows];
	        for(int i=1; i<=totalRows; i++){	          
	            apiData[i-1]=xlUtil.getCellData("Sheet1", i, 1);
	          
	        }
	        return apiData;
	    }
	 
	 @DataProvider(name= "StoreData")
		 public String[][] getAllStoreData() throws IOException{
			 String path= System.getProperty("user.dir")+"//testData//Storedata.xlsx";
			 XLUtility xlUtil= new XLUtility(path);
			 int totalRows= xlUtil.getRowCount("Sheet1");
			 int totalCols= xlUtil.getCellCount("Sheet1", 1);
			 String apiData[][]= new String[totalRows][totalCols];
			 for(int i=0; i<= totalRows; i++) {
				 for(int j=0; j<totalCols; j++) {
					 apiData[i-1][j]= xlUtil.getCellData("Sheet1", i, j);
				 }
			 }
			 return apiData;
		 }
	 
	 @DataProvider(name= "OrderId")
		 public String[] getIds() throws IOException {
			 String path= System.getProperty("user.dir")+"//testData//Storedata.xlsx";
			 XLUtility xlUtil= new XLUtility(path);
			 int totalRows= xlUtil.getRowCount("Sheet1");
			 String apiData[]=  new String[totalRows];
			 for(int i=1; i<=totalRows; i++){	          
		            apiData[i-1]=xlUtil.getCellData("Sheet1", i, 0);
		          
		        }
		     return apiData;
		 }

}
