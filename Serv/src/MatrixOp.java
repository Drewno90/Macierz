import  java.rmi.*;
import  java.rmi.server.*;
public class MatrixOp extends UnicastRemoteObject implements Matrix{
	/**
	 * 
	 */
	static double eps = 1e-12;

  public boolean ludist(int n, double [][] A)
	 {
	   WorkerThread [] Threads = new WorkerThread[n];
	   int i,j,k;
		int num_threads=n;
		int rows = n / num_threads;
		int start = 0;
		int end = rows;
	   for (int t = 0; t < num_threads; t++)
		{

           Threads[t] = new WorkerThread(start,end,n,A);
           Threads[t].start();
			start = end;
			end = start + rows;

				try {
					Threads[t].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	   
	   return true;
	 }

	 // Funkcja wyznacza wektor X na podstawie A i X[i]
	 //------------------------------------------------
	 public boolean lusolve(int k, int n, double [][] A, double [][] X)
	 {
	   int    i,j;
	   double s;

	   for(i = 1; i < n; i++)
	   {
	     s = 0;

	     for(j = 0; j < i; j++) s += A[i][j] * X[j][k];

	     X[i][k] -= s;
	   }

	   if(Math.abs(A[n-1][n-1]) < eps) return false;

	   X[n-1][k] /= A[n-1][n-1];

	   for(i = n - 2; i >= 0; i--)
	   {
	     s = 0;

	     for(j = i + 1; j < n; j++) s += A[i][j] * X[j][k];

	     if(Math.abs(A[i][i]) < eps) return false;

	     X[i][k] = (X[i][k] - s) / A[i][i];
	   }

	   return true;
	 }
	private static final long serialVersionUID = 1L;
	int i,j,k;
	int c[][]=new int[2][2];
	public MatrixOp() throws RemoteException
	{
	   super();
	}

	public double[][] trans(double A[][], int n) throws RemoteException
	{
		  double[][]X = new double[n][n];
		System.out.println("Macierz Odwrocona:");
		boolean ok;
	       if(ludist(n,A))
			  {
			  
			  // tworzymy macierz jednostkową w X    

			    for(i = 0; i < n; i++)
			    {
			      for(j = 0; j < n; j++) X[i][j] = 0;
			      X[i][i] = 1;
			    }

			  // Wyznaczamy kolejne kolumny macierzy odwrotnej w X

			    ok = true;
			    for(i = 0; i < n; i++)
			      if(! lusolve(i,n,A,X))
			      {
			        ok = false;
			        break;
			      }
			  }
			  else ok = false;
		  // jeśli obliczenia się powiodły, wyświetlamy X

		  if(ok)
		  {
		    for(i = 0; i < n; i++)
		    {
		      for(j = 0; j < n; j++)
		    	  System.out.print(X[i][j] + " ");  
		      System.out.println();  
		    }  
		    return X;
		  }
		  else System.out.println("Dzielnik Zero");
		  return null;
	}
}

