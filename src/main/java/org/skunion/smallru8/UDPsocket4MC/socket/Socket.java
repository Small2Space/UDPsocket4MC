package org.skunion.smallru8.UDPsocket4MC.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Socket implements Runnable{

	@Override
	public void run() {
		
	}
	
	public void sendData(String IP,DataFields df) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(df);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] yourBytes = bos.toByteArray();
	}
	
	public void sendData(DataFields df) {
		sendData(null,df);
	}
	
}
