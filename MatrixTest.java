
//Rohit Kulkarni, pa3, 1654070, rnkulkar
//Matrix tester that tests all of the methods in the Matrix ADT
public class MatrixTest {
	public static void main(String[] args)
	{
		int n = 6;
		Matrix M = new Matrix(n);
		//change Entry Test---------------------------------
		System.out.println("Change Entry Test------------------");
		for(int i = 1; i < n + 1; i++)
		{
			M.changeEntry(i, i, -1.0);
		}
		System.out.println(M.toString());
		//scalarMult test-------------------------------------
		System.out.println("Scalar Multiplication Test---------");
		M.scalarMult(3.0);
		System.out.println(M.toString());
		//copy() test------------------------------------------
		System.out.println("Copy Method Test-------------------");
		Matrix G = M.copy();
		System.out.println(G.toString());
		System.out.println("Is it the same as the original?");
		System.out.println("");
		System.out.println(M.toString());
		//add() test------------------------------------------
		System.out.println("Add Method Test----------------------");
		Matrix addTest = new Matrix(n);
		for(int i = 1; i < n + 1; i++)
		{
			addTest.changeEntry(i, 4, 4.0);
		}
		Matrix sum = M.add(addTest);
		System.out.println(sum.toString());
		//sub() test------------------------------------------
		System.out.println("Sub Method Test----------------------");
		Matrix subTest = new Matrix(n);
		for(int i = 1; i < n + 1; i++)
		{
			subTest.changeEntry(i, 4, 4.0);
		}
		Matrix sub = M.sub(addTest);
		System.out.println(sub.toString());
		//transpose() test------------------------------------
		System.out.println("Transpose Method Test------------------");
		Matrix test = new Matrix(n);
		for(int i = 1; i < n + 1; i++)
		{
			test.changeEntry(1, i, 1.0);
		}
		for(int i = 1; i < n + 1; i++)
		{
			test.changeEntry(2, i, 1.0);
		}
		System.out.println("Original: ");
		System.out.println(test.toString());
		System.out.println("Transpose: ");
		Matrix trans = test.transpose();
		System.out.println(trans.toString());
		Matrix trans2 = trans.transpose();
		System.out.println("Double Transpose (original): ");
		System.out.println(trans2.toString());
		//mult() test------------------------------------------
		System.out.println("Multiply Method Test--------------------");
		Matrix factor1 = new Matrix(3);
		Matrix factor2 = new Matrix(n);
		for(int i = 1; i < 3+1; i++)
		{
			factor1.changeEntry(1,i, i);
		}
		for(int i = 1; i < 3+1; i++)
		{
			factor1.changeEntry(2,i, i + 3);
		}
		for(int i = 1; i < 3+1; i++)
		{
			factor1.changeEntry(3,i, i + 6);
		}
		
		Matrix factorTrans = factor1.transpose();
		System.out.println(factor1.toString());
		System.out.println(factorTrans.toString());
		Matrix product = factor1.mult(factor1);
		System.out.println(product.toString());
//		Matrix A;
//		Matrix B;
//		A = new Matrix(10);
//        B = new Matrix(15);
//        A.changeEntry(1, 1, 1);
//        B.changeEntry(1, 1, 1);
//        System.out.println(A.equals(B));
//        B = new Matrix(10);
//        A.changeEntry(1, 1, 1);
//        A.changeEntry(1, 3, 1);
//        B.changeEntry(1, 1, 1);
//        B.changeEntry(1, 3, 1);
//        System.out.println(!A.equals(B));
//        A.changeEntry(1, 3, 0);
//        System.out.println(A.equals(B));
//        A.changeEntry(1, 1, 0);
//        B.makeZero();
//        A.changeEntry(10, 10, 10);
//        B.changeEntry(10, 10, 10);
//        System.out.println(!A.equals(B));
//        A = new Matrix(100);
//        B = new Matrix(100);
//        int valcount = 1;
//        for (int j = 1; j <= 100; j++) {
//          for (int k = 1; k <= 100; k++) {
//            // hint: this is 1-10000 left-to-right row-by-row
//            A.changeEntry(j, k, valcount++);
//          }
//          B.changeEntry(j, j, 1); // hint: this is the identity matrix
//        }
//        Matrix C;
//        C = A.scalarMult(2);
//        System.out.println(!C.equals(A.add(A)));
//        C = A.scalarMult(-2);
//        System.out.println(!C.equals(A.sub(A).sub(A).sub(A)));
//        C = A.mult(B);
//        System.out.println(!C.equals(A));
	}
}

