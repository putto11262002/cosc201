package cosc201.week03;

public class Test {
    public static void main(String[] args){
        String[] values = {"TOP", "BOTTOM", "LEFT"};
        for(String value: values){
            switch (value) {
                case "TOP":
                    System.out.println("TOP");
                    for(int i = 0; i < 10; i ++){
                        System.out.print(i);
                    }
                    System.out.println();
                    break;
                case "BOTTOM":
                    System.out.println("BOTTOM");
                    
                    break;
            
                default:
                    break;
            }

        }
        
    }
    
}
