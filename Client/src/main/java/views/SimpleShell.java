package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public SimpleShell() throws IOException {
    }


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        IdController idController = new IdController();
        MessageController messageController = new MessageController();

        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }
            if(commandLine.equalsIgnoreCase("CreateId")){
                System.out.println("whats your name? ");
                String idName = console.readLine();
                System.out.println("whats your github? ");
                String github = console.readLine();
                Id id = new Id(idName, github, "one");
                idController.postId(id);
                System.out.println("Id created successfully");
                continue;
            }
            if(commandLine.equalsIgnoreCase("GetMessages")){

            }
            if (commandLine.equalsIgnoreCase("SendMessage")){
                System.out.println("Who would you like to send this to?");
                String toId = console.readLine();
                System.out.println("What is your message? ");
                String sentMessage = console.readLine();
                Message message = new Message(idController.findByGitId("JayW").getUserID(),toId,sentMessage);
                messageController.postMessage(idController.findByGitId("JayW"), idController.findByGitId(toId), message);






                System.out.println("Message sent Successfully");
                continue;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
//                if (list.contains("ids")) {
//                    String results = webber.getids();
//                    SimpleShell.prettyPrint(results);
//                    continue;
//                }
//
//                // messages
//                if (list.contains("messages")) {
//                    String results = webber.get_messages();
//                    SimpleShell.prettyPrint(results);
//                    continue;
//                }
                // you need to add a bunch more.
//                if (list.contains("messages") && list.size() == 2) {
//                    IdController j = new IdController();
//                    Id foundId = j.findById(list.get(idController.findByGitId("")));
//                    if (foundId == null) {
//                        continue;
//                    }
//                    MessageController m = new MessageController();
//                    SimpleShell.prettyPrint(m.getMessagesForId(foundId));
//                    continue;
//                }

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}