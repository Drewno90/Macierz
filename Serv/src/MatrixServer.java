import  java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import  java.net.*;
public class MatrixServer 
{
	public static void main(String args[])
	{
		try
		{
		   MatrixOp op=new MatrixOp();
			Registry registry =	LocateRegistry.createRegistry(Constant.RMI_PORT);
	    	registry.bind(Constant.RMI_ID, op);
		  // Naming.rebind("MatrixOP",op);
		    
		    System.out.println("Server is waiting");
		    
		}
		catch(RemoteException e){}
		catch(Exception a){}
	}
}
