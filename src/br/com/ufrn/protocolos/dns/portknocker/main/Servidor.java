package br.com.ufrn.protocolos.dns.portknocker.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		int[] portas = { 6666, 7777, 8888, 9999 };

		System.out.println("Servidor em execução.");
		String enderecoCliente = "enderecoCliente"; 
		String enderecoClienteAnterior = "";

		while (true) {
			int i = 0;
			while (i < portas.length) {
				try {
					enderecoCliente = recebeConexao(portas[i], i, portas.length);
					if (i > 0 && !enderecoClienteAnterior.equals(enderecoCliente)) {
						return;
					}

					if (i == 0 && !enderecoClienteAnterior.equals(enderecoCliente)) {
						enderecoClienteAnterior = enderecoCliente;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			}

		}
	}

	public static String recebeConexao(int porta, int posicao, int qtdPortas) throws IOException {
		ServerSocket serverSocket = new ServerSocket(porta);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Aguardando conexão do cliente no servidor. Porta: " + porta);
		//serverSocket.setSoTimeout(9000);
		Socket socket = serverSocket.accept();
		String enderecoCliente = socket.getInetAddress().getHostAddress();
		System.out.println("Cliente " + enderecoCliente + " conectado.");
		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
		String mensagemResposta = "";
		if(posicao+1 == qtdPortas) {
			mensagemResposta = "CONEXAO_OK -> PORTA PRINCIPAL=5555";
		} else {
			mensagemResposta = "CONEXAO_OK";
		}
		outBytes.write(mensagemResposta.getBytes());
		System.out.println("Mensagem retornada do socket1 para o cliente: " + mensagemResposta);
		socket.close();
		serverSocket.close();
		System.out.println("--------------------------------------------------------------------");
		
		if(mensagemResposta.equals("CONEXAO_OK -> PORTA PRINCIPAL=5555")) {
			return recebeConexao(5555, 0, 5);
		}
		
		return enderecoCliente;
	}
}
