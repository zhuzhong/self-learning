package com.datastruct.chapter4;

public class AvlTreeImpl<T> {

	private static class AvlNode<T> {
		public T element;
		public AvlNode<T> left;
		public AvlNode<T> right;
		public int height;

		public AvlNode(T element, AvlNode<T> lt, AvlNode<T> rt) {
			this.element = element;
			this.left = lt;
			this.right = rt;
		}

		public AvlNode(T element) {
			this(element, null, null);
		}

	}

	private int height(AvlNode<T> t) {
		return t == null ? -1 : t.height;
	}

	public AvlNode<T> insert(T x, AvlNode<T> t) {
		if (t == null) {
			return new AvlNode<T>(x);
		}
		int com = compare(x, t.element);
		if (com < 0) {
			t.left = insert(x, t.left);
			if (height(t.left) - height(t.right) == 2) {
				if (compare(x, t.left.element) < 0) {
					t = rotateWithLeft(t);// 左左的形式，需要单次旋转
				} else {
					t = doubleWithLeftChild(t);// 左右的形式，需要2次旋转
				}
			}
		} else if (com > 0) {
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2) {
				if (compare(x, t.right.element) > 0) {
					t = rotateWithRight(t);// 右右的形式，只需要单次旋转
				} else {
					t = doubleWithRightChild(t); // 右左的形式，需要2次旋转
				}
			}
		} else {

		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	private AvlNode<T> doubleWithRightChild(AvlNode<T> t) {
		
		return null;
	}

	private AvlNode<T> rotateWithRight(AvlNode<T> k2) {
		AvlNode<T> k1=k2.right;
		k2.right=k1.left;
		k1.left=k2;	
		k2.height=Math.max(height(k2.left), height(k2.right))+1;
		k1.height=Math.max(height(k1.left), height(k1.right))+1;
		return k1;
	}

	private AvlNode<T> doubleWithLeftChild(AvlNode<T> t) {
		
		return null;
	}

	private AvlNode<T> rotateWithLeft(AvlNode<T> k2) {
		AvlNode<T> k1=k2.left;
		k2.left=k1.right;
		k1.right=k2;
		k2.height=Math.max(height(k2.left), height(k2.right))+1;
		k1.height=Math.max(height(k1.left), height(k1.right))+1;
		return k1;
	}

	private int compare(T x, T element) {
		// TODO Auto-generated method stub
		return 0;
	}
}
