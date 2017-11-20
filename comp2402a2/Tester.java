package comp2402a2;
import java.util.List;


/**
 */
public class Tester {
	public static boolean testPart1(List<Integer> ell) {
		int K = 100000;
		Stopwatch s = new Stopwatch();

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.add(i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.add(0, i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.add(ell.size()/2, i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < K; i++){
			ell.set(i, 4);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < K; i++){
			ell.get(i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.remove(ell.size()-1);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.remove(0);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.remove(ell.size()/2);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		return true;
	}

	public static boolean testPart2(List<Integer> ell) {
		int K = 100000;

		Stopwatch s = new Stopwatch();

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.add(i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.add(0, i);
		}
		s.stop();
		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < K; i++){
			ell.set(i, 4);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < K; i++){
			ell.get(i);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.remove(ell.size()-1);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			ell.remove(0);
		}
		s.stop();

		if (s.elapsedSeconds() > 1) {
			return false;
		}

		return true;

	}

	public static boolean testPart3(AbstractTable<Integer> ell) {
		Stopwatch s = new Stopwatch();

		int nrows = 1000, ncols = 1000;
		Table<Integer> t = new Table<Integer>(Integer.class);

		System.out.flush();
		s.start();
		for (int i = 0; i < ncols; i++) {
			t.addCol(t.cols());
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 0; i < nrows; i++) {
			t.addRow(t.rows());
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 1; i <= nrows; i++) {
			t.set(i-1, (i-1)%t.cols(), 1111*i);
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}

		System.out.flush();
		s.start();
		for (int i = 1; i <= nrows; i++) {
			t.get(i-1, (i-1)%t.cols());
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < nrows; i++){
			for (int j = 0; j < ncols; j++){
				t.get(i, j);
			}
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}

		System.out.flush();
		s.start();
		for(int i = 0; i < nrows; i++){
			for (int j = 0; j < ncols; j++){
				t.set(i, j, 1234);
			}
		}
		s.stop();
		if(s.elapsedSeconds() > 1){
			return false;
		}


		return true;
	}
}
