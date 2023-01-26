package sample;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
/*Dans cette partie de code je peux calculer la matrice de covariance, vecteurs propres
  valeurs propres , participation des  individus*/

public class COV_Corr_MAT {
	Normalize_mat my_norm_mat;
	private double[][] Mat_cov;
	private double [][] Mat_corr;
	//le eigenDecomposition me permet d'avoir la décomposition en valeurs et vecteurs propres
	private EigenDecomposition a;
	private double[] participation;
	// ces deux tableaux eigen.. contiennent les valeurs et vecteurs propres par
	// ordre decroissant
	private double[] eigenvals;
	private double[][] eigenvects;

	public COV_Corr_MAT(Normalize_mat normal) {
        my_norm_mat=normal;
		Mat_cov = Op_mat.prodMat(Op_mat.tr(my_norm_mat.get_mat()),my_norm_mat.get_mat());
		Mat_corr=Mat_cov;
		for (int i = 0; i < Mat_cov.length; i++) {
			for (int j = 0; j < Mat_cov[0].length; j++) {

				Mat_cov[i][j] = ((double) 1 / (Mat_cov.length)) * (Mat_cov[i][j]);

				Mat_corr[i][j]=Mat_cov[i][j]/(Gravity_STD.sd(my_norm_mat.get_mat())[j]*Gravity_STD.sd(my_norm_mat.get_mat())[i]);

			}
		}
	}

	public double[][] get_cov() {
		return Mat_cov;
	}
	public double[][] get_corr() {
		return Mat_corr;
	}


	public void My_eig() {
		RealMatrix mat = new Array2DRowRealMatrix(Mat_cov);
		a = new EigenDecomposition(mat);
		eigenvals = new double[Mat_cov.length];
		eigenvects = new double[Mat_cov.length][Mat_cov.length];
		eigenvals = a.getRealEigenvalues();

		for (int i = 0; i < Mat_cov.length; i++) {
			// Ici  je dois impérativement transformer le RealMatrix en Array

			eigenvects[i] = a.getEigenvector(i).toArray();
		}
		// Ces deux variables déclarés sont utilisés pour le tri
		double tmp;

		double[] tmp_vect;

		// Je dois trier des vecteurs propres et valeurs propres en meme tps

		for (int i = 0; i < Mat_cov.length; i++) {
			for (int j = i + 1; j < Mat_cov.length - 1; j++) {

				if (eigenvals[i] < eigenvals[j]) {
					tmp = eigenvals[i];

					eigenvals[i] = eigenvals[j];

					eigenvals[j] = tmp;

					tmp_vect = eigenvects[i];
					eigenvects[i] = eigenvects[j];
					eigenvects[j] = tmp_vect;
				                                 }

			}
		}
		// Pour le Calcul des participations

		double sum = get_eigenval()[0] + get_eigenval()[1] + get_eigenval()[2];
		participation = new double[Mat_cov.length];
		for (int i = 0; i < Mat_cov.length; i++) {
			participation[i] = (get_eigenval()[i] / sum) * 100;
		}
		
	}

	// mtn tt les vecteurs et valeurs propres sont triés par ordre décroissant
	// suivant les valeurs propres
	public double[] get_eigenval() {
		return eigenvals;
	}

	public double[][] get_eigenvect() {
		return eigenvects;
	}
// Ici j'utilise un vecteur contenant les participations des composantes dans l'information
	public double[] get_particip() {
		return participation;

	}
}
