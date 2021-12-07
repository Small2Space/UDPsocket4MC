package org.skunion.smallru8.UDPsocket4MC.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;

public class Socket implements Runnable{

	private MulticastSocket mSocket;
	private SocketAddress mAddress;//multicast ip
	private InetAddress uAddress;//unicast ip
	private int port = 25565;
	private int timeout = 5000;//ms
	
	private boolean runFlag = true;
	
	public Socket(String unicastIP) {
		try {
			mAddress = new InetSocketAddress("225.5.6.5",port);
			uAddress = InetAddress.getByName(unicastIP);
			mSocket = new MulticastSocket(port);//port 25565
			mSocket.joinGroup(mAddress, NetworkInterface.getByInetAddress(InetAddress.getByName(unicastIP)));//Send multicast join message via this interface get by IP.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
		while(runFlag) {
			try {
				mSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(packet.getAddress().getHostAddress().equalsIgnoreCase(uAddress.getHostAddress())) {//recv an unicast, Decrypt
				//TODO Decrypt
			}else if(packet.getAddress().getHostAddress().equalsIgnoreCase("225.5.6.5")) {//recv an multicast
				
			}else {
				continue;
			}
			
			ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
			ObjectInput in = null;
			try {
				in = new ObjectInputStream(bis);
				DataFields df = (DataFields) in.readObject(); 
				if (in != null) {
					in.close();
				}
				
				if(System.currentTimeMillis()-df.timestamp>timeout) {//timeout
					continue;
				}
				
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				continue;
			}
			
			
			
		}
	}
	
	public void sendData(String targetIP,DataFields df) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(bos);
			df.timestamp = System.currentTimeMillis();
			out.writeObject(df);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytestoSend = bos.toByteArray();
		DatagramPacket packet;
		
		if(targetIP == null)//multicast
			packet = new DatagramPacket(bytestoSend, bytestoSend.length,mAddress);
		else{//unicast, Encrypt
			//TODO 
			packet = new DatagramPacket(bytestoSend, bytestoSend.length,new InetSocketAddress(targetIP,port));
		}
		try {
			mSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendData(DataFields df) {
		sendData(null,df);
	}
	
}
