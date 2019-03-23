

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Exercise_Three_Class extends Common_Methods_Class {
	
	private Scanner reader;
	
	//------------------------------------------------------------------------------------------
	
	public void Execute()	{
	//  Method for the third Java Eclipse IDE Selenium exercise using Chrome or Firefox.
	//  This constructor opens random Wiki page, picks random category random article and outputs the compared differences between the categories of the original random page and the second one.
	//	Change the folder path in the setProperty line if importing project to another pc.
	//
	//	Written by Alex Chalyy on 2/16/2016.
			
		int status;
		boolean correct_input = false;
			
		while (!correct_input)	{
			status = PromptBrowser();
			switch(status)	{
				case 1:		Chrome();
							correct_input = true;
							break;
				case 2: 	Firefox();
							correct_input = true;
							break;
				case 3:		IE();
							correct_input = true;
							break;
				default:	System.out.println(status + " is invalid browser.");
			               	break;
			}
		}	
	}
	
	//------------------------------------------------------------------------------------------
	
	private void ClickRandomLink(String GroupXpath, WebDriver Driver)	{
	//	Clicks on a random link from the elements received in the GroupXpath string.
	//  The method received WebDriver object with web information and group xpath location string.
		
		String Random_XPath = new String();
		Random_XPath = AddRandomXPath(GroupXpath, Driver);
		
		Click_Link(Random_XPath, Driver);	// Clicks on random category link 
	}
	
	//--------------------------------------------------------------------------------------
	
	private String AddRandomXPath(String GroupXpath, WebDriver Driver)	{
	//	Modifies given XPath by adding a random constituent element of the given XPath and returns it as a string.
	//  The method received WebDriver object with web information and group xpath location string.	
		
		List<WebElement> data = Driver.findElements(By.xpath(GroupXpath));	// gets the list of all elements within XPath
		int length = data.size(), random = RandomInt(length);	// gets the number of elements within the Xpath and generates a random integer within that range
		String Random_Xpath = new String(), index = new String();
		
		if (random == 0)	{
			random = 1;
		}
		index = Integer.toString(random);
		Random_Xpath += GroupXpath;
		if (length > 1)	{ 
			Random_Xpath += '[';
			Random_Xpath += index;
			Random_Xpath += ']';
		}
		return Random_Xpath;
	}
	
	//--------------------------------------------------------------------------------------
	
	private void ComparedDifferences(List<String> c_1, List<String> c_2)	{
	// Outputs the differences between two sets of categories
	// The method received two lists of categories
			
		List<String> differences = new ArrayList<String>();
		
		System.out.println("Differences between the categories of two pages:"); 
		for (String counter : c_1)	{	// compares all elements between two string arrays and creates another string array with elements that are not shared between them
			if (!c_2.contains(counter))	{
				differences.add(counter);
			}
		}
		for (String counter : c_2)	{
			if (!c_1.contains(counter) && !differences.contains(counter))	{
				differences.add(counter);
			}
		}
		PrintString(differences);
	}
	
	//--------------------------------------------------------------------------------------
	
	private List<String> CategoriesToStrings (String Xpath, WebDriver Driver)	{
	//	Copies the titles of the Xpath elements into a string array.
	//  Receives string xpath for the list of web element group location, and webdriver object containing those elements.
		
		List<WebElement> categories = Driver.findElements(By.xpath(Xpath));
	    List<String> str = CopyCategoryTitles(categories);
	    
	    return str;
	}
	

	//--------------------------------------------------------------------------------------
	
	private void Chrome()	{
	//	Performs the functions described in calling method using chrome browser.
		
		WebDriver Driver = SetChrome();
		
		Exercise(Driver);
	}
	
	//--------------------------------------------------------------------------------------
	
	private void Firefox()	{
	//	Performs the functions described in calling method using firefox browser.	
		
		WebDriver Driver = SetFireFox();
		
		Exercise(Driver);
	}
	
	//-------------------------------------------------------------------------------------
	
	private void IE()	{
	//	Performs the functions described in calling melthod using IE browser.
		
		WebDriver Driver = SetIE();
		
		Exercise(Driver);
	}
	
	//--------------------------------------------------------------------------------------
	
	private void Exercise(WebDriver driver)	{
	//	Performs the functions described in the calling method.
	//	Receives the web driver object containing web page info.
		
		String Random_Article_Link_Xpath =  "//*[@id=\"n-randompage\"]/a", Random_Category_Xpath = "//*[@id=\"mw-normal-catlinks\"]/ul/li", url = "https://en.wikipedia.org/wiki/Main_Page", property_xpath = "//*[@id=\"mw-normal-catlinks\"]/ul/li";  
				
		driver.get(url);	// Navigates to given page.
		Click_Link(Random_Article_Link_Xpath, driver);	// Clicks on Random Article link
		List<String> cat_1 = CategoriesToStrings(Random_Category_Xpath, driver);		
		ClickRandomLink(Random_Category_Xpath, driver);	// Clicks on random link among the web links elements provided in Random_Category_Xpath				
		String R_XPath = Random_Page_XPath(driver);
		Click_Link(R_XPath, driver); // Clicks on random article within the page category
		List<String> cat_2 = CategoriesToStrings(property_xpath, driver);
		ComparedDifferences(cat_1, cat_2);	// lists the differences between the categories of current and previous page 
		EndOfProgram();
	}
	
	//--------------------------------------------------------------------------------------
	
	private String Random_Page_XPath(WebDriver Driver)	{
	//	Returns the correct random page xpath.
	//	Receives the Web Driver object containing web page information.
			
		List<WebElement> data = Driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div[1]"));
		int length = data.size();
		String Random_Xpath = new String(), R_XPath = new String();
		
		if (length == 0)	{
			Random_Xpath = AddRandomXPath("//*[@id=\"mw-pages\"]/div/ul", Driver);
			Random_Xpath += "/li";
			R_XPath = AddRandomXPath(Random_Xpath, Driver);
			R_XPath += "/a";
		}	else	{
						Random_Xpath = AddRandomXPath("//*[@id=\"mw-pages\"]/div/div/div", Driver);
						Random_Xpath += "/ul/li";
						R_XPath = AddRandomXPath(Random_Xpath, Driver);
						R_XPath += "/a";
		}
		return R_XPath;
	}
	
	//--------------------------------------------------------------------------------------
	
	private int PromptBrowser()	{
	//	Prompts the user which browser to use (chrome or firefox).
	//	Returns integer equivalent to the browser to use.
		
		reader = new Scanner(System.in);
		while (true)	{
			System.out.println("Please enter which browser you would like to use: \n");
			System.out.println("1 - Chrome");
			System.out.println("2 - Firefox");
			//System.out.println("3 - IE");	Note Internet Explorer is not supported by WebDriver.
			System.out.println("Enter a number: ");
			String status = reader.next();
			try	{
				int browser = Integer.parseInt(status);
				return browser;
			}
			catch (NumberFormatException ne)	{
				System.out.println("Input is not a number, continue\n");
			}	
		}
	}

}


