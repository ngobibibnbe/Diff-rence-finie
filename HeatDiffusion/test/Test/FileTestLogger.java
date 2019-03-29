/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.BufferedWriter;
import java.util.Map;
import java.io.FileWriter;

/**
 *
 * @author Yvan TCHOFFO
 */
public class FileTestLogger extends DefaultTestLogger implements TestLogger {
    static BufferedWriter  raf;
    static Object sync=new Object();
    public void init(Map parTest) throws Exception{
        synchronized(sync){
             if(raf==null) raf = new BufferedWriter( 
            new FileWriter("log.txt", true)); 
            }         
        }
    public void log(Map parTest, String event, boolean closeLogs) throws Exception{
        synchronized(sync){
            StringBuilder sb=new StringBuilder();
            sb.append("---;");
            String temp=(String) parTest.get(TESTCASE);
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(TESTREFERENCE);
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(TESTSTARTTIME);
            sb.append(temp==null?"---":temp).append(";");
            long start=Long.parseLong(temp);
            temp=(String) parTest.get(TESTENDTIME);
            long end=temp==null?-1:Long.parseLong(temp);
            long duration=temp==null?-1:(end-start);
            sb.append(temp==null?"-1":duration).append(";");
            sb.append(event).append(";");
            Boolean bool=(Boolean) parTest.get(TESTRESULT);
            sb.append(bool==null?"---":bool.toString()).append(";");
            Throwable th=(Throwable) parTest.get(TESTERROR);
            if(th!=null) temp=parTest.get(PRINTERRORTRACE)!=null? "????" : th.getMessage();
            sb.append(temp==null?"---":temp).append(";");
            temp=(String) parTest.get(TESTSTARTTIME);
            sb.append(temp==null?"---":temp).append(";");
            raf.write(sb.toString());
            raf.write(System.getProperty("line.separator"));
            raf.flush();
            if(closeLogs) raf.close();
            }        
        }
}
