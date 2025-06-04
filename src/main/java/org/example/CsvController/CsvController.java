package org.example.CsvController;

import org.example.Model.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvController {

    //final String filepath = "C://Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output/allcustomers.csv";
    //final String outputpath = "C://Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output/outputFile.csv";
    //final String transactionHistoryPath = "C:/Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output/transactionsHistory/transactionsHistory.csv";
    final String filepath = "src/main/java/org/example/Output_files/allcustomers.csv";
    final String outputpath = "src/main/java/org/example/Output_files/outputFile.csv";
    final String transactionHistoryPath = "src/main/java/org/example/Output_files/transactionsHistory/transactionsHistory.csv";


    public void writeCustomersToCsv(Customer customer)
            throws IOException {

        //String outputPath = "C://Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output";

        FileWriter writer = new FileWriter(filepath, true);
        String customerId = String.valueOf(customer.getId());
        String customerName = customer.getUser();
        String customerFunds = String.valueOf(customer.getMoneyInCents());
        writer.write(customerId + " , " + customerName+ " , " + customerFunds  + "\n");
        writer.close();
      //  Writer writer = Files.newBufferedWriter(Paths.get(filePath));
       // CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Id", "user", "money"));

        //printer.print(customer);
            // PROBABLY NEEDS TO BE DIFFERENT. Also, i NEED TO CREATE FILE IN ANOTHER METHOD
    }

    // this should be in transaction controller class
    public static boolean checkIfSenderAndReceiverAreDifferentCustomers(int expectedSenderId, String expectedSenderName, int expectedReceiverId, String expectedReceiverName) {
        return expectedSenderId != expectedReceiverId && !Objects.equals(expectedSenderName, expectedReceiverName);
    }

    public void addTransactionDataToTransactionRecords(Customer senderCustomer, Customer receiverCustomer, int sentAmount) throws IOException {

        FileWriter writer = new FileWriter(transactionHistoryPath, true);

        String senderId = String.valueOf(senderCustomer.getId());
        String senderName = senderCustomer.getUser();
        String receiverId = String.valueOf(receiverCustomer.getId());
        String receiverName = receiverCustomer.getUser();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd | HH.mm.ss").format(new java.util.Date());

        writer.write("Sender ID: " +  senderId + ", name: " + senderName + " , " + "Receiver ID: " + receiverId + ", name: " + receiverName + " , " + "Amount sent: " + sentAmount + " , " + timeStamp + "\n");
        writer.close();
    }

    public List<List<String>> returnCsvFile(String filepath) throws IOException {

        //FileReader reader = new FileReader(filePath);

        List<List<String>> records;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            records = reader.lines()
                    .map(line -> Arrays.asList(line.split("\n")))
                    .toList();
        }
        return records;
    }

    public Customer returnCustomerFromCsvFile(int customerIdParameter) throws IOException {

        List<List<String>> records;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            records = reader.lines()
                    .map(line -> Arrays.asList(line.split("\n")))
                    .toList();
        }

        String returnedRecord = splitListConvertToInt(records, customerIdParameter);
        String[] recordSplit = returnedRecord.split(",");

        String id = recordSplit.length > 0 ? recordSplit[0] : null;;
        assert id != null;
        id= id.replaceAll("[^\\p{L}\\p{N}]", "");
        recordSplit[0] = id;
        String customerName = recordSplit[1];
        customerName = customerName.replaceAll("[^\\p{L}\\p{N}]", "");
        recordSplit[1] = customerName;
        String money = recordSplit[2];
        money = money.replaceAll("[^\\p{L}\\p{N}]", "");
        recordSplit[2]= money;
        int idInt = Integer.parseInt(id);
        int moneyInt = Integer.parseInt(money);

        return new Customer(idInt, customerName, moneyInt);
    } // method will return array error if customer Id number is incorrect (doesn't belong to array) should make a handler

    public void writeCustomerAmountBackToNewCsvFile(Customer updatedCustomer) throws IOException {

        int customerId = updatedCustomer.getId();
        String customerNameUpdated = updatedCustomer.getUser();
        int customerFundsUpdated = updatedCustomer.getMoneyInCents();
        String ArrayUpdatedCustomer = customerId + " , " + customerNameUpdated + " , " + customerFundsUpdated;

        FileWriter writer = new FileWriter(outputpath, true);

        List<List<String>> records;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            records = reader.lines()
                    .map(line -> Arrays.asList(line.split("\n")))
                    .toList();   // how do I skip empty lines?
        }

        Iterator<List<String>> iterator = records.iterator();
        while (iterator.hasNext()) {
            List<String> customerRecordArray = iterator.next();
            Iterator<String> iterator2 = customerRecordArray.iterator();
            while (iterator2.hasNext()) {
                String customerRecord = String.valueOf(iterator2.next());

                String[] recordSplit = customerRecord.split(",");
                //String id = recordSplit[0]

                String id = recordSplit.length > 0 ? recordSplit[0] : null;
                int idInt = 0;
                if (id == null || id.trim().isEmpty()) {
                    System.out.println("empty here");    // might break here because of some reason
                } else {
                    String digits = id.replaceAll("\\D+", "");
                    if (digits.isEmpty()) {
                        System.out.println("empty here too");
                    }
                    else {
                        idInt = Integer.parseInt(digits);
                    }
                }
                if (idInt == customerId) {
                    customerRecord = ArrayUpdatedCustomer;
                }
                writer.write(customerRecord + "\n");
            }
        }
        writer.close();

    }

    public File deleteOldFileAndRenameNewOne() {

        File oldfile = new File(filepath);
        File newfile = new File(outputpath);
        File tempfile = new File("src/main/java/org/example/Output_files/temp.csv");
        File tempfilenew = new File(filepath);
        boolean renameOld = oldfile.renameTo(tempfile);
        if (renameOld) {
            boolean renameNew = newfile.renameTo(tempfilenew);
            if (renameNew) {
                tempfile.delete();
                newfile = oldfile;
            }
        }
        return newfile;
        // URL url = getClass().getResource("ListStopWords.txt");
        //File file = new File(url.getPath()); use some of this maybe
    }

    public String splitListConvertToInt(List<List<String>> records, int customerIdParameter) {
        Iterator<List<String>> iterator = records.iterator();
        String customerRecordFound = "";
        int idInt = 0;
        while (iterator.hasNext()) {
            List<String> customerRecordArray = iterator.next();
            Iterator<String> iterator2 = customerRecordArray.iterator();
            while (iterator2.hasNext()){
                String customerRecord = String.valueOf(iterator2.next());

                String[] recordSplit = customerRecord.split(",");
                //String id = recordSplit[0]

                String id = recordSplit.length > 0 ? recordSplit[0] : null;
                if (id == null || id.trim().isEmpty()) {
                    return "empty1";
                } else {
                    String digits = id.replaceAll("\\D+", "");
                    if (digits.isEmpty()) {
                        return "empty2";
                    }
                    else {
                        idInt = Integer.parseInt(digits);
                    }
                }
                if (idInt == customerIdParameter) {
                    customerRecordFound = customerRecord;
                    return customerRecordFound;
                }
            }
        }
        return "empty3";
    }
    // check if both files carry same content, rename the file, delete the old file.

    public String returnCustomerFromCsvFile2(int customerIdParameter) throws IOException {

        //   String customerId = Integer.toString(customerIdParameter);

        List<List<String>> records;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            records = reader.lines()
                    .map(line -> Arrays.asList(line.split("\n")))
                    .toList();
        }

        String returnedRecord = splitListConvertToInt(records, customerIdParameter);

        return returnedRecord;
    }

    public void saveTransactionToCustomerCsv(Customer senderCustomer, Customer receiverCustomer, int sentAmount) {

        String senderName = senderCustomer.getUser();
        String receiverName = receiverCustomer.getUser();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd | HH.mm.ss").format(new java.util.Date());

        String senderTransactionPath = "src/main/java/org/example/Output_files/customerTransactions/" + senderName + ".csv";

        FileWriter writer = null;
        try {
            writer = new FileWriter(senderTransactionPath, true);
            writer.write("You have sent: " + sentAmount + ",  to " + receiverName + ", | " + timeStamp + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String receiverTransactionPath = "src/main/java/org/example/Output_files/customerTransactions/" + receiverName + ".csv";

        try {
            FileWriter writer2 = new FileWriter(receiverTransactionPath, true);
            writer2.write("You have received: " + sentAmount + ",  from " + senderName + ", | " + timeStamp + "\n");
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int readWriteId() throws IOException {

        String customerNumberPath = "src/main/java/org/example/Output_files/customernumber_id.csv";
        int id = 0;

        Path path = Paths.get(customerNumberPath);
        if (!Files.exists(path)) {
            FileWriter writer = new FileWriter(customerNumberPath, false);

        }
        String readId = Files.readString(path);

        if (!readId.isEmpty()){
             id = Integer.parseInt(readId);
             id = id + 1;
        }else {
            id = 1;
        }
        String writeId = Integer.toString(id);
        FileWriter writer = new FileWriter(customerNumberPath, false);
        writer.write(writeId);
        writer.close();

        return id;
    }

    public boolean createUpdateFriendsList(Customer senderCustomer, Customer receiverCustomer) throws IOException {
        String senderName = senderCustomer.getUser();
        String receiverName = receiverCustomer.getUser();

        String senderFriendsList = "src/main/java/org/example/Output_files/friends/" + senderName + " friends.csv";
        String receiverFriendsList = "src/main/java/org/example/Output_files/friends/" + receiverName + " friends.csv";


        FileWriter writer = new FileWriter(senderFriendsList, true);
        writer.write(receiverName);
        writer.close();
        FileWriter writer2 = new FileWriter(receiverFriendsList, true);
        writer2.write(senderName);
        writer2.close();

        Path path = Paths.get(senderFriendsList);
        Path path2 = Paths.get(receiverFriendsList);

        if (Files.exists(path) && Files.exists(path2)) {
            return true;
        } else return false;
    }

    public boolean createUpdateMessageBox(Customer senderCustomer, Customer receiverCustomer, String message) throws IOException {
        String senderName = senderCustomer.getUser();
        String receiverName = receiverCustomer.getUser();

        String receiverMessageBox = "src/main/java/org/example/Output_files/messages/" + receiverName + " messages.csv";
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd | HH.mm.ss").format(new java.util.Date());


        FileWriter writer = new FileWriter(receiverMessageBox, true);
        writer.write(message + " from -   " + senderName + " , at " + timeStamp + "\n");
        writer.close();

        Path path = Paths.get(receiverMessageBox);

        if (Files.exists(path)) {
            return true;
        }
        else return false;
    }
}

