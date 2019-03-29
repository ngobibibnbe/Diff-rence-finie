package EDP;
/**
 * Titre :        Your Product Name
 * Description :  Your description
 * Copyright :    Copyright (c) 1998
 * Soci t  :      lamas
 * @author I.Moukouop
 * @version
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
//import test.TestDataLoader;
import com.mdalsoft.test.TestLogger;
import com.mdalsoft.test.Testable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;



/**
 *
 * @author ibrahim
 */
public class TestLoggerDifferenceFinie {
  
    //String  baseTestPath="c:\\moukouop\\moukouop\\mdalerp";
    String sep="\\";
    String testFileLogger="com.mdalsoft.test.FileTestLogger";
    String dbTestLogger="com.mdalsoft.test.DbTestLogger";  
    /**
     * replacer par le nom de la methode testee, sous le format correspondant à son nom complet
     * le plus souvent, si vous n'avez pas changé l'appel à testMonCas, il faudra juste changer le nom de la classe
     */
    String firstMethodToTest="TestLoggerDifferenceFinie.testMonCas";
     /**
     * replacer par le nom de la classe testee, sous le format correspondant à son nom complet
     */
    static String classeATester="EDP.HeatDiffusion";
    public static void main(String[] args) throws Exception{
         //18mn pour preparer le programme minimal de test
        //Double d=new Double(1.020010017E9);
        //System.out.println("Mon test "+System.currentTimeMillis());
       // System.exit(0);
        //DataDescriptor dd=new DataDescriptor("jdbc:odbc:nipma","","", "sun.jdbc.odbc.JdbcOdbcDriver");
        long startTime=System.currentTimeMillis();
        Map parTest=new HashMap();
        String methodToTest="TestLoggerDifferenceFinie.main";//ici, remplacer TestLoggerExample par le nom de la classe de ce fichier
        String testFileLogger="com.mdalsoft.test.FileTestLogger";
        try {
            parTest.put("classtotest",classeATester);
            parTest.put("teststarttime",""+startTime);
             //nomclasse.nommethode testee et eventuellement les signatures de paramètres s'il y a plusieurs methodes de meme nom
            //dans le respect des conventions des servicelocator
            parTest.put("testreference",methodToTest); 
            parTest.put("testLogger0",testFileLogger);    
            logTest(parTest,"start",false);
            
             
           
            
            TestLoggerDifferenceFinie mtti=new TestLoggerDifferenceFinie();
            parTest.put("testobjet l'objet que j utilise pour mon test",mtti);
            
            /**
             * il est souhaitable de ne rien changer  ici. Pour tester un autre cas, créer une autre classe de test.
             */
            boolean rt=mtti.testMonCas();
            parTest.put("testresult resultat de mon test i",rt);
           
            System.out.println("Situation du test "+rt);
            Logger.global.info("Situation du test "+rt);
           // Messager.messageDlg("Alerte", "Situation du test "+rt, Message.OK) ;         //mtti.test(lfm);
            }catch(Throwable exx){
                            //mettre un point d'arrêt à l'instruction printstacktrace suivante                            
                              exx.printStackTrace();
                              parTest.put("testresult",false);
                              parTest.put("testerror", exx);
                             }
                         finally{
                            parTest.put("testendtime",""+System.currentTimeMillis());
                            logTest(parTest,"end",true);
                            //if(lfm!=null) lfm.closeSession();
                              //si vous souhaitez couperle programme, vous vous faire System.exit(0), après avoir proprement libere les ressources
                            System.exit(0);			 
                            }
        }
        public boolean testMonCas() throws Exception{
            //on initialise pour le log
            Map paramLogTest=initParamTest(System.currentTimeMillis(),firstMethodToTest,null);
            logTest(paramLogTest, "start", false);
            boolean testResult=false;
            try{
                //passer cette variable à false si on n'utilise pas de threads dasns le traitement
               // boolean threaded=true;
                boolean threaded=false;
                //passer cette variable à fale si on ne souhaite pas exporter les données créées lors du test avant de réaliser les tests
               // boolean exportNewTestDataBeforeProcess=true;
               boolean exportNewTestDataBeforeProcess=false;
                boolean loadTestData=true;           
                boolean testDataLoaded=false;
                boolean useDbUnit=false;
                boolean exportMasterData=false;
             
                  //les paramètres des tests
                Map testParams=new HashMap();         
                //chargement des données
                
                Vector B=new Vector();
                int N=4;
                int a=0;
                int b=1;
              for(int i=0;i<4;i++)  B.add(0);
              Vector DonneTest = new Vector();
              DonneTest.add(a);DonneTest.add(b);DonneTest.add(N);DonneTest.add(B);
              testParams.put(DonneTest, "1");
               // DataSetMap testdata=null;
               Object testData=null;
              
               //testData="EDP.dtest.txt";

            

                //remplacer par le chemin du fichier de données de tests à charger 
                //s'il en existe un, sinon commenter
                 prepareChecking(testParams);
                //on realise le traitement
                Testable tet=(Testable)Class.forName(classeATester).newInstance();
                testParams.put("testedobject",tet);
                /**
                 * se contente d'executer la methode à tester, et à remplir testParams avec les éléments qui permettront de verifier le resultat
                 */
                tet.test(testParams);
                testResult=checkResult(testParams); 
                }catch(Throwable th){
                      paramLogTest.put("testerror", th);
                      throw th;                           
                     }finally{
                            paramLogTest.put("testresult",testResult);
                            paramLogTest.put("testendtime",""+System.currentTimeMillis());
                            logTest(paramLogTest, "end", false);                    
                            }
            return testResult;
            }
   /**
   * mt dans le map  testParams tout ce qui sera plus tard necessaire pour les oracles de test
   */
    private void prepareChecking(Map testParams) throws Exception{
         /*
        * ajoute au map les paramètres nécessaies au programme qui sera testé. Le testeur et le developpeur s'entendront sur les noms souhaités
        * cette méthode est redefinie au cas par cas.
        */
          Vector B=new Vector();
                int N=4;
                int a=0;
                int b=0;
              for(int i=0;i<4;i++)  B.add(0);
              Vector DonneTest = new Vector();
              DonneTest.add(a);DonneTest.add(b);DonneTest.add(N);DonneTest.add(B);
            testParams.put("entry", new HeatDiffusion(a,b,N,B));
           }
/**
 * verifie les oracles de test
 * @param testParams
 * @return true si ok et false sinon
 * @throws Exception 
 */
    private  boolean checkResult( Map testParams) throws Exception {
	//attention àce code qui compare deux réels en utilisant l'egalité
          Vector B=new Vector();
               
              for(int i=0;i<4;i++)  B.add(0);
           return ((Double)testParams.get("calulatedsolution")).equals(B);
            }

   
    private Map initParamTest(long startTime, String firstMethodToTest, Object o) {
        Map parTest=new HashMap(); 
        parTest.put("teststarttime",""+startTime);
             //nomclasse.nommethode testee et eventuellement les signatures de paramètres s'il y a plusieurs methodes de meme nom
            //dans le respect des conventions des servicelocator
        parTest.put("testreference",firstMethodToTest); 
        parTest.put("testLogger0",testFileLogger); 
        if(o!=null) parTest.put("lfm",o);
        parTest.put("testobjet",this);
        return parTest; 
        }
 
    
    
}







