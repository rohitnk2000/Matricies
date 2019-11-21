
//Rohit Kulkarni, pa3, 1654070, 5/16/19, rnkulkar
//Matrix.java, writes the Matrix ADT for pa3

public class Matrix {
	private class Entry {
		int position;
		double value;

		public Entry(int pos, double val) {
			position = pos;
			value = val;
		}

		// toString(): overrides Object's toString() method
		public String toString() {
			return "(" + position + ", " + value + ")";
		}

		// equals(): overrides Object's equals() method
		// Returns true if two Entries col and val are equal.
	      public boolean equals(Object x) {
	         boolean eq = false;
	         Entry that;
	         if(x instanceof Entry) {
	            that = (Entry) x;
	            eq = (this.position == that.position && this.value == that.value);
	         }
	         return eq;
	      }
	   }
	

	List[] matrixArray = null;
	int n;

	// Constructor---------------------------
	public Matrix(int n) {
		this.n = n;
		matrixArray = new List[n + 1];
		for (int i = 1; i < n + 1; i++) {
			matrixArray[i] = new List();
		}

	}

	// Access functions------------------------
	// Returns n, the number of rows and columns of this Matrix
	public int getSize() {
		return n;
	}
	 // Returns the number of non-zero entries in this Matrix
	public int getNNZ() {
		int count = 0;
		for (int i = 1; i < n + 1; i++) {
			matrixArray[i].moveFront();
			while(matrixArray[i].index() >= 0)
			{
				Entry E = (Entry) matrixArray[i].get();
				if(E.value != 0)
				{
					count++;
				}
				matrixArray[i].moveNext();
			}
		}
		return count;

	}
	

	// Manipulation procedures----------------------
	
	// sets this Matrix to the zero state 
	public void makeZero() {
		for (int i = 1; i < n; i++) {
			matrixArray[i] = new List();
		}
	}

	// returns a new Matrix having the same entries as this Matrix 
	public Matrix copy() {
		Matrix copy = new Matrix(this.getSize());
		for (int i = 1; i < copy.getSize() + 1; i++) {
			matrixArray[i].moveFront();
			while (matrixArray[i].index() >= 0) {
				copy.matrixArray[i].append(matrixArray[i].get());
				matrixArray[i].moveNext();

			}
		}
		return copy;
	}

	// changes ith row, jth column of this Matrix to x
	 // pre: 1<=i<=getSize(), 1<=j<=getSize()
	public void changeEntry(int i, int j, double x) {
		boolean contains = false;

		int c = i;
		matrixArray[c].moveFront();
		while (matrixArray[c].index() >= 0) {
			Entry curr = (Entry) matrixArray[c].get();
			if (curr.position == j) {
				contains = true;
				if (x == 0) {
					if(matrixArray[c].length() == 1)
					{
						matrixArray[c].clear();
					}
					else {
						matrixArray[c].delete();
					}
				} else if (x != 0) {
					curr.value = x;
				}
				return;
			}
			matrixArray[c].moveNext();
		}
		if (contains == false && x != 0) {
			if (matrixArray[c].length() == 0) {
				matrixArray[c].append(new Entry(j, x));
				
			} 
			else {
				matrixArray[c].moveFront();
				while (matrixArray[c].index() >= 0) {
					Entry E = (Entry) matrixArray[c].get();
					if (j < E.position) {
						matrixArray[c].insertBefore(new Entry(j, x));
						return;
					}
					matrixArray[c].moveNext();
				}
				matrixArray[c].append(new Entry(j, x));
			}
		}
	}
	
	// returns a new Matrix that is the scalar product of this Matrix with x
	public Matrix scalarMult(double x) {
		Matrix product = new Matrix(n);
		for (int i = 1; i < getSize() + 1; i++) {
			matrixArray[i].moveFront();
			while (matrixArray[i].index() >= 0) {
				Entry E = (Entry) matrixArray[i].get();
				product.changeEntry(i, E.position, E.value * x);
				matrixArray[i].moveNext();
			}
		}
		return product;
	}

	private List listAdd(List A, List B, List S) {
		A.moveFront();
		B.moveFront();
		while (A.index() >= 0 && B.index() >= 0) {
			Entry eA = (Entry) A.get();
			Entry eB = (Entry) B.get();
			if (eA.position == eB.position) 
			{
				S.append(new Entry(eA.position, eA.value + eB.value));
				A.moveNext();
				B.moveNext();
			} 
			else if (eA.position < eB.position) 
			{
				S.append(new Entry(eA.position, eA.value));
				A.moveNext();
			} else if (eA.position > eB.position) {
				S.append(new Entry(eB.position, eB.value));
				B.moveNext();
			}
		}
		if (A.index() >= 0) {
			while (A.index() >= 0) {
				Entry E = (Entry) A.get();
				S.append(new Entry(E.position, E.value));
				A.moveNext();
			}
		} else if (B.index() >= 0) {
			while (B.index() >= 0) {
				Entry E = (Entry) B.get();
				S.append(new Entry(E.position, E.value));
				B.moveNext();
			}
		}
		return S;

	}
	
	// returns a new Matrix that is the sum of this Matrix with M
	 // pre: getSize()==M.getSize()
	public Matrix add(Matrix M) {
		Matrix sum = new Matrix(this.n);
		
		if (M.getSize() != this.getSize()) {
			throw new RuntimeException("Trying to add matricies with different dimensions");
		}
		if(M.equals(this))
		{
			sum = M.scalarMult(2.0);
			return sum;
		}
		for (int i = 1; i < n + 1; i++)
		{
			List sumList = new List();
			sumList = listAdd(this.matrixArray[i], M.matrixArray[i], sum.matrixArray[i]);
			sum.matrixArray[i] = sumList;
		}
		return sum;
	}
	
	private List listSub(List A, List B, List S) {
		A.moveFront();
		B.moveFront();
		while (A.index() >= 0 && B.index() >= 0) {
			Entry eA = (Entry) A.get();
			Entry eB = (Entry) B.get();
			if (eA.position == eB.position) {
				if(eA.value - eB.value == 0)
				{
					A.moveNext();
					B.moveNext();
				}
				else
				{
					S.append(new Entry(eA.position, eA.value - eB.value));
					A.moveNext();
					B.moveNext();
				}
				
			} else if (eA.position < eB.position) {
				S.append(new Entry(eA.position, eA.value));
				A.moveNext();
			} else if (eA.position > eB.position) {
				S.append(new Entry(eB.position, -1 * eB.value));
				B.moveNext();
			}
		}
		if (A.index() >= 0) {
			while (A.index() >= 0) {
				Entry E = (Entry) A.get();
				S.append(new Entry(E.position, E.value));
				A.moveNext();
			}
		} else if (B.index() >= 0) {
			while (B.index() >= 0) {
				Entry E = (Entry) B.get();
				S.append(new Entry(E.position, -1 *E.value));
				B.moveNext();
			}
		}
		return S;

	}

	// returns a new Matrix that is the difference of this Matrix with M
	 // pre: getSize()==M.getSize()
	public Matrix sub(Matrix M) {
		Matrix sub = new Matrix(this.n);
		if (M.getSize() != this.getSize()) {
			throw new RuntimeException("Trying to subtract matricies with different dimensions");
		}
		for (int i = 1; i < n + 1; i++) {
			List subList = new List();
			subList = listSub(this.matrixArray[i], M.matrixArray[i], sub.matrixArray[i]);
			sub.matrixArray[i] = subList;
		}
		return sub;
	}

	// returns a new Matrix that is the transpose of this Matrix
	public Matrix transpose() {
		Matrix t = new Matrix(n);

		for (int i = 1; i < n + 1; i++) {
			List L = matrixArray[i];
			L.moveFront();
			while (L.index() >= 0) {
				Entry e = (Entry) L.get();
				t.changeEntry(e.position, i, e.value);
				L.moveNext();
			}
		}

		return t;
	}

	// overrides Object's toString() method
	public String toString() {
		String output = "";
		for (int i = 1; i < getSize() + 1; i++) {
			if(matrixArray[i].length() != 0)
			{
				output += i + ": ";
				matrixArray[i].moveFront();
				while (matrixArray[i].index() >= 0) {
					output += (Entry) (matrixArray[i].get()) + " ";
					matrixArray[i].moveNext();
				}
				output += "\n";
			}
			
		}
		return output;
	}
	
	// returns a new Matrix that is the product of this Matrix with M
	 // pre: getSize()==M.getSize()

	public Matrix mult(Matrix M)
	{
		if(M.getSize() != this.n)
		{
			throw new RuntimeException("Error: Multiply called on two arrays of different sizes.");
		}
		Matrix product = new Matrix(n);
		M = M.transpose();
		
		for(int i = 1; i < n + 1; i++)
		{
			for(int j = 1; j < n + 1; j++)
			{
				product.changeEntry(i, j, dot(M.matrixArray[j],matrixArray[i]));
			}
		}
		return product;
	}
	public static double dot(List A, List B)
	{
		A.moveFront();
		B.moveFront();
		double total = 0;
		while(A.index() >= 0 && B.index() >= 0)
		{
			Entry eA = (Entry) A.get();
			Entry eB = (Entry) B.get();
			if(eA.position == eB.position)
			{
				total += eB.value * eA.value;
				A.moveNext();
				B.moveNext();
			}
			else if(eA.position < eB.position)
			{
				A.moveNext();
			}
			else if(eA.position > eB.position)
			{
				B.moveNext();
			}
		}
		return total;
	}
	
	

}

