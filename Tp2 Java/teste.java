import java.util.Scanner;
import java.text.Normalizer;
import java.text.Normalizer.Form;


public class teste{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        String y = "Uniao";
        String n = Normalizer.normalize(x, Form.NFD);
        String m = n.replaceAll("\\p{M}", "");
        if(m.equalsIgnoreCase(y)){
            System.out.println(n+" "+m);
        }
}
}

