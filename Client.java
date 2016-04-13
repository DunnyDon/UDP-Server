package UDPServer;
//Conor Donohue 13404068
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String args[]) throws Exception    { 
		BufferedReader NameFromUser = new BufferedReader(new InputStreamReader(System.in));//open a input stream from the console
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];//Data sent and received accross sockets are in the form of bytes.
		InetAddress IP = InetAddress.getByName("127.0.0.1");//we are only connecting to this laptop hence the reason localhost is the IP address
		
		String Name = NameFromUser.readLine();//read in the name typed by the user
		sendData = Name.getBytes();//convert it to bytes to send accross the socket
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, 4444);//send the data using datagram packets
		clientSocket.send(sendPacket);//using the socket send the datagram packet
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);//create a new datagram packet to receive the data sent back by the socket 
		clientSocket.receive(receivePacket);//receive the data sent back by the server
		String modifiedSentence = new String(receivePacket.getData());//convert it from bytes
		int i=0;
		System.out.print("FROM SERVER:");
		while(modifiedSentence.charAt(i)!=0){
			System.out.print(modifiedSentence.charAt(i));//just print out exactly what was typed in
			i++;//by doing this we are not printing out the rest of the array which would be default values of chars 
			//Extra bits are set to the default value due to the size of the byte arrays
		}
		//close the socket once the transmission is complete	
		clientSocket.close();
		}
}
