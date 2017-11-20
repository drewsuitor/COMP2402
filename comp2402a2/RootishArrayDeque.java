package comp2402a2;

import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 */
public class RootishArrayDeque<T> extends AbstractList<T> {
	/**
	 * You decide on the instance variables you need.
	 */
	 public RootishArrayStack<T> front;
	 public RootishArrayStack<T> back;
	 public int n;
	 public int f;										//numbers or items in front
	 public int b;										//number of items in back
	 public int half;
	 public Class<T> t2;


	public RootishArrayDeque(Class<T> t) {
		// Put your own code here
		front = new RootishArrayStack<T>(t);
		back = new RootishArrayStack<T>(t);
		f = 0;
		b = 0;
		n = 0;
		t2 = t;
		//throw new UnsupportedOperationException("Constructor not yet implemented");
	}

	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		T y;
		if(i < f){
			y = front.get(f - i - 1);
		}
		else{
			y = back.get(i - f);
		}
		return y;
		// Put your own code here instead of throwing this exception
		//throw new UnsupportedOperationException("get(i) not yet implemented");
	}

	public T set(int i, T x) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		//throw new UnsupportedOperationException("set(i,x) not yet implemented");
		T y;
		if(i < f){
			y = front.set(f - i - 1, x);
		}
		else{
			y = back.set(i - f, x);
		}
		return y;
	}

	public void add(int i, T x) {
		if (i < 0 || i > size()) throw new IndexOutOfBoundsException();
		//half = (int)Math.round(n/2);
		if(i < f || i == 0){
			front.add(f - i, x);
			f++;
			n++;
		}
		else{
			back.add(i - f, x);
			b++;
			n++;
		}
		balance();
	}

	public T remove(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		T y;
		if(i < f){
			y = front.remove(f - i - 1);
			f--;
			n--;
		}
		else{
			y = back.remove(i - f);
			b--;
			n--;
		}
		balance();
		return y;
	}

	public int size() {
		return n;
		// Put your own code here
		//throw new UnsupportedOperationException("size() not yet implemented");
	}

	private void balance(){
		if(3*front.size() < back.size()){
			int s = n/2 - front.size();
			RootishArrayStack<T> l1 = new RootishArrayStack<T>(t2);
			RootishArrayStack<T> l2 = new RootishArrayStack<T>(t2);
			l1.addAll(back.subList(0, s));
			Collections.reverse(l1);
			l1.addAll(front);
			l2.addAll(back.subList(s, back.size()));
			front = l1;
			back = l2;
			f = l1.size();
			b = l2.size();
		}
		else if (3*back.size() < front.size()){
			int s = front.size() - n/2;
			RootishArrayStack<T> l1 = new RootishArrayStack<T>(t2);
			RootishArrayStack<T> l2 = new RootishArrayStack<T>(t2);
			l1.addAll(front.subList(s, front.size()));
			l2.addAll(front.subList(0, s));
			Collections.reverse(l2);
			l2.addAll(back);
			front = l1;
			back = l2;
			f = l1.size();
			b = l2.size();
		}
	}

	public static void main(String[] args) {
		//List<Integer> rad = new ArrayDeque<Integer>(Integer.class);
		List<Integer> rad = new RootishArrayDeque<Integer>(Integer.class);
		int K = 1000000;
		Stopwatch s = new Stopwatch();
		System.out.print("Appending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Prepending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(0, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the back...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(rad.size()-1);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the front...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(0);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");
	}



}
