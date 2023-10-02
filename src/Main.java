import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        boolean run = true;
        Scanner scan = new Scanner(System.in);

        String messageToEncrypt;
        String keyeStr;
        int shfk;
        int  programInput;

        CesarCypher cesar = new CesarCypher();
        VigenereCypher vigener = new VigenereCypher();

        CesarInterface cesarInterface = new CesarInterface.FakeCesar();
        VigenereInterface vigenereInterface = new VigenereInterface.FakeVigenere();

        while(run)
        {

            System.out.println();
            System.out.println("What would you like to do?");


            System.out.println("1. Encrypt message in Cesar cypher");
            System.out.println("2. Decrypt message in Cesar cypher");

            System.out.println("3. Encrypt message in Vigenere cypher");
            System.out.println("4. Decrypt message in Vigenere cypher");

            System.out.println("5. Test the program");

            System.out.println("0. Exit the program ");

            programInput = scan.nextInt();


            switch (programInput)
            {
                case 1:
                    System.out.println("Enter message to encrypt in Cesar cypher");
                    scan.nextLine();
                    messageToEncrypt = scan.nextLine();
                    System.out.println("Enter shift key");
                    shfk = scan.nextInt();
                    System.out.println("Encrypted message is: "+cesar.cesarEncrypt(messageToEncrypt,shfk));
                    break;

                case 2:
                    System.out.println("Enter message encrypted in Cesar cypher");
                    scan.nextLine();
                    messageToEncrypt = scan.nextLine();
                    System.out.println("Enter shift key");
                    shfk = scan.nextInt();
                    System.out.println("Decrypted message is: "+cesar.cesarDecrypt(messageToEncrypt,shfk));
                    break;

                case 3:
                    System.out.println("Enter message to encrypt in Vigenere cypher");
                    scan.nextLine();
                    messageToEncrypt = scan.nextLine();
                    System.out.println("Enter a key");
                    keyeStr = scan.nextLine();
                    System.out.println("Encrypted message is: "+vigener.vigenereEncrypt(messageToEncrypt,keyeStr));
                    break;

                case 4:
                    System.out.println("Enter message encrypted in Vigenere cypher");
                    scan.nextLine();
                    messageToEncrypt = scan.nextLine();
                    System.out.println("Enter a key");
                    keyeStr = scan.nextLine();
                    System.out.println("Decrypted message is: "+vigener.vigenereDecrypt(messageToEncrypt,keyeStr));
                    break;

                case 5:
                    System.out.println("Testing...");
                    System.out.println(cesarInterface.cesarEncrypt("Test",123));
                    System.out.println(cesarInterface.cesarDecrypt("Test",50));
                    System.out.println(vigenereInterface.vigenereEncrypt("Test","aaa"));
                    System.out.println(vigenereInterface.vigenereDecrypt("Test","abc"));
                    System.out.println("Everything looks fine");
                    break;

                case 0:
                    run=false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Please enter the correct menu option");
                    break;



            }

        }

    }

}

interface CesarInterface{
    public String cesarEncrypt(String mes, int shf);
    public String cesarDecrypt(String mes, int shf);


    class FakeCesar implements CesarInterface
    {
       public String cesarEncrypt(String mes, int shf)
        {
            return "FakeCesarCypherEncrypt";
        }
        public String cesarDecrypt(String mes, int shf)
        {
            return "FakeCesarCypherDecrypt";
        }


    }

}

interface VigenereInterface{

    public  String  vigenereEncrypt(String mes, String k);
    public   String  vigenereDecrypt(String mes, String k);

    class FakeVigenere implements  VigenereInterface
    {
        public  String  vigenereEncrypt(String mes, String k)
        {
            return "FakeVigenereCypherEncrypt";

        }
        public   String  vigenereDecrypt(String mes, String k)
        {
            return "FakeVigenereCypherDecrypt";
        }

    }

}

class CesarCypher implements  CesarInterface{

    private String alphabet;
    private  String message;
    private String encryptMessage;
    private  String decryptMessage;
    private int Shift;


    CesarCypher()
    {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.Shift=0;
        this.message="";
        this.encryptMessage="";
        this.decryptMessage="";


    }



 public  String cesarEncrypt(String mes, int shf)
    {
        this.message=mes;
        this.encryptMessage="";
        this.Shift=shf;
        this.message=this.message.toUpperCase();

        try
        {
            if (this.message.length() <= 0)
            {
                throw new Exception(" Length of message to encrypt cannot be equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG INPUT";

        }

        try
        {
            if (this.Shift <= 0)
            {
                throw new Exception(" Shift key to encrypt cannot be less or  equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG KEY SHIFT";

        }

        for(int i=0; i< message.length(); i++)
        {
            int letterPosition = this.alphabet.indexOf(message.charAt(i));

            int encryptPosition = ((this.Shift + letterPosition) % 26);

            char temp = this.alphabet.charAt(encryptPosition);


            this.encryptMessage+= temp;


        }



        return encryptMessage;
    }

 public  String cesarDecrypt(String mes, int shf)
   {

       this.message=mes;

       this.Shift=shf;
       this.decryptMessage="";
       this.message=this.message.toUpperCase();

       try
       {
           if (this.message.length() <= 0)
           {
               throw new Exception(" Length of message to decrypt cannot be equal to 0");
           }
       }
       catch (Exception e)
       {
           System.out.println(e);
           return "WRONG INPUT";

       }

       try
       {
           if (this.Shift <= 0)
           {
               throw new Exception(" Shift key to decrypt cannot be less or  equal to 0");
           }
       }
       catch (Exception e)
       {
           System.out.println(e);
           return "WRONG KEY SHIFT";

       }


       for(int i=0; i< message.length(); i++)
       {
           int letterPosition = this.alphabet.indexOf(message.charAt(i));

           int encryptPosition = (( letterPosition - this.Shift ) % 26);

           if (encryptPosition < 0){
               encryptPosition = alphabet.length() + encryptPosition;
           }

           char temp = this.alphabet.charAt(encryptPosition);


           this.decryptMessage+= temp;


       }

       return  this.decryptMessage;

   }



}


class VigenereCypher implements  VigenereInterface{

    private String message;
    private String alphabet;
    private String key;
    private String encryptMessage;
    private String decryptMessage;

    VigenereCypher()
    {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.message="";
        this.key="";
        this.encryptMessage="";
        this.decryptMessage="";


    }


  public  String  vigenereEncrypt(String mes, String k)
    {
        this.message=mes;
        this.message=this.message.toUpperCase();
        this.key=k;
        this.key=this.key.toUpperCase();
        encryptMessage="";


        try
        {
            if (this.message.length() <= 0)
            {
                throw new Exception(" Length of the  message to encrypt cannot be equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG INPUT";

        }

        try
        {
            if (this.key.length() <= 0)
            {
                throw new Exception(" Length of key to encrypt cannot be equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG KEY";

        }


        for(int i=0; i<message.length(); i++)
        {

            int encryptPosition = ( this.message.charAt(i) + this.key.charAt(i % this.key.length() )   ) % 26;

            char temp = this.alphabet.charAt(encryptPosition);

            encryptMessage+=temp;
        }

        return encryptMessage;
    }


  public   String  vigenereDecrypt(String mes, String k)
    {
        this.message=mes;
        this.message=this.message.toUpperCase();
        this.key=k;
        this.key=this.key.toUpperCase();
        decryptMessage="";

        try
        {
            if (this.message.length() <= 0)
            {
                throw new Exception(" Length of the message to decrypt cannot be equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG INPUT";

        }

        try
        {
            if (this.key.length() <= 0)
            {
                throw new Exception(" Length of key to decrypt cannot be equal to 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "WRONG KEY";

        }


        for(int i=0; i<message.length(); i++)
        {

            int encryptPosition = ( this.message.charAt(i) - this.key.charAt(i % this.key.length() )  + 26  ) % 26;

            char temp = this.alphabet.charAt(encryptPosition);

            decryptMessage+=temp;
        }

        return decryptMessage;
    }


}

