import java.util.ArrayList;
import java.util.Scanner;


public class teste{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        DosesAplicadas aplicadas = new DosesAplicadas();
        ArrayList<String> lista = new ArrayList<>();
        lista = aplicadas.all(lista);
        String dose = "1° Dose";
        int cont = 0,cont2 = 0;
        String cid, dosecid;
        TipoDose doses = new TipoDose();
        ArrayList<String> listaDose = new ArrayList<>();
        listaDose = doses.listarDose(listaDose);


        for (int i = 0; i < listaDose.size(); i++){
            System.out.println((i+1)+" = "+listaDose.get(i));
        }
        System.out.println("-------------");
        for (int i = 0; i < listaDose.size(); i++){
            System.out.println(listaDose.get(i));
        }
        System.out.println("-------------");
}
}

