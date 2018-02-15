package user.datagram.protocol;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.net.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Travis
 */
public class DatagramPacket {
    public DatagramPacket(byte [] buffer, int length){
        
    }
    public DatagramPacket(byte [] buffer, int length, InetAddress dest_addr
                          ,int dest_port){
    }
    InetAddress getAddress(){
    
    }
    
    byte[] getData(){}
    
    int getLength(){}
    
    int getPort(){}
    
    void setAddresss(InetAddress addr){}
    
    void setData(byte)
    
}
