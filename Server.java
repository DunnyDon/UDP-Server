package UDPServer;
//Conor Donohue 13404068
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	public static void main(String args[]) throws Exception       {
		DatagramSocket serverSocket = new DatagramSocket(4444);//create a socket to connect with the clients
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];//since data sent over sockets is usually in bytes create arrays to store the data in
		while(true){//by using a while true loop any new client can send a message even after the old one terminates and both their data will be stored
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);//create a datagram packet to receive the data sent from the client
			serverSocket.receive(receivePacket);
			String name = new String( receivePacket.getData());//convert the data from bytes
			String modifiedSentence = new String(receivePacket.getData());//convert it from bytes
			
			int i=0;
			System.out.print("FROM SERVER:");
			while(modifiedSentence.charAt(i)!=0){
				System.out.print(modifiedSentence.charAt(i));//just print out exactly what was typed in
				i++;//by doing this we are not printing out the rest of the array which would be default values of chars 
				//Extra bits are set to the default value due to the size of the byte arrays
				
			}
			System.out.println("");
			System.out.print("RECEIVED: ");//print out the data
			i=0;
			while(modifiedSentence.charAt(i)!=0){
				System.out.print(modifiedSentence.charAt(i));//just print out exactly what was typed in
				i++;//by doing this we are not printing out the rest of the array which would be default values of chars 
				//Extra bits are set to the default value due to the size of the byte arrays
				
			}
			InetAddress IP = receivePacket.getAddress();//find the IP address and port number of the data that was sent 
			int port = receivePacket.getPort();
			sendData = name.getBytes();//convert the data back to bytes and send it back to the original sender
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
			serverSocket.send(sendPacket);
			}
		}

}
