package comp2402a3;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.LinkedList;

/**
 *  This is just a copy of A2Table, your job is to make it faster
 */
public class FasterTable<T> implements Table<T> {
	List<List<T>> tab;
	List<Integer> index;
	int nrows, ncols;

	public FasterTable(Class<T> t) {
		index = new ArrayList<Integer>();
		nrows = 0;
		ncols = 0;
		tab = new ArrayList<List<T>>();
	}

	public int rows() {
		return nrows;
	}

	public int cols() {
		return ncols;
	}

	public T get(int i, int j) {
		if (i < 0 || i > rows() - 1 || j < 0 || j > cols()-1)
			throw new IndexOutOfBoundsException();
		return tab.get(i).get(index.get(j));
	}

	public T set(int i, int j, T x) {
		if (i < 0 || i > rows() - 1 || j < 0 || j > cols()-1)
			throw new IndexOutOfBoundsException();
		return tab.get(i).set(index.get(index.get(j)), x);
	}

	public void addRow(int i) {
		if (i < 0 || i > rows()) throw new IndexOutOfBoundsException();
		nrows++;
		List<T> row = new ArrayList<T>();
		for (int j = 0; j < cols(); j++) row.add(null);
		tab.add(i, row);
	}

	public void removeRow(int i) {
		if (i < 0 || i > rows() - 1) throw new IndexOutOfBoundsException();
		nrows--;
		tab.remove(i);
	}

	public void addCol(int j) {
        // this method is too slow!
		if (j < 0 || j > cols()) throw new IndexOutOfBoundsException();
		ncols++;
		index.add(j, ncols-1);
        // this loop takes O( rows*(cols()-j) ) time
		for (int i = 0; i < rows(); i++){
			tab.get(i).add(null);    // O( cols()-j ) time
		}
	}

	public void removeCol(int j) {
        // this method is too slow!
		if (j < 0 || j > cols() - 1) throw new IndexOutOfBoundsException();
        // this loop takes O( rows*(cols()-j) ) time
		for (int i = 0; i < rows(); i++){
			tab.get(i).set(index.get(j), tab.get(i).get(ncols-1));  // O( cols()-j ) time
			tab.get(i).remove(ncols-1);
		}
		for(int i = 0; i < ncols; i++){
			if(index.get(i) == ncols-1){
				index.set(i, index.get(j));
			}
		}
		index.remove(j);
		ncols--;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows(); i++) {
			for (int j = 0; j < cols(); j++) {
				sb.append(String.valueOf(get(i, j)));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Tester.testTable(new FasterTable<Integer>(Integer.class));
		int r = 9, c = 6;
		FasterTable<Integer> table = new FasterTable<Integer>(Integer.class);

		for(int i = 0; i < c; i++){
			table.addCol(table.cols());
		}
		for(int i = 0; i < r; i++){
			table.addRow(table.rows());
		}
		for(int i = 0; i < c; i++){
			table.set(i, i, null);
			table.get(i, i);
		}

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				table.set(i, j, 1111*(j+1));
			}
		}

		table.addCol(3);
		table.addCol(5);
		table.removeCol(3);
		table.addRow(4);
		table.addRow(1);
		table.removeRow(3);

		for(int i = 0; i < table.rows(); i++){
			for(int j = 0; j < table.cols(); j++){
				//table.set(i, j, null);
				table.get(i, j);
			}
		}



		System.out.println(table.toString());
	}
}
