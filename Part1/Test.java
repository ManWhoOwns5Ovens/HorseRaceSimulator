public class Test{
    public static void main(String[] a){
        int width=10;
        int height=6;

        for (int y=0; y<height;y++){
            for(int x=0; x<width;x++){
                if (x==width/2 || y==height/2 || (width%2==0 && x==width/2-1) || (height%2==0 && y==height/2-1)) {
                    System.out.print("X");
                }
                else{
                    System.out.print("O");
                }
                
            }
            System.out.print("\n");
        }
    }
}


