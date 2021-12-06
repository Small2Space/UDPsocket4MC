package org.skunion.smallru8.UDPsocket4MC;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class CERT {
	
	
	public CERT() {

	}
	
	private void checkFile() {
		File dir = new File("plugins/websocket4MC");// plugins/websocket4MC/
		if(!dir.exists())
			dir.mkdir();
		
		//Config.yml check
		Configuration configuration = null;
		File configYML = new File(dir,"config.yml");// plugins/websocket4MC/config.yml
		try {
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configYML);
		} catch (IOException e) {
			try {
				configYML.createNewFile();
				configuration = new Configuration();
				
				//TODO 初始化
				
				Random random = new Random();
				configuration.set("tls_cert_key_pkcs12", "server.pk12");//TLS cert(format pkcs12) name
				//configuration.set("storepass", ""+random.nextLong());可以存在RAM 待更正
				//configuration.set("keypass", ""+random.nextLong());
				//使用這3個參數產生keystore.jks, tls_cert_key_pkcs12不存在就隨機生成
				
				ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configYML);//save
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		dir = new File(dir,"cert");// plugins/websocket4MC/cert/
		if(!dir.exists())
			dir.mkdir();
		
		
	}
}
