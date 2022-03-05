package def;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import servidorsocket.Carro;

public class ClienteSocket {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// estabelecer a conexão --> ip:porta
				Socket socket = new Socket("127.0.0.1", 59841);
				// enviar o nome -> fluxo de envio de dados
				ObjectOutputStream envio = new ObjectOutputStream(socket.getOutputStream());
				envio.writeObject(new Carro("UNO", 80));
				envio.close();
				socket.close();
	}

}
