package org.example.CsvControllerTests;

import org.example.Controller.MeController;
import org.example.Controller.TransactionController;
import org.example.Model.Customer;
import org.example.CsvController.CsvController;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvControllerTest {
    CsvController csvcontroller = new CsvController();
    Customer expectedsenderCustomer = new Customer(10, "york", 4000);
    Customer expectedreceiverCustomer = new Customer(6, "joshua", 2000);
    CsvController csvtransactions = new CsvController();
    CsvController csvcustomertransactions = new CsvController();
    int idSenderCustomer = 54;

    String outputpath = "src/main/java/org/example/Output_files/outputFile.csv";
    String transactionHistoryPath = "src/main/java/org/example/Output_files/transactionsHistory/transactionsHistory.csv";
    final String filePath = "src/main/java/org/example/Output_files/allcustomers.csv";

    @Test
    public void writeCustomersToCsvTest() throws IOException { // has to make sure, that writes in a new line, checks if Id, name doesn't repeat.
        //what do I test here? the fact, that it takes an object, writes it to csv.

        Customer customer = new Customer(999, "andrew had", 8000);

        // File tempFile = File.createTempFile("C://Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output/test", ".csv");
        // String path = tempFile.getAbsolutePath();

        csvcontroller.writeCustomersToCsv(customer);
        String content = Files.readString(Path.of(filePath));

        assertThat(content).contains(customer.getId() + " , " + customer.getUser() + " , " + customer.getMoneyInCents() + "\n");
    }

    @Test
    public void returnCsvFileTest() throws IOException {

        CsvController readertocsv = new CsvController();

        List<List<String>> returnedFileInArrayList = readertocsv.returnCsvFile(filePath);
        // String CsvLine = "50 , ss , 5000";

        List<String> line1 = new ArrayList<>(List.of("5 , johhny , 5000"));
        List<String> line2 = new ArrayList<>(List.of("50 , ss , 5000"));
        List<String> line3 = new ArrayList<>(List.of("54, bb, 3000"));
        List<List<String>> expectedCsvList = new ArrayList<>(List.of(line1, line2, line3));

        assertThat(returnedFileInArrayList).isEqualTo(expectedCsvList);
    }

    @Test
    public void returnCustomerFromCsvFileTest() throws IOException {

        CsvController readertocsv = new CsvController();
        Customer actualcustomer = readertocsv.returnCustomerFromCsvFile(21);
// I totally forgot, it returns different object.

        int actualId = actualcustomer.getId();
        String actualName = actualcustomer.getUser();
        int actualMoney = actualcustomer.getMoneyInCents();

        assertThat(actualId + " " + actualName + " " + actualMoney).isEqualTo(" 54 , bb , 4100");
    }

    @Test
    public void returnCustomerFromCsvFileTest2() throws IOException {

        CsvController readertocsv = new CsvController();

        String returned = readertocsv.returnCustomerFromCsvFile2(54);
        assertThat(returned).isEqualTo("54, bb, 4100");

    }

    @Test
    public void checkIfSenderAndReceiverAreDifferentCustomersTest() {
        Customer expectedReceiverCustomer = new Customer(55, "bb4", 3000);

        int expectedSenderId = expectedsenderCustomer.getId();
        String expectedSenderName = expectedsenderCustomer.getUser();
        int expectedReceiverId = expectedReceiverCustomer.getId();
        String expectedReceiverName = expectedReceiverCustomer.getUser();

        boolean returnResult = CsvController.checkIfSenderAndReceiverAreDifferentCustomers(expectedSenderId, expectedSenderName, expectedReceiverId, expectedReceiverName);

        assertThat(returnResult).isTrue();
    }

    @Test
    public void createCsvTransactionHistoryFileForEachCustomer() {

    }

    @Test
    public void addTransactionDataToTransactionRecordsTest() throws IOException {
        // try to use TransactionRecords file for this.
        // create new Csv in a new directory to save each transaction that happens.
        // sender: id, username, amount ; receiver: id, username, amount
        int sentAmount = 300;
        csvcontroller.addTransactionDataToTransactionRecords(expectedsenderCustomer, expectedreceiverCustomer, sentAmount);
        String content = Files.readString(Path.of(transactionHistoryPath));

        assertThat(content).contains("Sender ID: senderId , name: senderName | Receiver ID: receiverId , name: receiverName | Amount sent: sentAmount | timeStamp");
    }

    @Test
    public void writeCustomerAmountBackToNewCsvFileTest() throws IOException {
        Customer expectedCustomerWithNewAmount = new Customer(60, "bbtty", 4100); //

        int expectedCustomerNewAmount = expectedCustomerWithNewAmount.getMoneyInCents();
        int expectedCustomerId = expectedCustomerWithNewAmount.getId();

        csvtransactions.writeCustomerAmountBackToNewCsvFile(expectedCustomerWithNewAmount);

        String content = Files.readString(Path.of(outputpath));

        assertThat(content).contains(expectedCustomerWithNewAmount.getId() + " , " + expectedCustomerWithNewAmount.getUser() + " , " + expectedCustomerWithNewAmount.getMoneyInCents() + "\n");
    }

    @Test
    public void deleteOldFileAndRenameNewOneTest() {
        CsvController csvcontroller = new CsvController();

        File file = csvcontroller.deleteOldFileAndRenameNewOne();

        //assertThat(file.getPath()).isEqualTo("C://Users/jacsi/Desktop/Projects/JAVA SPRINGBOOT LEARN/output/allcustomers.csv");
        assertThat(file.getPath()).isEqualTo("    src/main/java/org/example/Output_files/allcustomers.csv");
    }

    @Test
    public void splitListConvertToIntTest() {
        List<String> line1 = new ArrayList<>(List.of("5 , johhny , 5000"));
        List<String> line2 = new ArrayList<>(List.of("50 , ss , 5000"));
        List<String> line3 = new ArrayList<>(List.of("54, bb, 7500"));
        List<List<String>> expectedCsvList = new ArrayList<>(List.of(line1, line2, line3));

        String actualValue = csvcontroller.splitListConvertToInt(expectedCsvList, 50);

        assertThat(actualValue).isEqualTo("54, bb, 7500");
    }

    @Test
    public void saveTransactionOrDepositToCustomerCsvTest() throws IOException {

        int sentAmount = 500;
        csvcustomertransactions.saveTransactionToCustomerCsv(expectedsenderCustomer, expectedreceiverCustomer, sentAmount);
        String content = Files.readString(Path.of(transactionHistoryPath));  // incorrect, need to put other stuff here.

        assertThat(content).contains(" ");

        // probably should do some extra checks after, if everything went smoothly and.
        // how to check if no other data was deleted or altered?
    }

    @Test
    public void createUpdateFriendsListTest() throws IOException {

        boolean listCreated = csvcontroller.createUpdateFriendsList(expectedsenderCustomer, expectedreceiverCustomer);

        assertThat(listCreated).isTrue();
    }

   @Test
    public void createUpdateMessageBoxTest() throws IOException {

        String message = "Hey dude, how are you? ";
        boolean mBoxUpdated = csvcontroller.createUpdateMessageBox(expectedsenderCustomer, expectedreceiverCustomer, message);

        assertThat(mBoxUpdated).isTrue();
   }


}


  /*
   @Test
    public void returnCustomerByNameTest(){
        String customerName = "york";
        csvcontroller.returnCustomerByName
   }
*/