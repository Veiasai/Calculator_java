package com.veiasai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class calculator {
    private Scanner exp; // input expression
    private double result_double; // output
    private int state;  // 0 normal , 1 error , 2 done
    private IOException e;
    private char sig; //record + -
    private int grade;

    public calculator()
    {
        this.state = 0;  // init
        this.e = null;
        this.exp = null;
        this.result_double = 0;
        this.sig = ' ';
        this.grade = 0;
    }

    public void setInput(Scanner exp)
    {
        this.exp = exp;
    }

    public double getResult_double()    // get former result
    {
        return result_double;
    }

    public IOException getE()
    {
        return this.e;
    }

    public void cal()
    {
        result_double = cal_private();
    }

    public int getState()
    {
        return this.state;
    }

    private double cal_private()  // restart read and calculate, grade 0
    {
        try
        {
            this.state = 0;
            double num = getNum();
            while (this.state == 0)
            {
                num = handle(num);
            }
            if (this.state == 2)
                this.state = 0;
            return num;
        }
        catch (IOException e)
        {
            this.state = 1;
            this.e = e;
            return 0;
        }
    }

    private double expression()
    {
        try
        {
            double num = getNum();
            num = handle(num);
            return num;
        }
        catch (IOException e)
        {
            this.state = 1;
            this.e = e;
            return 0;
        }
    }

    private double handle(double num)   // grade 1
    {
        try {
            char sig = ' ';
            if (this.sig == ' ')
            {
                sig = getSig();
            }
            else
            {
                sig = this.sig;
                this.sig = ' ';
            }

            switch (sig) {
                case '#':
                    this.state = 2;
                    return num;
                case '+':
                    if (this.grade == 0)
                    {
                        this.grade = 1;
                        return num + expression();
                    }
                    else
                    {
                        this.sig = '+';
                        this.grade = 0;
                        return num;
                    }
                case '-':
                    if (this.grade == 0)
                    {
                        this.grade = 1;
                        return num - expression();
                    }
                    else
                    {
                        this.sig = '-';
                        this.grade = 0;
                        return num;
                    }
                case '*':
                    num = num * getNum();
                    return handle(num);
                case '/':
                    double nextnum = getNum();
                    if (nextnum != 0)
                    {
                        num = num / nextnum;
                        return handle(num);
                    }
                    else
                    {
                        this.e = new IOException("0 division error");
                        this.state = 1;
                        return 0;
                    }
                default:
                    this.state = 1;
                    return 0;
            }
        }
        catch (IOException e)
        {
            this.state = 1;
            this.e = e;
            return 0;
        }
    }

    private double getNum()  throws IOException // get a number
    {
        if (this.exp.hasNextDouble())
        {
            return this.exp.nextDouble();
        }
        else
        {
            throw new IOException("read number error");
        }
    }

    private char getSig()  throws IOException  // get next signal
    {
        if (exp.hasNext())
        {
            String temp = exp.next();
            char sig = temp.charAt(0);
            return sig;
        }
        else
            throw new IOException("read symbol error");
    }
}
