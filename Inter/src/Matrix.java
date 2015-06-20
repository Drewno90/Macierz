
import java.rmi.*;
public interface Matrix extends Remote
{
	public double[][] trans(double a[][], int n)throws RemoteException;
	public boolean lusolve(int k, int n, double [][] A, double [][] X)throws RemoteException;
	public boolean ludist(int n, double [][] A)throws RemoteException;
}