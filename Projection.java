package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

/*Dans la partie projection je suis capable de projeter mes données 
 ainsi que de representer la carte d'individus et le graphe d'eboulis (scree plot) 
 il faut deja créer avoir une instance d'objet COV pour s'en servir

 */
public class Projection {
	private double[][] proj_indiv;
	private double[][] base_projection;
	private Plot plt1, plt2,plt3;
	private COV_Corr_MAT CV;
// Constructeur pour calculer la projection et initialiser mes variables 
	
	
	public Projection(double my_data[][], COV_Corr_MAT cv) {
		CV = cv;

		base_projection = new double[2][my_data[0].length];

		proj_indiv = new double[my_data.length][2];

		base_projection[0] = cv.get_eigenvect()[0];

		base_projection[1] = cv.get_eigenvect()[1];

		proj_indiv = Op_mat.prodMat(my_data, Op_mat.tr(base_projection));
	}

	// getters 
	// get projection des individus cad facteurs

	public double[][] get_proj_indi() {
		return proj_indiv;
	}

// get : base de projection c'est une matrice

	public double[][] get_base() {
		return base_projection;
	}
	/*
	  Pour faire des plots j'ai utilisé la librairie matplotlib4j inspiré de python
	  pou saisir med données le dois les mettre dans des listes
	  
	 */
// plotting de la carte des individus 
	public void plot_carte1() throws IOException, PythonExecutionException {
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();


			plt1 = Plot.create();
			for (int i = 0; i < proj_indiv.length; i++) {
				x.add(proj_indiv[i][0]);
			}

			for (int i = 0; i < proj_indiv.length; i++) {
				y.add(proj_indiv[i][1]);
			}


			plt1.plot().add(x, y, "o").color("red").label("Individus");
			plt1.legend().loc("upper right");
			plt1.title("Carte des individus");

			plt1.ylabel("Participation 2:  " + String.format("%.2f", CV.get_particip()[1]) + "%");
			plt1.xlabel("Participation 1:  " + String.format("%.2f", CV.get_particip()[0]) + "%");

			plt1.show();

	}

	//plotting d'éboulis 
	public void plot_eboulis() throws IOException, PythonExecutionException {

		List<Double> z = new ArrayList<Double>();

		List<Double> w = new ArrayList<Double>();

		//w.add((double) 0);
		for (int i = 0; i <= CV.get_cov().length; i++) {
			z.add((double) i);
		}

		w.add((double) 0);

		for (int i = 0; i < CV.get_cov().length; i++) {
			w.add(CV.get_eigenval()[i] * 100);
		}


		plt2 = Plot.create();
		plt2.plot().add(z, w).color("green").label("%participation");
		plt2.legend().loc("upper right");
		plt2.title("Participation des variables ");

		plt2.ylabel("Variance %");
		plt2.xlabel("Nombre de variables");
		plt2.xlim(1, CV.get_cov().length + 1);
		plt2.ylim(0, 100);
		plt2.show();

	}
	public void plot_carte2() throws IOException, PythonExecutionException {
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();


		plt3 = Plot.create();


		for (int i = 0; i < proj_indiv.length; i++) {
				x.add(proj_indiv[i][0]);
			                                      }
		for (int i = 0; i < proj_indiv.length; i++) {
				y.add((double)0);
			}

			plt3.plot().add(x, y, "o").color("red").label("Individus");
			plt3.legend().loc("upper right");
			plt3.title("Carte des individus");
			plt3.xlabel("Participation 1:  " + String.format("%.2f", CV.get_particip()[0]) + "%");

		plt3.show();

	}
}
