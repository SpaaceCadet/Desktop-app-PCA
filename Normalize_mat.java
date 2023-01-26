package sample;
import java.io.FileNotFoundException;
import java.math.*;
public class Normalize_mat {
	// la matrice gravite est de la forme n x 1 c'est un vecteur
	private double[] gravite;
	private double [] std;
	private double [][] Mat;
	
	public Normalize_mat( double [][] mymat)  {
		Mat=mymat;
		gravite=Gravity_STD.gv(Mat);
		std=Gravity_STD.sd(Mat);
	}
	public void  Norm() {
		// on doit tout d'abord calculer les écart types
		// mtn apres calcul de l'ecart type et l'avoir stocké dans std on va normaliser
		// le fait qu'on a centrer nos données ceci nous a simplifier le calcul de
		// l'ecart type
		// on a calculé un ecart type corrigé cad on a n-1 ddl
		for (int j = 0; j < Mat[0].length; j++) {
			for (int i = 0; i < Mat.length; i++) {
				Mat[i][j] = Mat[i][j] / std[j];
			}
		}
		//return Mat;
	}

	public void Center() {
		// ce vecteur on va le remplir des 1 pour pouvoir calculer le centre
		gravite = Gravity_STD.gv(Mat);
		for (int j = 0; j < Mat[0].length; j++) {
			//gravite[j]= 1 / (Mat.length - 1) * gravite[j]; deja fai dans gravity_std
			for (int i = 0; i < Mat.length; i++) {
				Mat[i][j] = Mat[i][j] - gravite[j];
			}
		}
		//return Mat;
	}
	public double [][]get_mat(){
		return Mat;
	}
}
