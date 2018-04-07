import java.io.*;
import java.net.*;
import java.util.*;


class  Client 
{


	public static void main(String[] args)throws IOException,InterruptedException
	{
		System.out.println("Client Started..");
		Socket sock = new Socket("127.0.0.1",12345);
		System.out.println("Client connected..");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		InputStream in =sock.getInputStream();
		BufferedReader rec = new BufferedReader(new InputStreamReader(in));
		OutputStream out = sock.getOutputStream();
		System.out.println("Enter the String: ");
		String substitutionInput = buf.readLine();
		//System.out.println("Enter the length of the string:");
		int n = substitutionInput.length();
		//int n = Integer.parseInt(size);
			// Substitution encryption
		StringBuffer substitutionOutput = new StringBuffer();
		for(int i=0 ; i<substitutionInput.length() ; i++) 
		{
			char c = substitutionInput.charAt(i);
			substitutionOutput.append((char) (c+5));
		}
		System.out.println("\nSubstituted text:");
		System.out.println(substitutionOutput);

		// Transposition encryption
		String transpositionInput = substitutionOutput.toString();
		int modulus;
		if((modulus = transpositionInput.length()%n) != 0) 
		{
			modulus = n-modulus;
		// ‘modulus’ is now the number of blanks/padding (X) to be appended
			for( ; modulus!=0 ; modulus--) 
			{
				transpositionInput += "/";
			}
		}
		StringBuffer transpositionOutput = new StringBuffer();
		System.out.println("\nTransposition Matrix:");
		for(int i=0 ; i<n ; i++) 
		{
			for(int j=0 ; j<transpositionInput.length()/n ; j++) 
			{
				char c = transpositionInput.charAt(i+(j*n));
				System.out.print(c+"\t");
				transpositionOutput.append(c);
			}
			//System.out.println();
		}
		System.out.println("\nFinal encrypted text:");
		System.out.println(transpositionOutput);
		
		PrintWriter pw = new PrintWriter(out);
		pw.println(transpositionOutput);
		pw.flush();
		pw.println(n);
		pw.flush();
		String fin = rec.readLine();
		//System.out.println(fin);
		
		sock.close();
				
	
	}


}
