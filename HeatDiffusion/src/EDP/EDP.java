package EDP;

import java.util.Vector;

/**
 *
 * @author LONLA GATIEN JORDAN
 */

/*
* EDP = Equations aux Dérivées Partielles
* qui se ramène à l'équation AU = B
*/
public interface EDP<R> {
    
    /**
     * @param N le nombre de point intérieur de la subdivision de [0,1]
     *
     * @param B le vecteur des f(x_i) avec i = 1,...,N
     */
    
    void setProblem(int N, Vector<R> B); 
    
    /**
     * @param a représente u(0)
     *
     * @param b représente u(1)
     */
    void setConstraint(R a, R b);
    
    /**
     * @return le vecteur solution U
     */
    Vector<R> getSolution();
}
