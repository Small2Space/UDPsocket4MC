package org.skunion.smallru8.UDPsocket4MC.socket;

import java.util.ArrayList;

public class DataFields {

	public String sender;//sender hash
	public ArrayList<String> transporter = new ArrayList<String>();
	public long timestamp;
	public byte type = 0x01;//data type
	/*
	 * 0x01 = byte[]
	 * 0x02 = String(UTF8)
	 */
	public byte[] data;
	
}
