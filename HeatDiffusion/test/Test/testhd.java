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
    }

   public boolean oracle(double a, double b, int n, String x) {
             Vector<Double> F=new Vector(); 
       
        /******* initialisation du vecteur F qui est un paramètre de 
         notre fonction heatdiffusion
          *******/
        if (x.equals("x")) {

                        for (int i = 1; i < n+1; i++) {
                         F.add((1.0*i) /( n+1));
                               //   System.out.println( (1.0*i)/(n+1) );
                        }
                    } else if(x.equals("null")){
                        for (int i = 1; i < n+1; i++) {
                            F.add(0.0);
                        }
                    }
        else if(x.equals("x2")){
                        for (int i = 1; i < n+1; i++) {
                            F.add(Math.pow((1.0*i) /( n+1), 2));
                        }
                    }
        else if(x.equals("exp")){
                        for (int i = 1; i < n+1; i++) {
                            F.add(Math.exp((1.0*i) /( n+1)));
                        }
                    }
        //System.out.println("teille de f est "+F+" et n est "+n);
        /************FIN DE L INITIALISATION DE F***************/
        
        
        /************ON VERIFIE QUE F RESPECTE LES CRITERES SINON ON RETOURNZ UNE EXCEPTION***************/
         if(n<=0){
            try{
             HeatDiffusion df1 = new HeatDiffusion(a, b, n, F);}
            catch(Exception e){
                return true;
            }
        return false;}
         
         /************SI ON ARRIVE ICI CA VEUX DIRE QUE F N A PAS RETOURNE D EXCEOTION
          A PARTIR D ICI ON CALCULE L ECART MAX ENTRE LE RESULTAT EXACTE ET LE RESULTAT TROUVE
          ***************/
         else{ tol=0.5;
             HeatDiffusion df = new HeatDiffusion(a, b, n, F);
        double errmax = 0;
        double err=0;
        Vector<Double> U = df.getSolution();
 
        if(x.equals("x")){
            for(int i=0;i<U.size();i++){
                err=(Math.pow(((i+1)/(n+1)), 3)/6)+(b-a-(1/6))*((i+1)/(n+1))+a;
                err=Math.abs(err-(double)U.get(i));
                if(err>errmax){
                    errmax=err;
                }
            }
        }else if(x.equals("null")){
                          for(int i=0;i<U.size();i++){
                err=(b-a)*((i+1)/(n+1))+a;
                                err=Math.abs(err-(double)U.get(i));

                if(err>errmax){
                    errmax=err;
                }
            }
                    }
        else if(x.equals("x2")){
  for(int i=0;i<U.size();i++){
                err=(Math.pow(((i+1)/(n+1)), 4)/12)+(b-a-(1/12))*((i+1)/(n+1))+a;
                                err=Math.abs(err-(double)U.get(i));

                                if(err>errmax){
                    errmax=err;
                }
            }
                    }
        else if(x.equals("exp")){
                         for(int i=0;i<U.size();i++){
                err=(Math.exp(((i+1)/(n+1))))+(b-a-Math.exp(1)+1)*((i+1)/(n+1))+a-1;
                               err=Math.abs(err-(double)U.get(i));

                if(err>errmax){
                    errmax=err;
                }
            }
                    }
            
                   System.out.println("l'erreur de  consistace est est"+errmax+"et tol c ezst"+tol);
 
        if (errmax < tol) {
            return true;
        } else {
            return false;
        }
        
    }}

   /** LA FONCTION CI BAS RECUPERE LES DONNES SOUS LA FORME DU UPLET (F,a,b,n) ET   LES INSERE DANS UNE
     PILE QUI SERA RETOURNEE A LA SORTIE  CETTE FONCTION MARCHE PARFAITEMENT SUIVANT LE RESPECT DES TYPES 
     DONNE COMME DANS LE FICHIER df.txt DE CE DOSSIER  **/
    public Stack data() {
        Stack pile = new Stack();//on va garder nos donnés de test dans cette pile .

        String fichier = System.getProperty("user.dir") + "/test//Test//df.txt";

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
                  //  System.out.println(line.split(" ")[3] + "  " + o++);
                    n = Integer.parseInt(line.split(" ")[3]);
                    h = (double) 1 / n;
                   //System.out.println(h);
                    a = Double.parseDouble(line.split(" ")[1]);
                    b = Double.parseDouble(line.split(" ")[2]);
                    if (line.split(" ")[4].equals("x")) {
                        donne_entre.add("x");

                        
                    } else if(line.split(" ")[4].equals("null")) {
                        
                            donne_entre.add("null");
                          
                    }
                    else if(line.split(" ")[4].equals("x2")) {
                        
                            donne_entre.add("x2");
                          
                    }
                    else  {
                        
                            donne_entre.add("exp");
                          
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
      
     //boolean re=d.oracle(333000000, 2.0 ,3330, "x");
       //     System.out.println(        d.data());

    }

}
