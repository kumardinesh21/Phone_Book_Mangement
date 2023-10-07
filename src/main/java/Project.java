
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Project {
    static String text = "FolderName.txt";
    Node head;
    class Node{
        int id;
        String name;
        String gmail;
        long number;
        String address;
        Node next;
        //Node Constructor
        Node(int id, String name, String gmail, long number,String address)
        {
            this.id = id;
            this.name=name;
            this.gmail=gmail;
            this.number=number;
            this.address=address;
            this.next=null;

        }
    }
    Scanner sc=new Scanner(System.in);

    public void menuBar(FileWriter x) throws IOException {  // Implementation

        int num; // num is for use switch case

        System.out.println("\n------------------> PHONE MANAGEMENT SYSTEM <-------------------------\n");
        System.out.println("1) Add Contact ");
        System.out.println("2) Edit Contact");
        System.out.println("3) Search ");
        System.out.println("4) Delete ");
        System.out.println("5) View All Records");
        System.out.println("\n------------------>   THE END    <-------------------------\n");
        // asking num (number)
        num = sc.nextInt();

        switch (num) {
            case 1:

                System.out.println("Enter row Id");
                int id = sc.nextInt();
                System.out.println("Enter Your First Name");
                String name = sc.next();
                x.write(System.getProperty( "line.separator" ) +id+", ");
                x.write("Name : " + name + ", ");
                System.out.println("Enter your Gmail address");
                String gmail = sc.next();
                x.write("Gmail : " + gmail + ", ");
                System.out.println("Enter your Number");
                long number = sc.nextLong();
                x.write("Number : " + number + ", ");
                System.out.println("Enter your  address" + " ");
                String address = sc.next();
                x.write("Address : " + address + " ");
                addMethod(id,name, gmail, number, address);
                break;

            case 2:

                Scanner sc = new Scanner(System.in);
                System.out.println( "Enter Row Id For Updating the Contact : ");
                int rid = sc.nextInt();
                System.out.println("Enter Info to Update : \n ");
                System.out.println( "Enter Name: ");
                String uname = sc.next();
                System.out.println( "Enter Email: ");
                String uemail = sc.next();
                System.out.println( "Enter Number:  ");
                long unumber =sc.nextLong();
                System.out.println( "Enter Address: ");
                String uaddress = sc.next();
                String updated = updateContact(rid,uname,uemail,unumber,uaddress);

                if (!updated.isEmpty()){
                    System.out.println("Contact is "+updated);
                }
                else{

                    System.out.println("Invalid Id");
                }

                break;

            case 3:

                System.out.println("Enter Id to Get Contact Details : ");
                sc =new Scanner(System.in);
                int uid= sc.nextInt();
                String contactDetails = getContact(uid);

                if (!contactDetails.isEmpty()){
                    System.out.println("\n-----Contact Info ----- \n"+ contactDetails+"\n");
                }
                else
                {

                    System.out.println("Invalid");
                }

                break;

            case 4:

                sc = new Scanner(System.in);
                System.out.println( "Enter Id to delete the Contact : ");
                int cid = sc.nextInt();
                boolean isDeleted =deleteContact(cid);
                if (isDeleted)
                {
                    System.out.println("Contact Deleted Successfully!");
                }
                else
                {
                    System.out.println("\n>>> Invalid ID");
                }

                System.out.println("\n>>> All Contacts: \n");

                break;

            case 5:

                List<String> allContacts = getAllContacts();
                for (String contact:
                        allContacts
                ) {

                    System.out.println(contact);
                }
                break;

        }
    }
    public static List<String> getAllContacts()
    {

        List<String> contacts = new ArrayList<>();

        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File(text)));
            String line = "";
            while((line  = reader.readLine())!=null)
            {
                contacts.add(line);

            }
            reader.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return contacts;
    }
    public static String getContact(Integer id) {


        List<String> contacts = getAllContacts();
        for (String row : contacts) {
            try {
                String[] data = row.split(",");

                Integer contactId = Integer.parseInt(data[0].trim());
                if (contactId.equals(id)) {
                    return row;
                }
            }
            catch (Exception e)
            {}

        }

        return "";
    }

    public static boolean deleteContact(Integer id)
    {

        List<String> contacts = getAllContacts();
        List<String> records = new ArrayList<>();

        boolean isDeleted= false;
        for (String row : contacts) {
            String[] data = row.split(",");

            try
            {
                Integer contactId = Integer.parseInt(data[0].trim());
                if (!contactId.equals(id)) {
                    try {

                        records.add(row);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                }
                else {

                    isDeleted=true;
                }
            }
            catch (Exception e)
            {

            }

        }

        writeToFile(records);
        return isDeleted;
    }

    public static void writeToFile(List<String> records){
        BufferedWriter bw;


        try{

            new FileWriter(new File(text)).close();


            bw = new BufferedWriter(new FileWriter(new File(text),true));
            for (String row: records){
                bw.append(row);
                bw.newLine();
            }
            bw.flush();
            bw.close();

        }
        catch (IOException e){e.printStackTrace();}

    }

    public static String updateContact(int id, String name, String gmail, long number,String address) throws IOException {
        List<String> contacts = getAllContacts();
        List<String> updateRecord = new ArrayList<>();
        String newCont = "";
        for (String row : contacts) {
            try{
                String[] data = row.split(",");

                Integer contactId = Integer.parseInt(data[0].trim());
                if (contactId.equals(id)) {
                    try {
                        String updateData = contactId+", Name : "+name+", Gmail : "+gmail+", Number : "+number+", Address : "+address;
                        updateRecord.add(updateData);
                        newCont = updateData;

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                }
                else {

                    updateRecord.add(row);
                }
            }
            catch (Exception e){

            }

        }

        writeToFile(updateRecord);

        return newCont;

    }

    public  void addMethod(int id, String name, String gmail, long number,String address)
    {
        Node newNode=new Node(id,name, gmail, number, address);  //Node object


        if (head==null){
            head=newNode;
            //tail=node;
            return;
        }
        else {

            newNode.next = head;
            head = newNode;
        }
    }

    public void print() {
        if (head == null) {
            System.out.println("list is empty");
            return;
        }
        Node currNode = head;
        while (currNode != null) {

            System.out.println("Contact Added: ");
            System.out.println(" \n Name : " + currNode.name + "\n Gmail  :  " + currNode.gmail + " \n Contact NO  : " + currNode.number + "\n Address : " + currNode.address);
            currNode = currNode.next;
        }
    }

    public static void main(String[] args) throws IOException {

        Project project = new Project();  // Project class

        Scanner scan = new Scanner(System.in);

        // exit is for come out from while loop if exit -->0
        int exit;
//         text --> file handling name
        File file= new File (text);
        FileWriter fw;
        //myWriter is obj name
        if (file.exists())
        {
            fw = new FileWriter(file,true);//if file exists append to file. Works fine.
            // here writing file will be close if you are not inserting data


        }
        else
        {
            file.createNewFile();
            fw = new FileWriter(file);

            // here writing file will be close if you are not inserting data

        }
        do {
            //myWriter is obj name
            project.menuBar(fw);

            System.out.println("Do you want to continue? (Press 1 for yes | 0 for exit)");

            exit = scan.nextInt();

        } while (exit != 0);
        fw.close();
    }
}

