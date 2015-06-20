import  java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import  java.rmi.server.*;
import  java.net.*;
import  java.io.*;
import java.util.*;
public class MatrixClient
{
	public static void main(String args[]) throws RemoteException, NotBoundException
	{
		
		int i,j,n;	
		Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
		//String url  = "rmi://localhost/"; 192.168.1.28

		try
		{
		       Matrix rmob=(Matrix) registry.lookup(Constant.RMI_ID);
				//Matrix rmob=(Matrix) Naming.lookup(url+"MatrixOP");
	   	       Scanner sc=new Scanner(System.in);
	 		  System.out.println("Wprowadz wymiar macierzy");
			  n=sc.nextInt();
			  double a[][]=new double[n][n];
			  double x[][]=new double[n][n];
			  double z[][]=new double[n][n];
		       System.out.println("Wprowadz macierz:");
		       for(i=0;i<n;i++)
		        {
		        for(j=0;j<n;j++)   
		         {
		       a[i][j]=sc.nextInt();	
		        }
		      }
		       x=rmob.trans(a,n);	
		    if(x!=null){
		       System.out.println(" wynik="  );
		       for(i = 0; i < n; i++)
			    {
			      for(j = 0; j < n; j++)
			    	  System.out.print(x[i][j] + " ");  
			      System.out.println();  
			    }      
		      }
		    else
		    	System.out.println("Dzielnik Zero");
		       
		 }	
		catch(RemoteException e){System.out.println("error");}
		catch(Exception p){}
		
	}
}
			