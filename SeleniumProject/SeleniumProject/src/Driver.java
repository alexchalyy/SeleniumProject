//package SeleniumProject;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
	//-------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
	// 	Main method used to run exercise code snippets for Java Eclipse Selenium exercises. Make sure firefox, chrome, and IE are installed.
	//	Uncomment the exercise object you would like to run.
	//
	//	Written by Alex Chalyy on 12/19/2017.
		
			 Exercise_One_Class Exercise_One = new Exercise_One_Class();
			 Exercise_Two_Class Exercise_Two = new Exercise_Two_Class();
			 Exercise_Three_Class Exercise_Three = new Exercise_Three_Class();
			 Exercise_Four_Class Exercise_Four = new Exercise_Four_Class();
			 Extra_Exercise_Four_Class Extra_Exercise_Four = new Extra_Exercise_Four_Class();
			 Extra_Exercise_Five_Class Extra_Exercise_Five = new Extra_Exercise_Five_Class(); 
			 String status;
			 boolean correct_input = false;
			 int choice;
			 Scanner reader = new Scanner(System.in);
				
			 while (!correct_input)	{
				 System.out.println("Choose the exercise to execute: \n");
				 System.out.println("Test Case 1: Press 1 and enter ");
				 System.out.println("Test Case 2: Press 2 and enter ");
				 System.out.println("Test Case 3: Press 3 and enter ");
				 System.out.println("Test Case 4: Press 4 and enter ");
				 System.out.println("Test Case 5: Press 5 and enter ");
				 System.out.println("Test Case 6: Press 6 and enter ");
				 status = reader.next();
		         try {
		                choice = Integer.parseInt(status);
		                switch(choice)	{
						 	case 1:		Exercise_One.Execute();
						 				correct_input = true;
						 				break;
						 	case 2: 	Exercise_Two.Execute();
										correct_input = true;
										break;
						 	case 3:		Exercise_Three.Execute();
										correct_input = true;
										break;
						 	case 4:		Exercise_Four.Execute();
										correct_input = true;
										break;
						 	case 5:		Extra_Exercise_Four.Execute();
										correct_input = true;
										break;	
						 	case 6:		Extra_Exercise_Five.Execute();
										correct_input = true;
										break;						
						 	default:	System.out.println(status + " is invalid browser.");
					               		break;
		                }
		            } catch (NumberFormatException ne) {
		                System.out.println("Input is not a number, continue\n");
		            }
			}	
	} 
}
