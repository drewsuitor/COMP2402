package comp2402a2;

import java.util.AbstractList;

public class MyArrayDeque<T> extends AbstractList<T> {
	protected Factory<T> f;
	protected T[] a;
	protected int j;
	protected int n;

	protected void resize() {
		T[] b = f.newArray(Math.max(2*n,1));
		for (int k = 0; k < n; k++)
			b[k] = a[(j + k) % a.length];
		a = b;
		j = 0;
	}

	public MyArrayDeque(Class<T> t) {
		f = new Factory<T>(t);
		a = f.newArray(1);
		n = 0;
		j = 0;
	}

	public int size() {
		return n;
	}

	public T get(int i) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		return a[(j + i) % a.length];
	}

	public T set(int i, T x) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		T y = a[(j + i) % a.length];
		a[(j + i) % a.length] = x;
		return y;
	}

	public void add(int i, T x) {
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		if (n+1 > a.length){
      resize();
    }
		if (i < n/2) {
			j = (j == 0) ? a.length - 1 : j - 1;
			for (int k = 0; k <= i-1; k++)
				a[(j+k)& a.length] = a[(j + k + 1) % a.length];
		} else {
			for (int k = n; k > i; k--)
				a[(j + k) % a.length] = a[(j + k - 1) % a.length];
		}
		a[(j + i) % a.length] = x;
		n++;
	}

	public T remove(int i) {
		if (i < 0 || i > n - 1)	throw new IndexOutOfBoundsException();
		T x = a[(j + i) % a.length];
		if (i < n/2) {
			for (int k = i; k > 0; k--)
				a[(j + k) % a.length] = a[(j + k - 1) % a.length];
			j = (j + 1) % a.length;
		} else {
			for (int k = i; k < n-1; k++)
				a[(j + k) % a.length] = a[(j + k + 1) % a.length];
		}
		n--;
		if (3*n < a.length) resize();
		return x;
	}
}
