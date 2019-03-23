

import org.openqa.selenium.WebDriver;

public class Exercise_One_Class extends Common_Methods_Class {

	public void Execute()	{
		//	Method for the first Java Eclipse IDE Selenium exercise.
		//  This method navigates to wikipedia chrome and presses random article.
		//	Change the folder path in the setProperty line if importing project to another pc.
		//
		//	Written by Alex Chalyy on 1/18/2016.
		
		WebDriver driver = SetChrome();
		String Random_Article_Link_Xpath = "//*[@id=\"n-randompage\"]/a", title_Xpath = "//*[@id=\"firstHeading\"]", url = "https://en.wikipedia.org/wiki/Main_Page";
		
		driver.get(url);	// Navigates to given page.
		Check_Chrome_Web_Navigation(url, driver);	// Checks whether navigation to the right page has been performed. 
		Click_Link(Random_Article_Link_Xpath, driver);	// Clicks on Random Article link
		System.out.println(Page_Title(title_Xpath, driver) + " has been opened");	// Prints the title of the opened page
		EndOfProgram();
	}
}
