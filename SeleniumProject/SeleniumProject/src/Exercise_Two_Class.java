

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Exercise_Two_Class extends Common_Methods_Class {

	private String location = new String(), text = new String();
	
	//---------------------------------------------------------------------------------
	
	public Exercise_Two_Class()	{
	//	Constructor initializes necessary global variables.
		
		//location = "C:\\Users\\alex\\Desktop\\java\\selenium\\selenium_project\\selenium_project\\src\\selenium_project\\exercise_2.htm";
		location = "C:\\Users\\alexc\\SeleniumProject-master\\SeleniumProject\\SeleniumProject\\src\\exercise_2.htm";
	}
	
	//---------------------------------------------------------------------------------
	
	public void Execute()	{
	//	Method for the second Java Eclipse IDE Selenium exercise.
	//  This method opens an html file at given location and selects elements with word "Item" at string position 9 and prints them on prompt.
	//	Change the folder path to correct chrome driver path in SetChrome() method in common methods class.
	//
	//	Written by Alex Chalyy on 2/11/2016.		
	//
	//  Please create html file with the following contents at any location on your computer and substitute that location in the location string above.
	//
	//  <!DOCTYPE html>
	//  <html>
	//  <ul id="menu">
	//  	<li>This is Item 1</li>
	//  	<li>This is Record 2</li>
	//  	<li>This is Item 3</li>
	//  	<li>  This    is Item 4</li>
	//  	<li>This is Record  5</li>
	//  </ul>
	//  </html> 	
		
		WebDriver driver = SetChrome();
		List<WebElement> d = new ArrayList<WebElement>();
		
	    driver.get(location);	// Navigates to given page.
	    List<WebElement> data = driver.findElements(By.xpath("/html/body/ul/li"));  // Stores all elements of html table in list
	    for (WebElement datum : data)	{	// goes through entire list of html table elements
	    	text = datum.getText();			// gets inner text of the html table element
	    	text = text.trim();				// removes all additional spaces from the inner text
	    	text = text.replaceAll(" ", "");
	    	if (text.contains("Item"))	{	// creates a new list of correct elements from the html table
	    		d.add(datum);
	    	}
	    }
	    for (WebElement datum : d)	{	// prints out inner texts of all correct html elements from new list
	    	text = datum.getText();
	    	System.out.println(text);
	    }
	    EndOfProgram();
	}
}
