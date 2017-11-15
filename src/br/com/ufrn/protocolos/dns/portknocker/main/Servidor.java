package br.com.ufrn.protocolos.dns.portknocker.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {

		System.out.println("Servidor em execu��o.");
		while (true) {			
			ServerSocket serverSocket1 = new ServerSocket(7777);
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Aguardando conexão do cliente no serverSocket1...");
			Socket socket1 = serverSocket1.accept();
			System.out.println("Cliente " + socket1.getInetAddress().getHostAddress() + " conectado!");
			DataInputStream inBytes = new DataInputStream(socket1.getInputStream());
			byte[] data = new byte[128];
			inBytes.read(data);
			
			String mensagemRecebida = new String(data).trim();
			System.out.println("Mensagem recebida socket1: " + mensagemRecebida);			
			DataOutputStream outBytes = new DataOutputStream(socket1.getOutputStream());				
			outBytes.write(mensagemRecebida.getBytes());			
			System.out.println("Mensagem retornada do socket1 para o cliente: " + mensagemRecebida);
			
			
			socket1.close();
			serverSocket1.close();
			System.out.println("--------------------------------------------------------------------");
			
			ServerSocket serverSocket2 = new ServerSocket(8888);
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Aguardando conexão do cliente no serverSocket2...");
			Socket socket2 = serverSocket2.accept();
			System.out.println("Cliente " + socket2.getInetAddress().getHostAddress() + " conectado!");
			DataInputStream inBytes2 = new DataInputStream(socket2.getInputStream());
			byte[] data2 = new byte[128];
			inBytes2.read(data2);
			
			String mensagemRecebida2 = new String(data2).trim();
			System.out.println("Mensagem recebida socket2: " + mensagemRecebida2);			
			DataOutputStream outBytes2 = new DataOutputStream(socket2.getOutputStream());				
			outBytes.write(mensagemRecebida2.getBytes());			
			System.out.println("Mensagem retornada do socket2 para o cliente: " + mensagemRecebida2);
			
			
			System.out.println("--------------------------------------------------------------------");
			socket2.close();
			serverSocket2.close();
		}
	}
}
