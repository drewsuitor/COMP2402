package comp2402a5;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
* An unfinished implementation of an Countdown tree (for exercises)
* @author morin
*
* @param <T>
*/
public class CountdownTree<T> extends
BinarySearchTree<CountdownTree.Node<T>, T> implements SSet<T> {

	// countdown delay factor
	double d;

	public static class Node<T> extends BSTNode<Node<T>,T> {
		int timer;  // the height of the node
	}

	public CountdownTree(double d) {
		this.d = d;
		sampleNode = new Node<T>();
		c = new DefaultComparator<T>();
	}

	public int height(Node<T> u) {
		return (u == null) ? 0 : u.timer;
	}

	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.timer = (int)Math.ceil(d);
		u.x = x;
		if (super.add(u)) {
			// add some code here
			for(Node<T> w = u; w != nil; w = w.parent){
				w.timer = Math.max(height(w.left), height(w.right)) + 1;
			}
			explode(u);
			return true;
		}
		return false;
	}

	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		// add some code here (we just removed u from the tree
		for(Node<T> z = u; z != nil; z = z.parent){
			z.timer = Math.max(height(z.left), height(z.right)) + 1;
		}
		explode(w);
	}

	protected void explode(Node<T> u) {
		while(u != nil){
			int d = compare(u.left, u.right);
			if(d > 1){
				if(compare(u.left.right, u.left.left) >= 1)
					rotateLeft(u.left);
				rotateRight(u);
			}
			else if(d < -1){
				if(compare(u.right.left, u.right.right) >=1)
					rotateRight(u.right);
				rotateLeft(u);
			}
			u = u.parent;
		}
		// Write this code to explode u
		// Make sure to update u.parent and/or r (the tree root) as appropriate
	}

	public int compare(Node<T> u, Node<T> i){
		return height(u) - height(i);
	}

	public void rotateLeft(Node<T> u){
		super.rotateLeft(u);
		for (Node<T> w = u; w != nil; w = w.parent) {
				w.timer = Math.max(height(w.left), height(w.right)) + 1;
			}
	}

	public void rotateRight(Node<T> u) {
		super.rotateRight(u);
		for (Node<T> w = u; w != nil; w = w.parent) {
				w.timer = Math.max(height(w.left), height(w.right)) + 1;
			}
	}

	// Here is some test code you can use
	public static void main(String[] args) {
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 1000);
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 1000);
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 1000);

		java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
		ell.add(new java.util.TreeSet<Integer>());
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
		Testum.sortedSetSpeedTests(ell, 1000000);
	}
}
