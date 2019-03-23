

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Extra_Exercise_Five_Class extends Common_Methods_Class {
	
	private ArrayList<String> path = new ArrayList<String>();
	private String paragraph_xpath, url, Random_Article_Link_Xpath;
	
	Extra_Exercise_Five_Class()	{
	//	Constructor predefines certain values necessary for method executions.	
		
		paragraph_xpath = "//*[@id=\"mw-content-text\"]/div";
		url = "https://en.wikipedia.org/wiki/Main_Page";
		Random_Article_Link_Xpath = "//*[@id=\"n-randompage\"]/a";
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	public void Execute()	{
	//	This program automates a system in which you navigate to a random wikipedia article and follow your first link until you either arrive at the wikipedia page for science or encounter a loop.
	//	The result of this navigation prints a summary of the titles of all pages visited as well as a passing result for eventually reaching the science article or a failing result for encountering an unbreakable loop.
	//	Change the folder path in SetChrome method in Common Methods class if importing project to another pc.
	//
	//	Written by Alex Chalyy on 3/3/2016.
			
		WebDriver driver = SetChrome();
		
		driver.get(url);
		Click_Link(Random_Article_Link_Xpath, driver);
		Science_Crawl(driver);
		EndOfProgram();
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	private void Science_Crawl(WebDriver Driver)	{
	//	Clicks on the first link of the main text of a wikipedia article until either science web page is reached or an infinite loop is reached. Prints out the titles of all opened articles.
	//	Received WebDriver object containing web page information.
		
		String title = Page_Title("//*[@id=\"firstHeading\"]", Driver);
		
		System.out.println(title);
		path.add(title);
			
		if (title.equals("Science"))	{		
			return;
		}	else if (Loop(path)) {
					System.out.println("Unbreakable loop encountered.");
					return;
		} else {
					String correct_link = Correct_XPath(Driver, 1, 1);
					Click_Link(correct_link, Driver);	// Clicks on Random Article link
					Science_Crawl(Driver);
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	private boolean LinkInParenthesis(WebDriver Driver, String Link_XPath, String Paragraph_XPath)	{
	//	Returns true if link is within parenthesis. Receives Web Driver object containing the web page information and string holding xpath of the link.
		
		String paragraph = Page_Title(Paragraph_XPath, Driver), link = Page_Title(Link_XPath, Driver);
		int index = paragraph.indexOf(link), OpenParenthesis = paragraph.indexOf("("), CloseParenthesis = paragraph.indexOf(")"), Starting_Point = OpenParenthesis + 1, End_Point = CloseParenthesis + 1; 
		boolean return_value = false;
		
		while (OpenParenthesis != -1)	{
			if (OpenParenthesis != -1)	{
				if ((OpenParenthesis < index) && (CloseParenthesis > index))	{
					return true;
				}
				else return_value = false;
			} else return_value = false; 
			Starting_Point = OpenParenthesis + 1;
			End_Point = CloseParenthesis + 1;
			OpenParenthesis = paragraph.indexOf("(", Starting_Point);
			CloseParenthesis = paragraph.indexOf(")", End_Point);
		}
		return return_value;
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	private String Correct_XPath(WebDriver Driver, int Paragraph_Index, int Link_Index)	{
	//	Returns the xpath of the correct link to click.
	//	Receives the web driver object containing the web page information, integer variable for paragraph index, and integer variable for link index.
	
		String Paragraph_XPath = paragraph_xpath + "/p[" + Integer.toString(Paragraph_Index) + "]";
		String Link_XPath = Paragraph_XPath + "/a[" + Integer.toString(Link_Index) + "]", PageTitle = Page_Title("//*[@id=\"firstHeading\"]", Driver);
		
		if (ParagraphHasLinks(Driver, Paragraph_Index))	{
			if (LinkInParenthesis(Driver, Link_XPath, Paragraph_XPath) || RedLink(Driver, Link_XPath))	{
				if (PageTitle.equals("Diving at the 1924 Summer Olympics – Men's 3 metre springboard"))	{
					return ("//*[@id=\"mw-content-text\"]/p[1]/a[1]");
				}
				Link_Index++;
				if (LinkExists(Driver, Link_Index, Paragraph_XPath))	{
					return Correct_XPath(Driver, Paragraph_Index, Link_Index);
				}
				else {	
							Link_Index = 1;
							Paragraph_Index++;
							return Correct_XPath(Driver, Paragraph_Index, Link_Index);
				}
			}	else {
						return Link_XPath;
			}
			
		}
		else {  	
				Paragraph_Index++;
				Link_Index = 1;
				return Correct_XPath(Driver, Paragraph_Index, Link_Index);
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	
	private boolean RedLink(WebDriver driver, String Link_XPath)	{
	//	Returns true if the link of the webpage link is red, otherwise false.
	//	Receives the web driver object containing the web page information and the string representation of the link xpath.
		
		WebElement Link = driver.findElement(By.xpath(Link_XPath));
		String href = Link.getAttribute("href");
		
		if (href.contains("redlink=1"))	{
			return true;
		}
		else return false;
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	
	private boolean LinkExists(WebDriver driver, int index, String Paragraph_XPath)	{
	//	Returns true if link exists, otherwise returns false.
	//	Receives Web Driver object containing the web page information, string representing the paragraph xpath, and index of the link.
		
		String ParagraphLinkXPath = Paragraph_XPath + "/a[" + Integer.toString(index) + "]";
		List<WebElement> data = driver.findElements(By.xpath(ParagraphLinkXPath));	
		int length = data.size();
		
		if (length == 0)	{
			return false;
		}
		return true;
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	private boolean ParagraphHasLinks(WebDriver driver, int index)	{
	//	Returns true if paragraph has links to click.
	//	Receives WebDriver object that has web page information and integer number of paragraph.
		
		String ParagraphLinkXPath = paragraph_xpath + "/p[" + Integer.toString(index) + "]/a[1]";	
		List<WebElement> data = driver.findElements(By.xpath(ParagraphLinkXPath));			
		int length = data.size();

		if (length == 0)	{
			return false;
		}	else return true;
	}
}
