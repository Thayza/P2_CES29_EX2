package br.ita.P2_CES29_Q2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VulnerableClass {
	public void vulnerableMethod(String FILENAME){
		while (true) {
			
		    Scanner console = new Scanner(System.in);  // console não é fechado
		    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
		    		+ "W para escrever em um arquivo>? ");
			
		    String opr= console.nextLine();				
			
		    if (opr.equals("R")){
				BufferedReader br = null;		// buffer reader não é fechados		
				FileReader fr = null;           //file reader não é fechado
				
				try {

					fr = new FileReader(FILENAME);
					br = new BufferedReader(fr);

					String sCurrentLine;				

					br = new BufferedReader(new FileReader(FILENAME));

					while ((sCurrentLine = br.readLine()) != null) {
						System.out.println(sCurrentLine);
					}

				} catch (IOException e) {

					e.printStackTrace();

				} 
			}
			
			else {												// ??  qualquer coisa
				  BufferedWriter buffWrite;
				  
				  try {
					buffWrite = new BufferedWriter(new FileWriter(FILENAME));
					String linha = "";
					System.out.println("Escreva algo: ");
				    linha = console.nextLine();
				    buffWrite.append(linha + "\n");
				     
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
}