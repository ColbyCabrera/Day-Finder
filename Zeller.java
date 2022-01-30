/*
* Name: Colby Cabrera
* Overview: Calculates the day using Zeller's algorithm from a users input.
* Input: User uses the keyboard to enter the month, day and year.
* Output: Outputs the calculated day, and counts the number of entries.
* Variables: month, day, year, calc1, century, g, count, calcM, calc2.
* 
*/
import java.util.Scanner;
public class Zeller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int month = -1;
		int day;
		int year;
		int calc1;
		int century;
		int g;
		int count = 0;
		int year1000Fix;
		double calcM;
		String calc2 = "";
		
		Scanner scan =  new Scanner(System.in);
		
		System.out.println("\tZeller's Algorithm\n");
		
		//loops until 0 is entered
		while (month != 0)
		{
			//loops until a valid month value is entered
			do
			{	
				//gets month from the user
				System.out.print("Enter month (or 0 to exit): ");
				month = scan.nextInt();
				if (month > 12 || month < 0)
					System.out.println("Month must be between 0 and 12");
			}
			while (month > 12 || month < 0);
			
			if (month != 0)
			{
				//loops until the user enters a valid day
				do
				{
					System.out.print("Enter day: \t\t    ");
					day = scan.nextInt();
					if (day > 31 || day < 1)
						System.out.println("Day must be between 1 and 31.");	
				}	
				while (day > 31 || day < 1);
				
				//makes sure year is not negative
				do
				{
					//gets the year from the user
					System.out.print("Enter year:\t\t    ");
					year = scan.nextInt();
					
					//if month is 1 or 2 it is part of the previous year (January is considered month 11)
					if (month < 3)
						year = year - 1;
					century = year;
					year1000Fix = year;
					//error message if year is negative
					if (year < 0)
						System.out.println("Year cannot be negative.");
				}
				while (year < 0);
				
				//calculates the last two digits of year and puts them in calc2
				for (int i = 0; i < 2; i++)
				{
					//gets the last digit of year
					calc1 = year % 10;
					//puts it into a the calc2 string
					calc2 = calc1 + calc2;
					//divides year by 10 so that the next digit can be calculated
					year = year / 10;	
				}
				
				//subtracts month by two as the algorithm requires
				if (month > 2)
				{
					month = month - 2;
				}
				else
				{
					//if month is less than two month is changed to 11 or 12
					//this is because February is considered month 12 and January month 11
					switch(month) {
					case 1:
						month = 11;
						break;
					case 2:
						month = 12;
						break;
					}
				}
				//calculates some of the values for the algorithm
				//divides century by 100 to get the first two digits of a year
				if (century < 100)
					century = 1;
				else
					century = century / 100;
				
				calcM = (2.6 * month - 0.2);
				//gets the last two digits of the year 
				year = Integer.parseInt(calc2);
				//calculates Zeller's algorithm
				g = (((int)(calcM)) + day + year + ((int)(year / 4.0)) + ((int)(century / 4.0)) - 2 * century) % 7;
				
				//if g is less than 0, 7 is added for the calculating of the day to work
				if (g < 0)
					g = g + 7;
				
				//bugfix for the 11th century (the day was a day ahead of the real calendar of that time)
				if (year1000Fix < 1100 && year1000Fix > 999)
				{
					//sets the day back 1
					if (g == 0)
							g = 6;
					else
						g = g - 1;
				}	
							
				//chooses which day to print based on the value of g
				switch (g) {
				case 0:
					System.out.println("\tThe day is Sunday.\n");
					break;
				case 1:
					System.out.println("\tThe day is Monday.\n");
					break;
				case 2:
					System.out.println("\tThe day is Tuesday.\n");
					break;
				case 3:
					System.out.println("\tThe day is Wednesday.\n");
					break;
				case 4:
					System.out.println("\tThe day is Thursday.\n");
					break;
				case 5:
					System.out.println("\tThe day is Friday.\n");
					break;
				case 6:
					System.out.println("\tThe day is Saturday.\n");
					break;
				default:
					System.out.println("There was an error computing the date.");
				}
				//increments count for the entry counter
				count++;
				System.out.println("Number of entries: " + count + "\n");
				//resets calc2 so that entering the same date multiple times doesn't change the result
				calc2 = "";
				
			}	
		}
			
	}

}
