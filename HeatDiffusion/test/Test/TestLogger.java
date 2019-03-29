/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.Map;

/**
 *
 * @author Yvan TCHOFFO
 */
public interface TestLogger {
    public final static String TESTSTARTTIME="teststarttime";
    public final static String TESTCASE="testcase"; 
    public final static String TESTREFERENCE="testreference"; 
    public final static String LFM="lfm";
    public final static String TESTOBJECT="testobjet";
    public final static String TESTRESULT="testresult";
    public final static String TESTERROR="testerror";   
    public final static String PRINTERRORTRACE="printErrorTrace";
    public final static String TESTENDTIME="testendtime";
    void init(Map parTest) throws Exception;
    void log(Map parTest, String event, boolean closeLogs) throws Exception;   
}
