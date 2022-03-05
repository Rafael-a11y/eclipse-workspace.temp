package cliente;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import objetosTrafegados.Pessoa;
import objetosTrafegados.Pessoa.EstadoCivil;
public class Principal {

	public static void main(String[] args) throws UnknownHostException {
		
		try {
			//Estabelecndo conex�o usando de par�metro ip e a porta em uma tomada, observa��o importante: o ip passado de par�metro
			//127.0.0.1 � um ip gen�rico que indica que o servidor se encontra na pr�pria m�quina, � o famoso loop back. A tomada
			//em quest�o vai tentar acessar o servidor do ip fornecido pela porta fornecida.
			JOptionPane.showMessageDialog(null, "Conex�o estabelecida");
			Socket socket = new Socket("127.0.0.1", 59841);
			
			//Enviar o objeto por meio de um fluxo de sa�da de objeto.
			ObjectOutputStream envio = new ObjectOutputStream(socket.getOutputStream());
			//Linha em que o objeto � enviado.
			envio.writeObject(new Pessoa("Rafael Souto", 21, EstadoCivil.SOLTEIRO));
			JOptionPane.showMessageDialog(null, "Cliente fechando conex�o.");
			envio.close();
			socket.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
