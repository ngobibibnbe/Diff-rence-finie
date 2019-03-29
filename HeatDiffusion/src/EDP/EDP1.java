package EDP;

import java.util.Vector;

/**
 *
 * @author LONLA GATIEN JORDAN
 */

/*
* EDP1 = Equations aux Dérivées Partielles en dimension 1
*/

/*
* Il s'agit des EDP à coefficients réels
*/
public abstract class EDP1 implements EDP<Double>{
    
    public double a;
    public double b;
    public int N;
    public Vector<Double> B;

    public EDP1(double a, double b, int N, Vector<Double> B) {
        this.setConstraint(a, b);
        this.setProblem(N,B);
    }
    
    @Override
    public final void setProblem(int N, Vector<Double> B) {
        if(N <= 0 || B == null || B.size() != N){
            throw new IllegalArgumentException("Wrong Problem definition");
        }else{
           this.N = N;
           this.B = B; 
        }   
    }

    @Override
    public final void setConstraint(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public abstract Vector<Double> getSolution();
    
}
