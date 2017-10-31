package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;

public class Part8 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		Map<String, Integer> m = new HashMap<>();

		for (String line = r.readLine(); line != null; line = r.readLine()) {
				if(m.containsKey(line)){
					m.put(line, m.get(line) + 1);
				}
				else{
					m.put(line, 1);
				}
			}

			SortedSet<String> s = new TreeSet<>(new Comparator<String>(){
				public int compare(String m1, String m2){

					if(m.get(m2) - m.get(m1) == 0){
						return m1.compareTo(m2);
					}

					else{
						return(m.get(m2) - m.get(m1));
					}
				}
			});

			for(String key : m.keySet()){
				s.add(key);
			}

			while(!s.isEmpty()){
				w.println(s.first());
				s.remove(s.first());
			}

	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
