package servidor;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

import objetosTrafegados.Pessoa;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import objetosTrafegados.Pessoa;

public class Principal {

	public static void main(String[] args) {
		
		try {
			//Criando a tomada servidor passando de parâmetro o número da porta.
			ServerSocket serverSocket = new ServerSocket(59841);
			JOptionPane.showMessageDialog(null, "Porta 59841 foi aberta e está sendo escutada.");
			
			//Estabelecendo a conexão criando uma tomada que recebe a tomada da outra ponta.
			//É a linha em que o servidor recebe e a solicitação do cliente  e se conecta a ele.
			Socket socket = serverSocket.accept();
			JOptionPane.showMessageDialog(null, "Cliente " + socket.getInetAddress() + " - " + 
				socket.getInetAddress().getHostAddress() + " conectado");
			
			//Receber o objeto, o fluxo de entrada de objeto 'recebimento' é criado.
			//Linha em que o objeto é recebido.
			ObjectInputStream recebimento = new ObjectInputStream(socket.getInputStream());
			//A variável pessoa recebe o objeto lido de socket, porém, o que é lido é um Object
			//e sendo assim é necessário uso de casting. É a linha em que o objeto é processado
			// para Pessoa;
			Pessoa pessoa = (Pessoa) recebimento.readObject();
			JOptionPane.showMessageDialog(null, "Servidor fechando a conexão e a porta.");
			
			//Fechando a conexão, pois tanto tomada servidor, tomada e fluxo de entrada de objeto
			//precisam ser fechados.
			recebimento.close();
			socket.close();
			serverSocket.close();
			
			//Mostrando o objeto recebido do outro programa;
			JOptionPane.showMessageDialog(null, pessoa);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}
}
