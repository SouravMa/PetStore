package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
    public ExtentTest test;
    String repName;
    
    public void onStart(ITestContext testContext){
        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName= "Test-Report-" + timeStamp + ".html";
        sparkReporter= new ExtentSparkReporter("./reports/" + repName);

        sparkReporter.config().setDocumentTitle("RestAssuredAutomation Report");
        sparkReporter.config().setReportName("Pet Stores Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent= new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Pet Stores Users API");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user", "Sourav");

    }
    
    public void onTestSuccess(ITestResult result){
        test= extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, result.getName()+" test passed");
    }
    
    public void onTestFailure(ITestResult result){
        test= extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName()+" Test failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }
    
    public void onTestSkipped(ITestResult result){
        test= extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" Test skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }
    
    public void onFinish(ITestContext testContext){
        extent.flush();
    }
	
}
