package com.veiasai;

import java.io.IOException;
import java.util.Scanner;

public class calculator {
    private Scanner exp; // input expression
    private double result_double; // output
    private int state;  // 0 normal , 1 error
    private IOException e;
    public calculator()
    {
        this.state = 0;  // init
        this.e = null;
        this.exp = null;
        this.result_double = 0;
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

    private double cal_private()  // restart read and calculate
    {
        try
        {
            this.state = 0;
            double num = getNum();
            return handle(num);
        }
        catch (IOException e)
        {
            this.state = 1;
            this.e = e;
            return 0;
        }
    }

    private double handle(double num)
    {
        try {
            char sig = getSig();
            switch (sig) {
                case '#':
                    return num;
                case '+':
                    return num + cal_private();
                case '-':
                    return num - cal_private();
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
        if (exp.hasNextLine())
        {
            String temp = exp.next();
            char sig = temp.charAt(0);
            return sig;
        }
        else
            throw new IOException("read signal error");
    }
}
