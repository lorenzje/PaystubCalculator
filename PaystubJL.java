//----------------------------------------------------------------------|
// CINCINNATI STATE | IT - 161 | SP24            						|
//----------------------------------------------------------------------|
// Abstract  : PaystubJL (Project 2 Practical)								   		        |
// Professor : T. Gartland                       						|
// Student   : Jack Lorenz                       						|         
//----------------------------------------------------------------------|

//----------------------------------------------

//----------------------------------------------------------------------
// Imports
//----------------------------------------------------------------------
import java.io.BufferedReader;
import java.io.InputStreamReader;



//----------------------------------------------------------------------
//Main Method
//----------------------------------------------------------------------
/** Method  : MAIN
 *  Purpose : the main method for the program 
 ---------------------------------------------*/
public class PaystubJL 
{

    public static void main(String[] args) 
    {
        System.out.println("Paystub Calculator\n-------------------------------------------------------");
        
        float fltTotalGrossIncome = 0; 
		float fltTotalMedicareTax = 0; 
		float fltTotalSocialSecurityTax = 0; 
		float fltTotalFederalIncomeTaxWithheld = 0;
		float fltTotalNetIncome = 0;
		float fltHourlyWage = 0;
		float fltHoursWorked = 0;
		int intWithholdingExemptions = 0;
        float fltGrossEarnings = 0;
        float fltMedicareTax = 0;
        float fltSocialSecurityTax = 0;
        float fltFederalIncomeTaxWithheld = 0;
        float fltNetEarnings = 0;
		
        String strMaritalStatus;
		String strName;
		
		//Loop until Quit 
        while (true)
        {
            System.out.print("Employee Name:(type 'Quit' to end program) ");
            strName = readStringFromUser();
            if ("QUIT".equalsIgnoreCase(strName)) 
            {
                break;
            }
            
                       
            //Get Inputs
            System.out.print("Hourly Wage: ");
            fltHourlyWage = readFloatFromUser();
            System.out.print("Hours Worked: ");
            fltHoursWorked = readFloatFromUser();
            System.out.print("Withholding Exemptions: ");
            intWithholdingExemptions = readIntegerFromUser();
            System.out.print("Marital Status (S = Single, M = Married): ");
            strMaritalStatus = readStringFromUser();
            
                       
            //Calculate Variables
            fltGrossEarnings = calculateGrossEarnings(fltHourlyWage, fltHoursWorked);
            fltMedicareTax = calculateMedicareTax(fltGrossEarnings);
            fltSocialSecurityTax = calculateSocialSecurityTax(fltGrossEarnings);
            fltFederalIncomeTaxWithheld = calculateFederalIncomeTaxWithheld(fltGrossEarnings, intWithholdingExemptions, strMaritalStatus);
            fltNetEarnings = fltGrossEarnings - (fltMedicareTax + fltSocialSecurityTax + fltFederalIncomeTaxWithheld);
            
            
            //Add Variables to Totals
            fltTotalGrossIncome += fltGrossEarnings;
            fltTotalMedicareTax += fltMedicareTax;
            fltTotalSocialSecurityTax += fltSocialSecurityTax;
            fltTotalFederalIncomeTaxWithheld += fltFederalIncomeTaxWithheld;
            fltTotalNetIncome += fltNetEarnings;
            
            
            //Print Output
            System.out.printf("Paycheck for %s\n", strName);
            System.out.printf("Gross Income:                           $      %.2f\n", fltGrossEarnings);
            System.out.printf(" less Medicare Tax:                     $        %.2f\n", fltMedicareTax);
            System.out.printf(" less Social Security Tax:              $       %.2f\n", fltSocialSecurityTax);
            System.out.printf(" less Federal Income Tax Withheld:      $       %.2f\n", fltFederalIncomeTaxWithheld);
            System.out.printf("Net Income:                             $      %.2f\n", fltNetEarnings);
            System.out.println("-----------------------------------------------------");
        }
        
        //Print After quit 
        System.out.println("Grand Paycheck Totals--------------------------------");
        System.out.printf("Total Gross Income:                     $     %.2f\n", fltTotalGrossIncome);
        System.out.printf("Total Medicare Tax:                     $       %.2f\n", fltTotalMedicareTax);
        System.out.printf("Total Social Security Tax:              $      %.2f\n", fltTotalSocialSecurityTax);
        System.out.printf("Total FICA Tax:                         $      %.2f\n", fltTotalMedicareTax + fltTotalSocialSecurityTax);
        System.out.printf("Total Federal Income Tax Withheld:      $      %.2f\n", fltTotalFederalIncomeTaxWithheld);
        System.out.printf("Total Net Income:                       $     %.2f\n", fltTotalNetIncome);
        System.out.println("-----------------------------------------------------");
        
        
    }
    
    
    
  //----------------------------------------------------------------------
  //AUX Methods
  //----------------------------------------------------------------------

    /** Method  : ReadFloatFromUser
     *  Purpose : Allows program to read float from user
     ----------------------------------------------------*/
    public static float readFloatFromUser() 
    {
        float sngValue = 0;
        try {
            BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));
            String strBuffer = burInput.readLine();
            sngValue = Float.parseFloat(strBuffer);
        } catch (Exception excError) {
            System.out.println(excError.toString());
        }
        return sngValue;
    }
    
    

    /** Method  : ReadIntegerFromUser
     *  Purpose : Allows program to read integer from user
     ----------------------------------------------------*/
    public static int readIntegerFromUser() 
    {
        int intValue = 0;
        try {
            BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));
            String strBuffer = burInput.readLine();
            intValue = Integer.parseInt(strBuffer);
        } catch (Exception excError) {
            System.out.println(excError.toString());
        }
        return intValue;
    }

    /** Method  : ReadStringFromUser
     *  Purpose : Allows program to read String from user
     ----------------------------------------------------*/
    public static String readStringFromUser() 
    {
        String stringValue = "";
        try 
        {
            BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));
            stringValue = burInput.readLine();
        } catch (Exception excError) 
          {
            System.out.println(excError.toString());
          }
        return stringValue;
    }
    
    
    
    /** Method  : calculateGrossEarnings
     *  Purpose : Allows program to read String from user
     ----------------------------------------------------*/
    private static float calculateGrossEarnings(float hourlyWage, float hoursWorked) {
        if (hoursWorked <= 40) {
            return hourlyWage * hoursWorked;
        } else {
            return (hourlyWage * 40) + ((hoursWorked - 40) * hourlyWage * 1.5f);
        }
    }
    
    
    
    /** Method  : calculateMedicareTax
     *  Purpose : Allows program to read String from user
     ----------------------------------------------------*/
    private static float calculateMedicareTax(float fltGrossEarnings) 
    {
        return fltGrossEarnings * 0.0145f;
    }
    
    
    
    /** Method  : calculateSocialSecurityTax
     *  Purpose : Allows program to read String from user
     ----------------------------------------------------*/
    private static float calculateSocialSecurityTax(float fltGrossEarnings) 
    {
        return fltGrossEarnings * 0.062f;
    }


    
    /** Method  : calculateFederalIncomeTaxWithheld
     *  Purpose : Allows program to read String from user
     ----------------------------------------------------*/
    private static float calculateFederalIncomeTaxWithheld(float fltGrossEarnings, int intWithholdingExemptions, String strMaritalStatus) 
    {
        float fltAdjustedGrossIncome = 0;
        float fltFederalIncomeTax = 0;
        fltAdjustedGrossIncome = fltGrossEarnings - (intWithholdingExemptions * 55.77f);
        if (strMaritalStatus.equalsIgnoreCase("S")) 
        { // Single
            if (fltAdjustedGrossIncome <= 50.99) 
            {
            	fltFederalIncomeTax = 0;
            } 
            else if (fltAdjustedGrossIncome <= 500.99) 
            {
            	fltFederalIncomeTax = (fltAdjustedGrossIncome - 51) * 0.10f;
            } 
            else if (fltAdjustedGrossIncome <= 2500.99) 
            {
            	fltFederalIncomeTax = 45.00f + (fltAdjustedGrossIncome - 500) * 0.15f;
            } 
            else if (fltAdjustedGrossIncome <= 5000) 
            {
            	fltFederalIncomeTax = 345.00f + (fltAdjustedGrossIncome - 2500) * 0.20f;
            } 
            else 
            {
            	fltFederalIncomeTax = 845.00f + (fltAdjustedGrossIncome - 5000) * 0.25f;
            }
        } else if (strMaritalStatus.equalsIgnoreCase("M")) 
        { // Married
            if (fltAdjustedGrossIncome <= 50.99) 
            {
            	fltFederalIncomeTax = 0;
            } 
            else if (fltAdjustedGrossIncome <= 500.99) 
            {
            	fltFederalIncomeTax = (fltAdjustedGrossIncome - 51) * 0.05f;
            } 
            else if (fltAdjustedGrossIncome <= 2500.99) 
            {
            	fltFederalIncomeTax = 22.50f + (fltAdjustedGrossIncome - 500) * 0.10f;
            } 
            else if (fltAdjustedGrossIncome <= 5000) 
            {
            	fltFederalIncomeTax = 225.50f + (fltAdjustedGrossIncome - 2500) * 0.15f;
            } 
            else 
            {
            	fltFederalIncomeTax = 600.50f + (fltAdjustedGrossIncome - 5000) * 0.20f;
            }
        }
        return fltFederalIncomeTax;
    }
}
