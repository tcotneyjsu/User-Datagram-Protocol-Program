/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.datagram.protocol;
import java.awt.event.*;
import java.nio.*;
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
    private byte[] buffer = new byte[512];
    
    public UserDatagramProtocol(){
        int port = 2000;
        try{
            socket = new DatagramSocket(port);
        }
        catch(Exception e){}
    }
    public void running(){
        running = true;
        while(running){
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try{
                socket.receive(packet);
            }
            catch(Exception e){};
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
    }
    
}
