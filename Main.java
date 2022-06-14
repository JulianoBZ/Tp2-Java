import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.Collator;


public class Main {

    public static void main(String args[]) {
    
        //Instancia e define variáveis
        Scanner in = new Scanner(System.in);
        Cidade cidade = new Cidade();
        TipoDose dose = new TipoDose();
        DosesAplicadas aplicadas = new DosesAplicadas();
        Boolean validador = true;
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> listaGeral = new ArrayList<String>();
        ArrayList<String> listaDose = new ArrayList<String>();
        //A lista está recebendo um método e referenciando a si mesma, pois quando a lista é atualizada, o método não realiza a leitura do arquivo novamente, mas sim a nova lista gerada, a não ser que esteja vazia, que então ela lê o arquivo e usa os dados do arquivo, não os modificados durante a sessão.
        listaDose = dose.listarDose(listaDose);
        listaGeral = aplicadas.all(listaGeral);



        while(validador){

            //Sistema de menu simples.

            System.out.println("O que deseja fazer? \n [1] - Cadastrar cidade\n [2] - Visualizar cidades\n [3] - Cadastrar Dose\n [4] - Visualizar Doses \n [5] - Atualizar número de doses\n [6] - Exibir Tudo\n [7] - Ver cidade específica\n [8] - Cadastrar Doses e Vacinas na cidade\n [9] - Cadastrar nova dose em uma cidade\n [0] - Sair do Programa ");
            String decisao = in.nextLine();
    
            switch (decisao){
                case "1":
                
                    //Cadastra uma cidade
                    cidade.cadastrarCidade();
                    break;

                case "2":

                    lista = cidade.listarCidade();

                    //Imprime as cidades da lista sem duplicatas
                    for (int i = 0; i < lista.size() ; i++){
                        System.out.println(lista.get(i));
                    }

                    break;
                
                case "3":

                    //Cadastra um novo tipo de dose
                    dose.cadastroDose();
                    break;

                case "4":

                    //Imprime as doses da lista sem duplicatas
                    System.out.println("-------------");
                    for (int i = 0; i < listaDose.size(); i++){
                        System.out.println(listaDose.get(i));
                    }
                    System.out.println("-------------");
                    break;
                    
                case "5":
                    
                    //Pergunta qual cidade, imprime os tipos de doses existentes na lista de doses, e solicita a dose a ser pesquisada
                    String cid,doseString;
                    int dosecid;
                    int val = 0, j = 0;
                    System.out.println("Qual cidade: ");
                    cid = in.nextLine();

                    //Imprime a lista de doses cadastradas
                    for(int i = 0;i < listaDose.size();i++){
                        System.out.printf("%d = %s | ",(i+1),listaDose.get(i));
                    }
                    System.out.println("\nDose: ");
                    dosecid = in.nextInt();
                    //De acordo com o número inserido, pega o item na lista correspondente ao index da string
                    doseString = listaDose.get(dosecid-1);
                    
                    //Procura se existe a informação da cidade com a dose ao lado, a partir da listaGeral
                    for (int i = 0; i < listaGeral.size()-3;i+=3){
                        if (listaGeral.get(i).equalsIgnoreCase(cid) && listaGeral.get(i+1).equalsIgnoreCase(doseString)){
                            val++;
                            j = i;
                        }
                    }

                    //Se achar, o valor será > 0
                    if (val>0){
                        listaGeral = aplicadas.atualizaCidade(cid,doseString,j);
                    }else{
                        System.out.println("-------------");
                        System.out.println("Cidade ou Dose não cadastradas!");
                        System.out.println("-------------");
                    }

                    break;
                
                case "6":

                    //A partir da listaGeral, que possui TODOS os dados em ordem de Cidade,Tipo de dose e Quantidade, exibe a cidade, o proximo valor e o próximo a esse, e então pula 3 valores no i, que representa a próxima cidade, e repete o processo.
                    for(int i = 0; i < listaGeral.size()-2; i+=3){
                        System.out.printf("Cidade: %s  Dose: %s  Quantidade: %s\n",listaGeral.get(i),listaGeral.get(i+1),listaGeral.get(i+2));
                    }

                    break;

                case "7":
                    
                    //Solicita uma string "cidade", procura na listaGeral, e passa para o método tudoCidade

                    String cid2;
                    System.out.println("Qual cidade?");
                    cid2 = in.nextLine();

                    for (int i = 0; i < listaGeral.size()-3;i+=3){
                        if(listaGeral.get(i).equalsIgnoreCase(cid2)){
                        aplicadas.tudoCidade(cid2);
                        break;    
                        }
                    }
                    break;

                case "8":

                    //Atualiza doses nas cidades, e vai perguntando até que todas sejam atualizadas ou ignoradas.
                    aplicadas.tudoCadastro();
                    break;

                case "9":
                listaDose = dose.listarDose(listaDose);
        
                //Pergunta qual cidade, imprime os tipos de doses existentes na lista de doses, e solicita a dose a ser pesquisada
                String cid3,doseString2;
                int dosecid2,qnt;
                int val2 = 0;
                System.out.println("Qual cidade: ");
                cid3 = in.nextLine();
        
                //Imprime a lista de doses cadastradas
                for(int i = 0;i < listaDose.size();i++){
                    System.out.printf("%d = %s | ",(i+1),listaDose.get(i));
                }
                System.out.println("\nDose: ");
                dosecid2 = in.nextInt();
                //De acordo com o número inserido, pega o item na lista correspondente ao index da string
                doseString2 = listaDose.get(dosecid2-1);

                System.out.println("Quantidade: ");
                qnt = in.nextInt();
                
                //Procura se existe a informação da cidade com a dose ao lado, a partir da listaGeral
                for (int i = 0; i < listaGeral.size()-3;i+=3){
                    if (listaGeral.get(i).equalsIgnoreCase(cid3) && listaGeral.get(i+1).equalsIgnoreCase(doseString2)){
                        System.out.println("Já cadastrado!");
                        val2++;
                    }
                }

                
        
                if(val2 == 0){

                    listaGeral.add(cid3.toUpperCase());
                    listaGeral.add(doseString2);
                    String quantidade = Integer.toString(qnt);
                    listaGeral.add(quantidade);
                    aplicadas.cadastraCidDose(cid3,doseString2,qnt);
                    System.out.printf("Cidade %s, Dose: %s, Quantidade: %s\n Cadastrados com sucesso!\n",cid3,doseString2,qnt);
                }
                    break;

                case "0":
                    System.out.println("\nObrigado! Feito por Juliano Barreira Zorzetto - 2° Ciclo ADS - Vespertino");
                    validador = false;
                    break;
            }
        }

        in.close();

        
    }
}

