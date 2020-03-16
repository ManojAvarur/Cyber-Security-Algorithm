import java.util.*;
import java.io.*;


class ct {

    static ct c = new ct();
    public static void main(String[] args) {
        String keyword = "";
        String message = "";
        Scanner sc = new Scanner(System.in);
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the Keyword");
        keyword = sc.next();
        int kl = keyword.length();

        System.out.println();
        System.out.println("Enter the Message");
        try {
                message = read.readLine();
            } catch (Exception e) {
                e.getStackTrace();
            }

        message += "\0";

        int ml = message.length();
        ml = ml / 2;

        char[][] MsgArr = new char[ml][kl];
        char[][] EncMsgArr = new char[ml][kl];

        c.insertingIntoArray(kl, ml, keyword, message, MsgArr);
        c.Encryption(kl, ml, keyword, MsgArr, EncMsgArr);
        c.Decryption(read,sc);
        sc.close();
    }

    void insertingIntoArray(int kl, int ml, String keyword, String message, char[][] MsgArr) {


        for (int j = 0; j < kl; j++) {
            MsgArr[0][j] = keyword.charAt(j);
        }

        int count = 0;
        boolean breakcheck = false;
        for(int i = 1; i < ml; i++){
            for(int j = 0; j < kl; j++){
                if( message.charAt(count) == '\0' )
                {
                    breakcheck = true;
                    //MsgArr[i][j] = message.charAt(count);
                    break;
                }
                else{
                    MsgArr[i][j] = message.charAt(count);
                    count = count + 1;
                }
            }
            if( breakcheck )
                break;
        }

        for(int i = 0; i < ml; i++){
            for(int j = 0; j < kl; j++){
                if( MsgArr[i][j] == 0 ){
                    MsgArr[i][j] = '`';
                }
            }
        }

        // System.err.println();
        // System.out.println("Message in Array Befor Encryption : ");
        // c.Display(kl, ml, MsgArr);

        System.err.println();
        System.err.print("Message before Encryption : ");
        c.Display(MsgArr, ml, kl);
       
    }

    void Encryption( int kl, int ml, String keyword, char[][] MsgArr, char[][] EncMsgArr ){

        String[] keywordArr = keyword.split("");
        String temp1;
        char temp;
        
        for (int i = 0; i < kl; i++) {
            for (int j = i + 1; j < kl; j++) { 
                if (keywordArr[i].compareTo(keywordArr[j]) > 0) {
                    temp1 = keywordArr[i];
                    keywordArr[i] = keywordArr[j];
                    keywordArr[j] = temp1;
                }
            }
        }

        keyword = "";
        for(int i = 0 ; i < keywordArr.length ; i++){
            keyword += keywordArr[i];
        }

        int count = 0;
        for(int i = 0 ; i < kl; i++)
        {
            temp = keyword.charAt(i);
            //System.out.println("i = "+i);
            for(int j = 0 ; j < kl ; j++)
            {
                //System.out.println("j = "+j);
                if(temp == MsgArr[0][j])
                {
                    for( int k = 0; k < ml; k++)
                    {
                        EncMsgArr[k][count] = MsgArr[k][j];     
                    }
                    count = count + 1;
                    break;
                }
            }
        }

        // System.err.println();
        // System.err.println("Message in Array After Encryption : ");
        // c.Display(kl, ml, EncMsgArr);

        System.err.println();
        System.err.print("Message After Encryption : ");
        c.Display(EncMsgArr, ml, kl);

    }

    // void Display( int kl, int ml, char[][] ErDArr){

    //     /* ml= ml/2; */
    //     System.err.println();
    //     for(int i = 0; i < ml; i++){
    //         for(int j = 0; j < kl; j++){
    //             System.err.print(ErDArr[i][j]+"\t");
    //         }
    //         System.err.println(); 
    //     }
    // }

    void Display( char[][] ErDArr, int ml, int kl){

        System.err.println();
        System.out.print("\" ");
        for(int i = 0; i < ml; i++){
            for(int j = 0; j < kl; j++){
                System.err.print(ErDArr[i][j]);
            }
        }
        System.out.println(" \"");
        System.err.println();
    }

    void Decryption ( BufferedReader read, Scanner in ){

        System.out.println("--------------------------------------------------------------------------------------");

        String Key = "";
        String msg = "";

        System.out.println();
        System.out.println("Enter the Message");
        try {
            msg = read.readLine();
            } catch (Exception e) {
                e.getStackTrace();
            }

        msg += "\0";

        int ml = msg.length();
        ml = ml / 2;

        System.out.println("Enter the Keyword");
        Key = in.next();
        int kl = Key.length();

        char[][] MsgArr2 = new char[ml][kl];
        char[][] DecMsgArr = new char[ml][kl];

        //Inserting into Array 
        //{
        int j;
        for (j = 0; j < kl; j++) {
            MsgArr2[0][j] = msg.charAt(j);
        }

       
        int count = j;
        boolean breakcheck = false;
        for(int i = 1; i < ml; i++){
            for( j = 0; j < kl; j++){
                if( msg.charAt(count) == '\0' )
                {
                    breakcheck = true;
                    //MsgArr[i][j] = message.charAt(count);
                    break;
                }
                else{
                    MsgArr2[i][j] = msg.charAt(count);
                    count = count + 1;
                }
            }
            if( breakcheck )
                break;
        }

        for(int i = 0; i < ml; i++){
            for(j = 0; j < kl; j++){
                if( MsgArr2[i][j] == 0 ){
                    MsgArr2[i][j] = '`';
                }
            }
        }

        // System.err.println();
        // System.out.println("Message in Array After Inserting : ");
        // c.Display(kl, ml, MsgArr2);
        //}

        //Decription 
        //{
        count = 0;
        char temp;
        for(int i = 0 ; i < kl; i++)
        {
            temp = Key.charAt(i);
            //System.out.println("i = "+i);
            for(j = 0 ; j < kl ; j++)
            {
                //System.out.println("j = "+j);
                if(temp == MsgArr2[0][j])
                {
                    for( int k = 0; k < ml; k++)
                    {
                        DecMsgArr[k][count] = MsgArr2[k][j];     
                    }
                    count = count + 1;
                    break;
                }
            }
        }

        // System.err.println();
        // System.out.println("Message in Array After Decription : ");
        // c.Display(kl, ml, DecMsgArr);
        //}

        // Display
        //{
            System.err.println();
            System.out.println("Decrypted Message : ");
            System.out.print("\" ");
            for(int i = 1; i < ml; i++){
                for(j = 0; j < kl; j++){
                    if( !(DecMsgArr[i][j] == '`'))
                        System.err.print(DecMsgArr[i][j]);
                }
            }
            System.out.println(" \"");
            System.err.println();

    }
 
}