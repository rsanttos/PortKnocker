package br.com.ufrn.protocolos.dns.portknocker.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		int[] portas = { 6666, 7777, 8888, 9998 };

		System.out.println("Servidor em execução.");
		
		for (int i = 0; i < portas.length; i++) {
			try {
				recebeConexao(portas[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void recebeConexao(int porta) throws IOException {
		ServerSocket serverSocket = new ServerSocket(porta);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Aguardando conexão do cliente no servidor. Porta: " + porta);
		Socket socket = serverSocket.accept();
		System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado.");			
		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());		
		String mensagemResposta = "CONEXAO_OK";
		outBytes.write(mensagemResposta.getBytes());			
		System.out.println("Mensagem retornada do socket1 para o cliente: " + mensagemResposta);			
		socket.close();
		serverSocket.close();
		System.out.println("--------------------------------------------------------------------");
	}
}
