package br.com.ufrn.protocolos.dns.portknocker.main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	static Scanner leitorTeclado = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {

		int[] portas = { 6666, 7777, 888, 9999 };
		String host = "localhost";
		
		for (int i = 0; i < portas.length; i++) {
			try {
				realizaConexao(host, portas[i]);
			} catch (Exception e) {
				System.out.println("Porta " + portas[i] + " inválida.");
				e.printStackTrace();
				Thread.sleep(10000);
			}
		}
	}

	public static void realizaConexao(String host, int porta) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, porta);
		System.out.println("Cliente conectado com o servidor " + socket.getInetAddress().getHostAddress() + ":"
				+ socket.getPort());
		DataInputStream inBytes = new DataInputStream(socket.getInputStream());
		byte[] data = new byte[128];
		inBytes.read(data);
		String mensagemRecebida = new String(data).trim();
		System.out.println("Resposta do servidor: " + mensagemRecebida);
		socket.close();
		
		int posicao = mensagemRecebida.indexOf("=");
		if(posicao > 0) {
			String portaPrincipal = mensagemRecebida.substring(posicao + 1, mensagemRecebida.length());
			int portaPrincipalInt = Integer.parseInt(portaPrincipal);
			realizaConexao(host, portaPrincipalInt);
		}
		
	}
}
