

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.*;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.util.List;

public class Common_Methods_Class {
// 	This class contains normally used methods for Selenium Eclipse Java exercises.
//
//	Written by Alex Chalyy on 1/18/2016.
	
	WebDriver SetChrome()	{
	//  Returns set up chrome webdriver.	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\alex\\eclipse-workspace\\SeleniumProject\\web_drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		return driver;
	}
	
	//-----------------------------------------------------------------------------
	
	WebDriver SetFireFox()	{
	//	Returns set up FireFox WebDriver.
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\alex\\eclipse-workspace\\SeleniumProject\\web_drivers\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		WebDriver webDriver = new FirefoxDriver(options);
		
		return webDriver;
	}
	
	//-----------------------------------------------------------------------------
	
	WebDriver SetIE()	{
	//	Returns set up IE Webdriver.
		
		System.setProperty("webdriver.ie.driver", "C:\\Users\\alex\\eclipse-workspace\\SeleniumProject\\web_drivers\\IEDriverServer.exe");
		WebDriver Driver = new InternetExplorerDriver();
		
		return Driver;
	}
	
	//-----------------------------------------------------------------------------
	
	void Check_Chrome_Web_Navigation(String Web_Site_Name, WebDriver Driver)	{
	//	This void method checks whether correct url has been opened.
	//	The method receives browser WebDriver object and compares the page opened in it to received string that contains the correct webpage.
		
		String CurrentUrl = Driver.getCurrentUrl();
		
		if (CurrentUrl.equals(Web_Site_Name))	{
			System.out.println(Web_Site_Name + " has been open.\n");
		}
		else System.out.println(Web_Site_Name + " has NOT been open.\n");
	}
	
	//-----------------------------------------------------------------------------
	
	void Click_Link (String searchButtonXpath, WebDriver Driver)	{
	//	This void method clicks on the link on the given web page given by an xpath.
	//  This method receives string with xpath and the Webdriver object that contains the web page.
		
		WebElement searchButton = Driver.findElement(By.xpath(searchButtonXpath));

	    searchButton.click();
	    System.out.println("Link has been clicked.\n");
	}
	
	//-------------------------------------------------------------------------------
	
	String Page_Title(String TitleXpath, WebDriver Driver){
	//	Returns text contents of the given xpath.
	//	Accepts the xpath of the element whose inner html text must be printed in string format and WebDriver object containing webpage.
		
		WebElement Title = Driver.findElement(By.xpath(TitleXpath));
		String title = Title.getText();
	
		return title;
	}
	
	//--------------------------------------------------------------------------------
	
	int RandomInt(int Max)	{
	//	Returns random integer from 0 to Max.
		
		Random r = new Random();
		int n = r.nextInt(Max + 1);
				
		return n;
	}
	
	//--------------------------------------------------------------------------------------
	
	List<String> CopyCategoryTitles(List<WebElement> cat_1)	{
	// Copies the category names from current web page in String list.
	// Receives list of web elements representing the categories.
		
		List<String> category_list = new ArrayList<String>();
		
		for (WebElement cat : cat_1)	{
			category_list.add(cat.getText());
		}
		return category_list;
	}
	
	//--------------------------------------------------------------------------------------
	
	void PrintString (List<String> list)	{
	//	Outputs all elements of string array.
	//  Receives String array.
		
		for (String counter : list)	{
			System.out.println(counter);
		}
	}
	
	//--------------------------------------------------------------------------------------
	
	void CopyParagraph(Actions build, WebDriver driver, String xpath, int x1, int y1, int x2, int y2)	{
	//	Selects and copies paragraph from a web page using a web element as a reference point.
	//	Receives  actions object, web driver, web element xpath as well as lower right hand corner and upper left hand corner x and y coordinates.
		
		WebElement start = driver.findElement(By.xpath(xpath));
		
		build.dragAndDropBy(start, x1, y1).keyDown(Keys.CONTROL).dragAndDropBy(start, x2, y2).sendKeys("c").keyUp(Keys.CONTROL);
		Action selectParagraph = build.build();
		selectParagraph.perform();
	}
	
	//---------------------------------------------------------------------------------------
	
	String GetClipboardContents()	{
	//	Returns the clipboard contents in string format (memory contents from previous copy action).
		
	    String result = "";
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    
	    try {
	        result = (String) clipboard.getData(DataFlavor.stringFlavor);
	    } catch (Exception ex) {
	    } 
	    return result;
	}
	
	//------------------------------------------------------------------------------------
	
    void Paste(Actions build, WebDriver driver)	{
    //	Pastes the text in memory.
   	
		build.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
		Action paste = build.build();
		paste.perform();
    }
    
    //-----------------------------------------------------------------------------------------
    
    void EndOfProgram()		{
    //	This program makes beeping sound and displays the status that the program was successfully finished.
    	
		System.out.println("\nThe program was successfully executed.");
		Toolkit.getDefaultToolkit().beep();
    }
    
    //-----------------------------------------------------------------------------------------
    
    void FillTextBox(WebDriver Driver, String xpath, String title)	{
    //	Enters text into a text box on a web page. Receives web driver object with web page information, string xpath of text box, and string to fill in the text box.
    
    	WebElement Text_Box = Driver.findElement(By.xpath(xpath));

    	Text_Box.sendKeys(title);
    }
    
	//-----------------------------------------------------------------------------------------
	
	void Print_Result_Titles(WebDriver Driver, String result_xpath_beginning, String result_xpath_end, int start, int end)	{
	//	Prints the received result titles from the search window. Receives starting xpath of result, the amount of results to print, and the WebDriver containing web page information.
	
		for (int c = start; c < end; c++)	{
			String index = Integer.toString(c);
			String xpath = result_xpath_beginning + '[' + index + ']' + result_xpath_end;
			WebElement result = Driver.findElement(By.xpath(xpath));
			//System.out.println("xpath = " + xpath);
			System.out.println(result.getText());
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	boolean Loop(ArrayList<String> Path)	{
	//	Returns true only if string list contains repetitive loop.
		
		String current = Path.get(Path.size() - 1);
		
		if (((Path.size() % 2) == 0) && (Path.size() > 1))	{
			for (int c = (Path.size() - 1); c >= 0; c--)	{
				if ((c != (Path.size() - 1)) && (current.equals(Path.get(c))))		{
					return true;
				}
			}
		}
		return false;
	}
}

