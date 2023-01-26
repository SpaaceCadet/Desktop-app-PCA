package sample;
public class Op_mat {
	
	
	/* Pogramme Simple pour le produit matriciel de deux cas soit Matrix*Matrix ou 
	    Matrice*vecteur */
	
	//CAS 1
	public static double[][] prodMat(double[][] A, double[][] B) {
		double[][] prod = new double[A.length][B[0].length];
		int i, j, k;
		for (i = 0; i < A.length; i++) {
			for (j = 0; j < B[0].length; j++) {
				prod[i][j] = 0;
				for (k = 0; k < A[0].length; k++) {
					prod[i][j] += (A[i][k] * B[k][j]);
				}
			}
		}
		return prod;

	}
	/*CAS 2 ; cette methode est redéfinit c'est deja utilisé lors de calcul du centre 
	de gravité  dans Normalize_mat ligne :46 */
	
	public static double[]prodMat(double[][] A, double[] B) {
		double[]prod = new double[A.length];
		int i, j;
		for (i = 0; i < A.length; i++) {
			prod[i] = 0;
			for (j = 0; j < B.length; j++) {
			    prod[i] += A[i][j] * B[j];
			}
		}
		return prod;

	}
	public static double[][] tr(double[][] A) {

		double[][] B = new double[A[0].length][A.length];

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				B[j][i] = A[i][j];
			}
		}

		return B;
	}


}