package EDP;

import java.util.Map;
import java.util.Vector;

/**
 *
 * @author LONLA GATIEN JORDAN
 */
public class HeatDiffusion extends EDP1{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Vector<Double> B = new Vector();
       int N = 1000;
       for(int i = 1; i < N ; i++){
           B.add(1.0*i/N);
       }
       HeatDiffusion H = new HeatDiffusion(0.0, 1.0, N-1, B);
       H.getSolution();
    }

    public HeatDiffusion(double a, double b, int N, Vector<Double> B) {
        super(a, b, N, B);
    }

    @Override
    public Vector<Double> getSolution() {
        Vector<Double> C = new Vector();
        Vector<Double> U = new Vector();
        Vector<Double> V = new Vector();
        Double h = 1.0/(this.N + 1);
        Double c = 0.0, d = this.b - this.a; // c = j*x_j 
        Double H = Math.pow(h,2);
        
        for(int j = 0; j <this.B.size() ; j++){
            C.add(this.B.get(j)*H);
            V.add(0.0);
        }
        
        for(int j = 1; j <=this.B.size() ; j++){
            c = j * C.get(j-1);
            for(int i = 1; i <= this.B.size(); i++){
                V.set(i-1, V.get(i-1)+(((i<j)?1.0*i/j:1)-i*h)*c);
            }
        }
        
        for(int j = 0; j <this.B.size() ; j++){
            U.add(this.a + V.get(j) + d*B.get(j));
        }
       // System.out.println(V);
        System.out.println(U);
        return U;
    }
      public void test(Map testParams) {
        HeatDiffusion d=(HeatDiffusion)testParams.get("entry");
        testParams.put("calulatedsolution",d.getSolution());        
        }
}
