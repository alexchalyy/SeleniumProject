

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Exercise_Four_Class extends Common_Methods_Class {
	
	private String url = "https://en.wikipedia.org/wiki/Selenium", start_xpath = "/html/body/div[4]/div[3]/div[4]/div/p[1]/b[1]", url2 = "https://pastebin.com/", paragraph_xpath = "//*[@id=\"paste_code\"]";
	private int x_2 = -30, y_2 = -10, x_1 = 300, y_1 = 80, x_3 = -500, y_3 = -100, x_4 = 500, y_4 = 100;
	
	public void Execute()	{
	//	Method for fourth Java Eclipse IDE Selenium exercise.
	//	This Method navigates to wikipedia, Highlights and "Copies" the first paragraph of text using the Actions class, Navigates to pastebin.com and paste the copied text there, Verifies that the copied and pasted text match.
	//	Make sure Firefox is default browser before running the script.
	//
	//	Written by Alex Chalyy on 2/27/2016.
		
		WebDriver Driver = SetFireFox();
		//WebDriver Driver = SetChrome();
		Actions builder = new Actions(Driver);
		
		Driver.get(url);	// Navigates to given page.
		Driver.manage().window().maximize();
		DonationBanner(Driver);
		CopyParagraph(builder, Driver, start_xpath, x_1, y_1, x_2, y_2);	 // copies the paragraph
		String copied_text = GetClipboardContents();
		System.out.println("copied text is " + copied_text);
		Driver.get(url2); 	// Navigates to given page.
		Paste(builder, Driver);												 // pastes the paragraph
		CopyParagraph(builder, Driver, paragraph_xpath, x_3, y_3, x_4, y_4); // copies the pasted paragraph
		String pasted_text = GetClipboardContents();
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
			Click_Link("/html/body/div[1]/div/div[1]/div[1]/span", driver);
			System.out.println("Donation banner closed.");
		}
		catch (Exception e)	{
			System.out.println("Donation banner not present.");
			x_2 = -700; 
			y_2 = -48; 
			y_1 = 40; 
			start_xpath = "/html/body/div[3]/div[3]/div[4]/div/p[1]";
		}
	}
}
