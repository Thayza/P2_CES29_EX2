package br.ita.P2_CES29_Q2;


import static org.junit.Assert.fail;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Before;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter; 

@FixMethodOrder
public class SafeClassTests {
	

	private SafeClass safety = null;
	private BufferedWriter bw = null;
	
	private void criarArquivo(String filename, String data){
		try{
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(data);
			bw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void deletarArquivoCriado(String filename){
		try{
			File file = new File(filename);
			file.delete();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void entradaConsole(String input){
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	}
	
	
	
	@Before
	public void setup(){
		safety = new SafeClass();
	}

	@Test
	public void arquivoComNomeInavlido() {
		System.out.print("Teste: Nome inválido\n");
		try{
			safety.SafeMethod("arquivo/.txt");  // caractere inválido /
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
	}
	
	@Test
	public void comandoInvalido() {
		System.out.print("\nTeste: Comando inválido\n");
		criarArquivo("entrada.txt", "qualquercoisa");
		entradaConsole("A");
		
		try{
			safety.SafeMethod("entrada.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
		deletarArquivoCriado("entrada.txt");
	}
	
	@Test 
	public void lerAqruivoNaoExistenteComandoR(){
		deletarArquivoCriado("entrada.txt");
		System.out.print("\nTest: Arquivo Inexistente\n");
		entradaConsole("R");
		try{
			safety.SafeMethod("entrada.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
	}
	
	@Test
	public void lerArquivoExistente() {
		System.out.print("\nTeste: Ler arquivo\n");
		criarArquivo("entrada.txt", "qualquercoisa");
		entradaConsole("R");
		try{
			safety.SafeMethod("entrada.txt");
		}
		catch(Exception e){}
		deletarArquivoCriado("entrada.txt");
	}
	
	@Test
	public void esceverArquivoExistente() {
		System.out.print("\nTeste: Escrever arquivo\n");
		criarArquivo("entrada.txt", "qualquercoisa");
		entradaConsole("W");
		try{
			safety.SafeMethod("entrada.txt");
		}
		catch(Exception e){}
		deletarArquivoCriado("entrada.txt");
	}
}
