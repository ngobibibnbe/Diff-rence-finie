    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import EDP.HeatDiffusion;
import static Test.DefaultTestLogger.logTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class testhd {

    String fileName = System.getProperty("user.dir") + "/test//Test//df.txt";
    String line = null;
    double tol = 1e-8;
    String methodToTest = "HeatDiffusion.getSolution";
    String testFileLogger = "Test.FileTestLogger";
    String classeATester = "HeatDiffusion";

    public void test() throws Exception {
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
        while (data.capacity() != 0) {
            DoE = (ArrayList) data.pop();
            startTime = System.currentTimeMillis();
            parTest.put("classtotest", classeATester);
            parTest.put("testreference", methodToTest);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testLogger0", testFileLogger);
            logTest(parTest, "start", false);

            parTest.put(TESTCASE, "f= "+DoE.get(0)+"  u(0)="+DoE.get(1)+"  u(n+1)="+DoE.get(2)+"  n="+DoE.get(3)+" " );
            boolean result= oracle((double)DoE.get(1),(double)DoE.get(2),(int)DoE.get(3),(String)DoE.get(0));
            parTest.put(TESTRESULT, result);
            parTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(parTest, "end", false);



        }
    }

    boolean oracle(double a, double b, int n, String x) {
        Vector<Double> F=new Vector();
        if (x.equals("x")) {

                        for (int i = 1; i < n; i++) {
                            F.add(1.0*i / n);
                        }
                    } else {
                        for (int i = 1; i < n; i++) {
                            F.add(0.0);
                        }
                    }
        System.out.println("teille de f"+F.size()+" "+n);
         if(n<=1){
            try{
             HeatDiffusion df1 = new HeatDiffusion(a, b, n-1, F);}
            catch(Exception e){
                return true;
            }
        }
        HeatDiffusion df = new HeatDiffusion(a, b, n-1, F);
        double max = 0;
        double rob=0;
        Vector U = df.getSolution();
        for (int i = 0; i < F.size(); i++) {
            
            if(U.size()==1){
               
             
                rob=((double)b + (double) a - 2 * ((double) U.get(i)));
                rob = Math.pow(n, 2) * rob - (double) F.get(i);
                rob = Math.abs(rob);
                if (rob > max) {
                    max = rob;
                

            } 
            }
            else if (i == 0 && U.size()!=1) { 
                
              Double  Ui1=(double) U.get(i + 1);
              double  Ui_1=(double) a;
                rob=((double) U.get(i + 1) + (double) a - 2 * ((double) U.get(i)));
                rob = Math.pow(n, 2) * rob - (double) F.get(i);
                rob = Math.abs(rob);
                if (rob > max) {
                    max = rob;
                }

            } else if (i == F.size() - 1) {
                rob = Math.pow(n, 2) * ((double) b + (double) U.get(i - 1) - 2 * ((double) U.get(i))) - (double) F.get(i);

                rob = Math.abs(rob);
                if (rob > max) {
                    max = rob;
                }
            } else {
                rob = Math.pow(n, 2) * ((double) U.get(i - 1) + (double) U.get(i + 1) - 2 * ((double) U.get(i))) - (double) F.get(i);
                rob = Math.abs(rob);
                if (rob > max) {
                    max = rob;
                }
            }

        }

        if (max < tol) {
            return true;
        } else {
            return false;
        }
    }

    Stack data() {
        Stack pile = new Stack();//on va garder nos donnés de test dans cette pile .

        String fichier = "C:\\Users\\USER\\Documents\\NetBeansProjects\\HeatDiffusion\\test\\Test\\df.txt";

        try {
            int n;
            double h;
            double a, b;
            Vector<Double> F = new Vector();
            BufferedReader buff = new BufferedReader(new FileReader(fichier));
            ArrayList donne_entre = new ArrayList();
            try {
                String line;
// Lire le fichier ligne par ligne 
// La boucle se termine quand la méthode affiche "null" 
                int o = 0;
                while ((line = buff.readLine()) != null) {
                    donne_entre = new ArrayList();
                    F = new Vector();
                    System.out.println(line.split(" ")[3] + "  " + o++);
                    n = Integer.parseInt(line.split(" ")[3]);
                    h = (double) 1 / n;
                   System.out.println(h);
                    a = Double.parseDouble(line.split(" ")[1]);
                    b = Double.parseDouble(line.split(" ")[2]);
                    if (line.split(" ")[4].equals("x")) {
                        donne_entre.add("x");

                        
                    } else {
                        
                            donne_entre.add("null");
                          
                    }
                    donne_entre.add(a);
                    donne_entre.add(b);
                    donne_entre.add(n);
                   // donne_entre.add(F);
                    pile.push(donne_entre);
                }

                buff.close(); //Lecture fini donc on ferme le flux 

                //   System.out.println(pile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return pile;
    }

    public static void main(String[] args) throws Exception {
        testhd d = new testhd();
         d.data();
       d.test();
    }

}
