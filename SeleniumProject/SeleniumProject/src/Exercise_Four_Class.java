

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Exercise_Four_Class extends Common_Methods_Class {
	
	private String url = "https://en.wikipedia.org/wiki/Selenium_(software)", start_xpath = "//*[@id=\"mw-content-text\"]/div/p[2]", url2 = "https://pastebin.com/", paragraph_xpath = "//*[@id=\"paste_code\"]";
	
	public void Execute()	{
	//	Method for fourth Java Eclipse IDE Selenium exercise.
	//	This Method navigates to wikipedia, Highlights and "Copies" the first paragraph of text using the Actions class, Navigates to pastebin.com and paste the copied text there, Verifies that the copied and pasted text match.
	//	Make sure Chrome is default browser before running the script.
	//
	//	Written by Alex Chalyy on 2/27/2016.
			
			WebDriver Driver = SetChrome();
			Actions builder = new Actions(Driver);
			
			Driver.get(url);	// Navigates to given page.
			Driver.manage().window().maximize();
			DonationBanner(Driver);
			CopyParagraph(Driver, start_xpath);	 // copies the paragraph
			String copied_text = GetClipboardContents();
			System.out.println("copied text is " + copied_text);
			Driver.get(url2); 	// Navigates to given page.
			Wait(6);
			Paste(builder, Driver);												 // pastes the paragraph
			String pasted_text = Driver.findElement(By.xpath(paragraph_xpath)).getAttribute("value");
		    CompareTwoStrings(copied_text, pasted_text);						 // verifies if copied and pasted paragraphs are the same 
		    EndOfProgram(); 
	}	
	
	//---------------------------------------------------------------------------------------
	
	void CompareTwoStrings(String one, String two)	{
	// Compares the contents of two strings and prints on prompt screen if they are equal or not.
		
		if (one.equals(two))	{
			System.out.println("Copied and pasted texts are the same.");
		}
		else System.out.println("Copied and pasted texts are not the same.");
	} 
	
	//----------------------------------------------------------------------------------------
	
	void DonationBanner(WebDriver driver)	{
	//	If donation banner is present, closes it.
				
		try	{
			Click_Link("//*[@id=\"frb-form\"]/div[1]/button[1]", driver);
			System.out.println("Donation banner closed.");
		}
		catch (Exception e)	{
			System.out.println("Donation banner not present.");
		}
	}
}
