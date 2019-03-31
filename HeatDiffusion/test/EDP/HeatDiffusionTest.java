/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDP;

import static Test.DefaultTestLogger.logTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USER
 */
public class HeatDiffusionTest {
      String fileName = System.getProperty("user.dir") + "/test//Test//df.txt";
    String line = null;
    double tol = 1e-8;
    String methodToTest = "HeatDiffusion.getSolution";
    String testFileLogger = "Test.FileTestLogger";
    String classeATester = "HeatDiffusion";
    public HeatDiffusionTest() {
    }
    
    @Before
    public void setUp() {
        

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class HeatDiffusion.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        HeatDiffusion.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSolution method, of class HeatDiffusion.
     */
    @Test
    public void testGetSolution() {
        System.out.println("getSolution");
        HeatDiffusion instance = null;
        Vector<Double> expResult = null;
        Vector<Double> result = instance.getSolution();
         Map parTest = new HashMap();
        String TESTSTARTTIME = "teststarttime";
        String TESTCASE = "testcase";
        String TESTREFERENCE = "testreference";
        String LFM = "lfm";
        String TESTOBJECT = "testobjet";
        String TESTRESULT = "testresult";
        String TESTERROR = "testerror";
        String PRINTERRORTRACE = "printErrorTrace";
        String TESTENDTIME = "testendtime";

        long startTime = System.currentTimeMillis();
        parTest.put("classtotest", classeATester);
        parTest.put("testreference", methodToTest);
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);

        parTest.put(TESTCASE, "mon cas de test");
        parTest.put(TESTREFERENCE, "REFERENCE test");
        //il est de type throwable parTest.put(TESTERROR, "ERREUR DE  test");
        parTest.put(TESTRESULT, true);
        parTest.put(LFM, "lfm");
        parTest.put(TESTOBJECT, "OBJET  test");
        parTest.put("testendtime", "" + System.currentTimeMillis());
        logTest(parTest, "end", false);

        parTest.put("classtotest", classeATester);
        parTest.put("testreference", methodToTest);
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testLogger0", testFileLogger);
        logTest(parTest, "start", false);

        parTest.put(TESTCASE, "mon cas de test 2");
        parTest.put(TESTREFERENCE, "REFERENCE test2");
        //il est de type throwable parTest.put(TESTERROR, "ERREUR DE  test");
        parTest.put(PRINTERRORTRACE, "TRACE DE L ERREUR test");
        parTest.put(TESTRESULT, false);
        parTest.put(LFM, "lfm");
        parTest.put(TESTOBJECT, "OBJET  test");
        //  parTest.put(TESTENDTIME, "FIN DE  test");

        parTest.put("testendtime", "" + System.currentTimeMillis());
        logTest(parTest, "end", false);
        //on verifie chacun des tests et on les ecrits dans notre fichier log 
        Stack data = data();
        ArrayList DoE;
        int i=0;
        while (!data.empty()) {i++;
            DoE = (ArrayList) data.pop();
            startTime = System.currentTimeMillis();
            parTest.put("classtotest", classeATester);
            parTest.put("testreference", methodToTest);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testLogger0", testFileLogger);
            logTest(parTest, "start", false);

            parTest.put(TESTCASE, "cas "+i+"f= "+DoE.get(0)+"  u(0)="+DoE.get(1)+"  u(n+1)="+DoE.get(2)+"  n="+DoE.get(3)+" " );
            boolean result= oracle((double)DoE.get(1),(double)DoE.get(2),(int)DoE.get(3),(String)DoE.get(0));
            parTest.put(TESTRESULT, result);
            parTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(parTest, "end", false);



        }
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of test method, of class HeatDiffusion.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        Map testParams = null;
        HeatDiffusion instance = null;
        instance.test(testParams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
