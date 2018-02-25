/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.datagram.protocol;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.net.*;
import java.net.DatagramSocket;//Had to import both because of constructor errors
import java.net.DatagramPacket;//Had to import both because of constructor errors

/**
 *
 * @author Travis
 */
public class UserDatagramProtocol extends Thread{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer = new byte[256];
    private Random random;
    long startTime;
    long stopTime;
    long totalDuration;
    int clientSent;
    public UserDatagramProtocol(){
        random = new Random();
        Thread thread_2 = new UDClient();
        Thread thread_1 = new UDServer();
        thread_2.start();
        thread_1.start();
    }
    
    class UDServer extends Thread{
        public void run(){
            try{
                // Create a datagram socket, bound to the specific port 2000
                DatagramSocket socket = new DatagramSocket(2000);

                System.out.println ("Bound to local port " + socket.getLocalPort());

                    // Create a datagram packet, containing a maximum buffer of 256 bytes
                DatagramPacket packet = new DatagramPacket( new byte[256], 256 );

                // Receive a packet - remember by default this is a blocking operation
                socket.receive(packet);
                stopTime = System.currentTimeMillis();
                long totalDuration = stopTime - startTime;
                if(random.nextInt(2) == 1){
                    System.out.println ("Packet received at " + new Date( ));
                    // Display packet information
                    InetAddress remote_addr = packet.getAddress();
                    System.out.println ("Sender: " + remote_addr.getHostAddress( ) );
                    System.out.println ("from Port: " + packet.getPort());

                    // Display packet contents, by reading from byte array
                    ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData());

                    // Display only up to the length of the original UDP packet
                    for (int i=0; i < packet.getLength(); i++)  {
                            int data = bin.read();
                            if (data == -1) break;

                            else System.out.print ( (char) data);
                            
                    }
                    socket.close( );
                    
                }
                else{
                    socket.close();
                    System.out.println("Did not receive packet, now Hangry c(-_-c), "
                            + "continuing to next iterration.");
                }
            }
            catch (IOException e) 	{
                    System.out.println ("Error - " + e);
            }
        }
    }    
    //Im doing this for you trent
    class UDClient extends Thread{
        //use localhost to experiment on a standalone computer
        public void run(){
        String hostname="localhost";    String message = "HELLO USING UDP!";
            try {
		// Create a datagram socket, look for the first available port
		DatagramSocket socket = new DatagramSocket();

		System.out.println ("Using local port: " + socket.getLocalPort());
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                PrintStream pOut = new PrintStream(bOut);
                pOut.print(message);
                //convert printstream to byte array
                byte [ ] bArray = bOut.toByteArray();
		// Create a datagram packet, containing a maximum buffer of 256 bytes
		DatagramPacket packet=new DatagramPacket( bArray, bArray.length );

                System.out.println("Looking for hostname " + hostname);
                    //get the InetAddress object
                InetAddress remote_addr = InetAddress.getByName(hostname);
                //check its IP number
                System.out.println("Hostname has IP address = " + remote_addr.getHostAddress());
                        //configure the DataGramPacket
                        packet.setAddress(remote_addr);
                        packet.setPort(2000);
                        //send the packet
                        Random random = new Random();
                       //if(random.nextInt(2) == 1){
                            socket.send(packet);
                            startTime = System.currentTimeMillis();
                       //}
                        socket.close();
		System.out.println ("Packet sent at! " + new Date());

		// Display packet information
		System.out.println ("Sent by  : " + remote_addr.getHostAddress() );
        		System.out.println ("Send from: " + packet.getPort());

		}
                catch (UnknownHostException ue){
                        System.out.println("Unknown host "+hostname);
                }
		catch (IOException e){
			System.out.println ("Error - " + e);
		}
            }    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserDatagramProtocol test = new UserDatagramProtocol();
        // TODO code application logic here
    }
}