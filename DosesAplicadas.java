import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.text.Collator;

public class DosesAplicadas {
    Scanner in = new Scanner(System.in);
    
    
    ArrayList<String> lista = new ArrayList<String>();
    TipoDose dose = new TipoDose();
    ArrayList<String> listaDose = new ArrayList<String>();
    

    public ArrayList<String> all(ArrayList allGeral){

        String linha;

        try{
            
            File arquivoCSV = new File("C:\\Tp2 Java\\20220525_vacinometro.csv");
            BufferedReader br = new BufferedReader(new FileReader(arquivoCSV));
    
                //Lê as linhas do arquivo enquanto não forem nulas e as separa em um Array, o valor na posição [1] do array é o nome da dose, que é colocado em uma lista, se estiver vazia
                if(allGeral.isEmpty()){
                    while ((linha = br.readLine()) != null) {
    
                        String[] colunas = linha.split(";");
    
                        if (colunas[0] != null && colunas[1] != null && colunas[2] != null && colunas[1].equalsIgnoreCase("dose") == false){
                            allGeral.add(colunas[0]);
                            allGeral.add(colunas[1]);
                            allGeral.add(colunas[2]);
                        }
                    }
                }
                
                br.close();

            }catch(FileNotFoundException e){
                System.out.println("Arquivo não encontrado");
            }catch(IOException e){
                System.out.println("Erro de leitura/escrita");
            }
            return allGeral;
    }


    public ArrayList<String> atualizaCidade(String cidade,String tipoDose, int j){

        boolean validador = true;
        String numDoseS;
        int numDose = 0;

        
        lista = all(lista);

            //Acho que seria possível fazer isso com índices, similar a banco de dados, nos dados das tabelas e organizar elas, mas não sei como faria.

            if(lista.get(j).equalsIgnoreCase(cidade) && lista.get(j+1).equalsIgnoreCase(tipoDose)){
                    System.out.printf("O número atual de doses dessa cidade é %s, para qual número deseja atualizar? : ",lista.get(j+2));

                        while(validador){
                                    
                        try{
                        numDose = in.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("Por favor, digite um número válido! :");
                        }
                        if (numDose > 0){
                             validador = false;
                        }
                    }

                    numDoseS = "" + numDose;
                    lista.set(j+2,numDoseS);

                }else{
                    System.out.println("Erro!");
                }

        return lista;

    }

    public void tudoCidade(String cidade){

        lista = all(lista);

            for(int i = 0; i < lista.size()-3; i++){
                if(lista.get(i).equalsIgnoreCase(cidade)){
                    System.out.printf("%s == %s == %s \n",lista.get(i),lista.get(i+1),lista.get(i+2));
                }
            }
    }

    public void tudoCadastro(){

        lista = all(lista);
        System.out.println("Digite 'sair' para parar de atualizar doses.");
        String c;

        for (int i = 0; i < lista.size()-3;i+=3){
            System.out.printf("Cidade: %s , Tipo: %s , Quantidade: %s , atualizar a quantidade para:",lista.get(i),lista.get(i+1),lista.get(i+2));
            c = in.nextLine();
            if(c.equalsIgnoreCase("sair")){
                System.out.println("Cancelando atualização...");
                break;
            }else{
                lista.set(i+2,c);
            }
        }

    }

    public void cadastraCidDose(String cid, String dose, int qnt){

        lista = all(lista);

        lista.add(cid);
        lista.add(dose);
        String quantidade = Integer.toString(qnt);
        lista.add(quantidade);
        
    }

}