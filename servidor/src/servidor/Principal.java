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
			//Criando a tomada servidor passando de par�metro o n�mero da porta.
			ServerSocket serverSocket = new ServerSocket(59841);
			JOptionPane.showMessageDialog(null, "Porta 59841 foi aberta e est� sendo escutada.");
			
			//Estabelecendo a conex�o criando uma tomada que recebe a tomada da outra ponta.
			//� a linha em que o servidor recebe e a solicita��o do cliente  e se conecta a ele.
			Socket socket = serverSocket.accept();
			JOptionPane.showMessageDialog(null, "Cliente " + socket.getInetAddress() + " - " + 
				socket.getInetAddress().getHostAddress() + " conectado");
			
			//Receber o objeto, o fluxo de entrada de objeto 'recebimento' � criado.
			//Linha em que o objeto � recebido.
			ObjectInputStream recebimento = new ObjectInputStream(socket.getInputStream());
			//A vari�vel pessoa recebe o objeto lido de socket, por�m, o que � lido � um Object
			//e sendo assim � necess�rio uso de casting. � a linha em que o objeto � processado
			// para Pessoa;
			Pessoa pessoa = (Pessoa) recebimento.readObject();
			JOptionPane.showMessageDialog(null, "Servidor fechando a conex�o e a porta.");
			
			//Fechando a conex�o, pois tanto tomada servidor, tomada e fluxo de entrada de objeto
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
