package swing.layout.test;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
public class InvokeLaterTest {

	private static void incUm() {
		
		System.out.println( 1 + 1);
		
	}
	private static void incDois() {
		System.out.println(1 + 2);
		incUm();
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		
		SwingUtilities.invokeAndWait(() -> {incDois();});
		System.out.println("APARECE PRIMEIRO");
	}
}

