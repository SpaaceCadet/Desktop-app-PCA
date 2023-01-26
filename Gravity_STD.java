package sample;

public class Gravity_STD{
 
	public static double [] gv(double [][] myMat) {

		double[] Vecteur_1 = new double[myMat.length];

		for (int i = 0; i < Vecteur_1.length; i++) {

				Vecteur_1[i] = (float)1/(Vecteur_1.length);
		}

		// le resultat va etre de la forme de matrice n x 1

		return  Op_mat.prodMat(Op_mat.tr(myMat),Vecteur_1);

          	}
	
	public static double [] sd(double [][] myMat) {
		double sum = 0;
		double[] std = new double[myMat[0].length];
		// on calcule les ecarts type des variables

		for (int j = 0; j < myMat[0].length; j++) {
			for (int i = 0; i < myMat.length; i++) {
				sum += Math.pow(myMat[i][j], 2);
			}
			std[j] = Math.sqrt(sum / (myMat.length - 1));
		}

		return std;
		
	}
	
	
}
