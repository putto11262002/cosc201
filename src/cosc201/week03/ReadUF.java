package cosc201.week03;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;



public class ReadUF {
    static UF3 uf3 = new UF3();

    public static void main(String[] args) {
        readFile("src/cosc201/week03/testInput.txt");

    }

    static void readFile(String pathname) {
        try {
            File file = new File(pathname);
            Scanner scan = new Scanner(file);
            try {
                int size = Integer.parseInt(scan.nextLine().trim());
                uf3.make(size);
                System.out.println("Size " + size);
            } catch (Exception e) {
                System.out.println("Invalid set size.");
            }
            while (scan.hasNext()) {
                process(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    static void find(int x) {
        System.out.println("Find " + x);
        uf3.find(x);
    }

    static void all(int x) {
        System.out.print("All " + x + " ");
        for (int member : uf3.all(x)) {
            System.out.print(member + " ");
        }
        System.out.println();

    }

    static void summary() {
        System.out.println("Summary ");
        for (int[] set : uf3.summary()) {
            for (int member : set) {
                System.out.print(member + " ");
            
            }
            System.out.println();
            

        }
       

    }

    static void union(int x, int y) {
        System.out.println("Union " + x + " " + y);
        uf3.union(x, y);
    }

    static void process(String line) {
        String[] lineArr = line.split(" ");

        switch (lineArr[0]) {
            case "Find":
                find(Integer.parseInt(lineArr[1]));
                break;
            case "All":
                all(Integer.parseInt(lineArr[1]));
                break;
            case "Summary":
                summary();
                
                break;
            case "Union":
                union(Integer.parseInt(lineArr[1]), Integer.parseInt(lineArr[2]));
                break;

            default:
                throw new Error("Invalid input.");
               
        }
    }

}
