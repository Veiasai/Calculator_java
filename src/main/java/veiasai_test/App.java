package veiasai_test;

import com.veiasai.calculator;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        calculator cal = new calculator();
        Scanner in = new Scanner(System.in);
        while (true)
        {
            cal.setInput(new Scanner(in.nextLine()));
            cal.cal();
            if (cal.getState() == 0)
            {
                System.out.println(cal.getResult_double());
            }
            else
            {
                System.out.println(cal.getE().toString());
            }
        }
    }
}
