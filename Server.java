import java.util.*;
import java.io.*;
import java.net.*;

class Server 
{


	public static void main(String[] args)throws IOException
	{

		ServerSocket sSock = new ServerSocket(12345);
		System.out.println("Started..........");
		Socket sock = sSock.accept();
		System.out.println("got a client to transfer...");
	//	InputStream in =sock.getInputStream();
		
		DataInputStream din = new DataInputStream(sock.getInputStream());
		DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
	//	OutputStream out = sock.getOutputStream();
		BufferedReader buf = new BufferedReader(new InputStreamReader(din));
		String lineRead;
		StringBuffer transpositionOutput = new StringBuffer(buf.readLine());
	//	System.out.println("eljadlfjadlf");
		//String filename = buf.readLine());
		String size  = buf.readLine();
		int n =Integer.parseInt(size);
		// Transposition decryption
		
		n = transpositionOutput.length()/n;
		StringBuffer transpositionPlaintext = new StringBuffer();
		for(int i=0 ; i<n ; i++) 
		{
			for(int j=0 ; j<transpositionOutput.length()/n ; j++) 
			{
				char c = transpositionOutput.charAt(i+(j*n));
				transpositionPlaintext.append(c);
			}
		}
	
		// Substitution decryption
		StringBuffer plaintext = new StringBuffer();
		for(int i=0 ; i<transpositionPlaintext.length() ; i++) 
		{
			char c = transpositionPlaintext.charAt(i);
			plaintext.append((char) (c-5));
		}

		System.out.println("\nPlaintext:");
		System.out.println(plaintext);

		PrintWriter pw = new PrintWriter(dout);
		pw.println(plaintext);
		
		sock.close();
		//System.out.println("File Transfer Completed...");
		

		/*Socket s = ss.accept();

		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());

		String str="";
		
		while(!str.equals("stop")){

			str = din.readUTF();
			System.out.print(str);
			dout.writeUTF("received");
			dout.flush();
			

		}*/


	}






}
