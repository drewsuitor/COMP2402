package comp2402a4;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}

	public void inorderDraw() {
		assignLevels();
		GeometricTreeNode u = r;
		GeometricTreeNode p;
		p = u;
		int count = 0;

		while(u != null){
			if(p == u.right){
				p = u;
				u = u.parent;
			}
			else if(u.left != null && p != u.left){
				p = u;
				u = u.left;
			}
			else if(u.right != null){
				u.position.x = count;
				count ++;
				p = u;
				u = u.right;
			}
			else{
				u.position.x = count;
				count ++;
				p = u;
				u = u.parent;
			}

		}
	}


	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}


	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		GeometricTreeNode d = r;
		d.position.x = -1;

		Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
		q.add(r);

		while(!q.isEmpty()){
			GeometricTreeNode u = q.remove();

			if(d.position.y == u.position.y){
				u.position.x = d.position.x + 1;
				d = u;
			}

			else{
				u.position.x = 0;
				d = u;
			}

			if(u.left != nil){
				q.add(u.left);
			}

			if(u.right != nil){
				q.add(u.right);
			}
		}
	}


	public void balancedDraw() {
		calculateSizes(r);
		int x = 0;
		int y = 0;
		GeometricTreeNode current = firstNode();
		while(current != nil){
			current.size = currentSize(current);
			current = nextNode2(current);
		}
		current = r;
		do{
			while(current.left != nil || current.right != nil){
				if(current.left != nil && current.right != nil){
					current = smallerChild(current);
					y++;
				}
				else{
					if(current.left != nil){
						current = current.left;
					}
					else{
						current = current.right;
						}
						x++;
					}
					setPosition(current, x, y);
				}
				do{
					current = current.parent;
				}while(current != nil && ((current.left == nil || current.right == nil) || largerChild(current).position.x > 0));
			if(current  == nil){
				break;
			}
			y = current.position.y;
			current = largerChild(current);
			current.position.y = y;
			x++;
			current.position.x = x;
		}while(true);
	}

	private void calculateSizes(GeometricTreeNode u){
		if(u == null){
			return;
		}
		u.position.x = 0;
		calculateSizes(u.left);
		calculateSizes(u.right);
	}

	private void setPosition(GeometricTreeNode u, int x, int y){
		u.position.x = x;
		u.position.y = y;
	}

	private int currentSize(GeometricTreeNode u){
		if(u.left != nil && u.right != nil){
			return(u.left.size + u.right.size + 1);
		}

		if(u.left != nil){
			return(u.left.size  + 1);
		}

		else if(u.right != nil){
			return(u.right.size + 1);
		}

		return 1;
	}

	private GeometricTreeNode nextNode2(GeometricTreeNode u){
		if(u.parent != nil && u.parent.left == u){
			u = u.parent;
			if(u.right != nil){
				u = u.right;
				while(u.left != nil || u.right != nil){
					while(u.left != nil){
						u = u.left;
					}
					if(u.right != nil){
						u = u.right;
					}
				}
			}
		}
		else{
			u = u.parent;
		}
		return u;
	}

	private GeometricTreeNode smallerChild(GeometricTreeNode u){
		if(u.left.size < u.right.size){
			return u.left;
		}
		else{
			return u.right;
		}
	}

	private GeometricTreeNode largerChild(GeometricTreeNode u){
		if(u.left.size < u.right.size){
			return u.right;
		}
		else{
			return u.left;
		}
	}

	protected void assignLevels() {
		assignLevels(r, 0);
	}

	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}

	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}

}
