package veiasai_test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.veiasai.Calculator;

import java.io.IOException;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * 自己写的case
     */

    //常规测试
    public void testadd() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("1 + 1 e"));
        cal.cal();
        assertEquals(2.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    public void testsub() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("1 - 1 e"));
        cal.cal();
        assertEquals(0.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    public void testmul() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 * 5 e"));
        cal.cal();
        assertEquals(25.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    public void testdiv() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 / 2 e"));
        cal.cal();
        assertEquals(2.5, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    //除以 0 测试
    public void testdiv0() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 / 0 + 2 - 1 * 5 e"));
        cal.cal();
        assertEquals(0.0, cal.getResult_double());
        assertEquals(1,cal.getState());
        assertEquals(new IOException("0 division error").toString(), cal.getE().toString());
    }

    //综合测试
    public void test1() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 / 2 + 2 - 1 * 5 e"));
        cal.cal();
        assertEquals(-0.5, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    public void test2() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 * 4 + 2 - 2 * 5 / 5 e"));
        cal.cal();
        assertEquals(20.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    public void test3() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("5 * 4 + 2 - 2 * 5 / 5 + 4 e"));
        cal.cal();
        assertEquals(24.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }
    public void test4() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("1 - 5 + 4 e"));
        cal.cal();
        assertEquals(0.0, cal.getResult_double());
        assertEquals(0,cal.getState());
    }

    //异常输入测试
    public void test5() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("1- 5 + 4 e"));
        cal.cal();
        assertEquals(1,cal.getState());
        assertEquals(new IOException("read number error").toString(), cal.getE().toString());
    }

    public void test6() {
        Calculator cal = new Calculator();
        cal.setInput(new Scanner("1 - 5 + 4 "));
        cal.cal();
        assertEquals(1,cal.getState());
        assertEquals(new IOException("read symbol error").toString(), cal.getE().toString());
    }

}
