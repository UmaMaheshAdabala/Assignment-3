import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.*;
class Assign{
    Scanner sc=new Scanner(System.in);
    ArrayList<ArrayList<String>> str1=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> str2=new ArrayList<ArrayList<String>>();
    ArrayList<Integer> str3=new ArrayList<>();
    ArrayList<Integer> str4=new ArrayList<>();
    void read(){
    try {
        Scanner s1=new Scanner(new FileReader("menuList.csv"));
        Scanner s2=new Scanner(new FileReader("orderDetails.csv"));
        String s;
        while(s1.hasNext()){
            s=s1.nextLine();
            String[] str=s.split(",");
            List<String> d=Arrays.asList(str);
            ArrayList<String> str11=new ArrayList<>(d);
            
            str1.add(str11);
        }
        while(s2.hasNext()){
            String ss=s2.nextLine();
            String[] str1=ss.split(",");
            List<String> d1=Arrays.asList(str1);
            ArrayList<String> str21=new ArrayList<>(d1);
            str2.add(str21);

        }
    }
    catch (Exception e) {
        System.out.println("Running Error");
    }
    }
    void display(ArrayList<ArrayList<String>>str1){
        int n=str1.size();
        for(int i=0;i<n;i++){
            ArrayList<String> d1=str1.get(i);
            for (String string : d1) {
                System.out.print(string+" ");                
            }
            System.out.println();
        }
    }
    void menulst(){
        String arr[]={"Generate new bill","View the total collection for today","Cancel the bill"};
        int n=arr.length;
        for(int i=0;i<n;i++){
            System.out.print(i+1+"-"+arr[i]+"\n");
        }
        System.out.print("Enter Your Choice: ");
    }
    void generate(){
        System.out.println("Generate new bill");
        display(str1);
        orderdet();
    }
    void collect(){
        sc.nextLine();
        System.out.println("View the total Amount collected for today");
        System.out.print("Enter date: DD-MMM-YY");
        String s=sc.nextLine();
        Double collect=0.0;
        for(int i=0;i<(str2.size());i++){
            ArrayList<String> s1=str2.get(i);
            if((s1.get(1)).equals(s)){
                Double b=Double.parseDouble(s1.get(2));
                collect+=b;
                System.out.println(s1+"\n");
            }  
        }
        System.out.println("Total Amount collected for the day: "+collect);

    }
    void cancel(){
        System.out.println("Cancel the bill:: ");
        display(str2);
        System.out.print("Enter the id in Above list:: ");
        int n=sc.nextInt();
        int t=str2.size();
        if(n>t){
            System.out.println("Enter ValidId");
        }
        else{
            n=n-1;
            (str2.get(n)).set(4, "cancelled");
            try {
                FileWriter objq=new FileWriter("orderDetails.csv",false);
                for(int i=0;i<(str2.size());i++){
                    ArrayList<String>str1=str2.get(i);
                    String str11=String.join(",",str1);
                    str11+="\n";
                    FileWriter objq1=new FileWriter("orderDetails.csv",true);
                    objq1.write(str11);
                    objq1.close();
                }
                    objq.close();
                    display(str2);
                    System.out.println("Oreder Cancelled");
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error");
            }
        }


    }
    void details(){
        int n=sc.nextInt();
        switch (n) {
            case 1:
                generate();
                break;
            case 2:
                collect();
                break;
            case 3:
                cancel();
                break;
        
            default:
                System.out.println("Enter Valid Number");
                break;
        }
    }
    void orderdet(){
        System.out.print("Enter OrderId: ");
        int n=sc.nextInt();
        System.out.print("Enter Quantity: ");
        int m=sc.nextInt();
        str3.add(n);
        str4.add(m);
        System.out.print("If you to order again Yes:- press 'y' or No:- press 'n': ");
        char ch=sc.next().charAt(0);
        if(ch=='y'){
            orderdet();
        }
        else{
            int ordercount=str3.size();
            Double total=0.0;
            System.out.print("Check Details: ");
            for(int i=0;i< ordercount;i++){
                int k=str3.get(i);
                int l=str4.get(i);
                ArrayList<String> d12=str1.get(k-1);
                Double a=Double.parseDouble(d12.get(2));
                total+=a*l;
                System.out.println(str3.get(i)+" ");
            }
            System.out.println("total:  "+total);
            System.out.print("Do you want to confirm order Yes:-Press 'y' or No:- press 'n': ");
            char ch1=sc.next().charAt(0);
            if(ch1=='y'){
                String s=",";
                LocalDate date1= LocalDate.now();
                DateTimeFormatter obj22=DateTimeFormatter.ofPattern("DD-MMM-YY");
                String date=date1.format(obj22);
                int x=str2.size()+1;
                String a=x+s+date+s+total+.00+s;
                for(int i=0;i<(str3.size());i++){
                    a+=str3.get(i)+" ";
                    a+=str4.get(i)+" ";
                }
                a+=s+"Approved";
                try {
                    File newn=new File("orderDetails.csv");
                    FileWriter obj2=new FileWriter(newn,true);
                    obj2.write("\n"+a);
                    obj2.close();
                } catch (Exception e) {
                    
                    System.out.println("Error");
                }
                
                System.out.println("Thank You for Visiting");
            }
            else{
                menulst();
                details();
            }      
            
        }
    }
    
}
public class Assign_3 {
 public static void main(String[] args) {
    Assign ass=new Assign();
    ass.read();
    ass.menulst();
    ass.details();  
    
 }   
}