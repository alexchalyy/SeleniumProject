

import org.openqa.selenium.WebDriver;

public class Extra_Exercise_Four_Class extends Common_Methods_Class {
	
	private String url, search_xpath, title, search_button, user_rating_sort_button, beginning_xpath, end_xpath;
	private int start = 1, end = 6;
	
	Extra_Exercise_Four_Class()	{
	//	Initialized necessary private global variables.
		
		url = "http://www.imdb.com/search/title";
		search_xpath = "//*[@id=\"main\"]/div[1]/div[2]/input";
		title = "office";
		search_button = "//*[@id=\"main\"]/p[3]/button";
		user_rating_sort_button = "//*[@id=\"main\"]/div/div/div[2]/a[3]";
		beginning_xpath = "//*[@id=\"main\"]/div/div/div[3]/div";
		end_xpath = "/div[3]/h3/a";
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	public void Execute()	{
	//	This program fills out search form on imbd.com, searches for office, sorts by rating, and outputs top 5 results.
	//	Change the folder path in the SetFireFox method in Common Methods class if importing project to another pc.
	//
	//	Written by Alex Chalyy on 2/29/2016.
			
		WebDriver driver = SetFireFox();
		
		driver.get(url);	// Navigates to given page.
		FillTextBox(driver, search_xpath, title);
		Click_Link(search_button, driver);
		Click_Link(user_rating_sort_button, driver);
		System.out.println("The results are:");
		Print_Result_Titles(driver, beginning_xpath, end_xpath, start, end);
		EndOfProgram();	
	}
}
