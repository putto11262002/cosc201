package cosc201.week06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ShuffleApp {
    public static void main(String[] args){
        Experiment1();


    }
    public static void Experiment1(){

        try{
            StringBuilder s = new StringBuilder("r,k,i\n");

            PrintWriter pw = new PrintWriter(new File("src/main/cosc201/week06/experiment1.csv"));

          for(int r = 1; r <= 100;r++){
              for(int k = 1; k <= 50; k++){
                  int[] deck = Shuffler.newDeck(52);
                  int zeroCard = 0; // as d[i] = i
                  int[] shuffledDeck = Shuffler.shuffle(deck,k);
                  for(int i = 0; i < deck.length; i++){
                      if(shuffledDeck[i] == zeroCard){
                          s.append(r);
                          s.append(",");
                          s.append(k);
                          s.append(",");
                          s.append(i);
                          s.append("\n");

                          break;
                      }
                  }
              }
          }
            pw.write(s.toString());
            pw.close();
            System.out.println(s.toString());
        }catch (FileNotFoundException e){
            System.out.println(e);
        }



    }
}
