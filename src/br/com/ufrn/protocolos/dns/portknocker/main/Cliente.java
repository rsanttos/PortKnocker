package br.com.ufrn.protocolos.dns.portknocker.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	static Scanner leitorTeclado = new Scanner(System.in);
	public static void main(String[] args) throws IOException {

		Socket socket = new Socket("localhost", 7777);
		System.out.println("Cliente 1 conectado com o servidor " + socket.getInetAddress().getHostAddress() + ":"
				+ socket.getPort());

		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
		String mensagem = "";

		System.out.print("Digite a mensagem para enviar ao servidor: ");
		mensagem = leitorTeclado.nextLine();		
		
		outBytes.write(mensagem.getBytes());
		
		DataInputStream inBytes = new DataInputStream(socket.getInputStream());
		byte[] data = new byte[128];
		inBytes.read(data);
		String mensagemRecebida = new String(data).trim();
		System.out.println("Resposta do servidor: " + mensagemRecebida);			
		
		socket.close();
		
		/* --------------------------- */
		
		Socket socket2 = new Socket("localhost", 8888);
		System.out.println("Cliente 2 conectado com o servidor " + socket2.getInetAddress().getHostAddress() + ":"
				+ socket2.getPort());

		DataOutputStream outBytes2 = new DataOutputStream(socket2.getOutputStream());
		String mensagem2 = "";

		System.out.print("Digite a mensagem para enviar ao servidor: ");
		mensagem2 = leitorTeclado.nextLine();		
		
		outBytes2.write(mensagem2.getBytes());
		
		DataInputStream inBytes2 = new DataInputStream(socket2.getInputStream());
		byte[] data2 = new byte[128];
		inBytes2.read(data2);
		String mensagemRecebida2 = new String(data2).trim();
		System.out.println("Resposta do servidor: " + mensagemRecebida2);			
		
		socket2.close();
	}
}
