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
			//Estabelecndo conexão usando de parâmetro ip e a porta em uma tomada, observação importante: o ip passado de parâmetro
			//127.0.0.1 é um ip genérico que indica que o servidor se encontra na própria máquina, é o famoso loop back. A tomada
			//em questão vai tentar acessar o servidor do ip fornecido pela porta fornecida.
			JOptionPane.showMessageDialog(null, "Conexão estabelecida");
			Socket socket = new Socket("127.0.0.1", 59841);
			
			//Enviar o objeto por meio de um fluxo de saída de objeto.
			ObjectOutputStream envio = new ObjectOutputStream(socket.getOutputStream());
			//Linha em que o objeto é enviado.
			envio.writeObject(new Pessoa("Rafael Souto", 21, EstadoCivil.SOLTEIRO));
			JOptionPane.showMessageDialog(null, "Cliente fechando conexão.");
			envio.close();
			socket.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
