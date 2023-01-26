package sample;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.math3.linear.*;

public class CSVREAD {

	private int ROWS;
	private int COL;
	private double[][] numArray;

	public CSVREAD(int r, int c) {
		ROWS = r;
		COL = c;
		numArray = new double[ROWS][COL];
	}
	// public static void main(String[] args) throws FileNotFoundException {
	// readFile();
	// }

	public double[][] readFile() throws FileNotFoundException {

		Scanner sc = new Scanner(choseTextFile());
		while (sc.hasNextLine()) {
			for (int i = 0; i < numArray.length; i++) {
				String[] line = sc.nextLine().trim().split(",");
				for (int j = 0; j < line.length; j++) {
					numArray[i][j] = Double.parseDouble(line[j]);
				}
			}

		}
		// System.out.println(Arrays.deepToString(numArray));
		return numArray;
	}

// cette partie de code me permet de selectionner le fichier à déposer 
	private static File choseTextFile() {
		FileDialog dialog = new FileDialog((Frame) null, "Select File To Open");
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		File[] file = dialog.getFiles();
		return file[0];
	}

}