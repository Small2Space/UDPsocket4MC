package org.skunion.smallru8.UDPsocket4MC.socket;

import java.util.ArrayList;

public class DataFields {

	public long sender;//sender server id
	public ArrayList<Long> transporter = new ArrayList<Long>();
	public long timestamp;
	public byte type = 0x01;//data type
	/*
	 * 0x01 = byte[]
	 * 0x02 = String(UTF8)
	 */
	public byte[] data;
	
}
