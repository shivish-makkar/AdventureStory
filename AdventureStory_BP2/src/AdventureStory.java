//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: TestAdventureStory
// Files: AdventureStory.java TestAdventureStory.java
// Course: CS 200 Spring 2019
//
// Author: Marc Renault
// Email: mrenault@cs.wisc.edu
// Lecturer's Name: self
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class AdventureStory {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output and the user is prompted again.
     *
     * @param sc     The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min    The minimum acceptable int value (inclusive).
     * @param max    The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int userInput = 0;
        boolean invalid = true;

        // The loop below prompts the user for a value. It returns a value between min and max and
        // keeps on going till the the user provides a valid value.
        do {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                userInput = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.print("Invalid value");
            }


            if (userInput >= min && userInput <= max) {
                return userInput;
            } else {
                System.out.print("Invalid value");
            }

        } while (invalid);

        return 0;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        String userInput = "";
        char returnValue = '\0';
        // Prompts the user for a value
        System.out.print(prompt);

        // gets value from the user and formats it according to the method's requirements
        userInput = sc.nextLine();
        userInput = userInput.toLowerCase();
        userInput = userInput.trim();

        // returns the required values
        if (userInput.isEmpty()) {
            return '\0';
        } else {
            returnValue = userInput.charAt(0);
        }

        return returnValue;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input, removing any leading and 
     * trailing whitespace.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user with leading and trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        // prompts the user for a value, formats it according to specifications and returns the
        // required value.
        System.out.print(prompt);
        String userInput = sc.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    /**
     * Saves the current position in the story to a file.
     *
     * The format of the bookmark file is as follows:
     * Line 1: The value of Config.MAGIC_BOOKMARK
     * Line 2: The filename of the story file from storyFile
     * Line 3: The current room id from curRoom
     *
     * Note: use PrintWriter to print to the file.
     *
     * @param storyFile The filename containing the cyoa story.
     * @param curRoom The id of the current room.
     * @param bookmarkFile The filename of the bookmark file.
     * @return false on an IOException, and true otherwise.
     */
    public static boolean saveBookmark(String storyFile, String curRoom, String bookmarkFile) {
        try {
            // creates FileOutputStream and PrintWriter objects in order to print to file
            FileOutputStream outputStream = new FileOutputStream(bookmarkFile);
            PrintWriter printStream = new PrintWriter(outputStream);

            // added required values to printStream in order to output them in a file
            printStream.println(Config.MAGIC_BOOKMARK);
            printStream.println(storyFile);
            printStream.println(curRoom);
            printStream.close();
        } catch (IOException io) {
            return false;
        }
        return true;
    }

    /**
     * Loads the story and current location from a file either a story file or a bookmark file. 
     * NOTE: This method is partially implemented in Milestone 2 and then finished in Milestone 3.
     * 
     * The type of the file will be determined by reading the first line of the file. The first
     * line of the file should be trimmed of whitespace.
     *
     * If the first line is Config.MAGIC_STORY, then the file is parsed using the parseStory method.
     * If the first line is Config.MAGIC_BOOKMARK, the the file is parsed using the parseBookmark
     * method.
     * Otherwise, print an error message, terminated by a new line, to System.out, displaying: 
     * "First line: trimmedLineRead does not correspond to known value.", where trimmedLineRead is 
     * the trimmed value of the first line from the file. 
     *
     * If there is an IOException, print an error message, terminated by a new line, to System.out,
     * saying "Error reading file: fName", where fName is the value of the parameter.
     *
     * If there is an error reading the first line, print an error message, terminated by a new 
     * line, to System.out, displaying: "Unable to read first line from file: fName", where fName is
     * the value of the parameter. 
     *
     * This method will be partially implemented in Milestone #2 and completed in Milestone #3 as 
     * described below.
     *
     * Milestone #2: Open the file, handling the IOExceptions as described above. Do not read the
     * the first line: Assume the file is a story file and call the parseStory method.
     *
     * Milestone #3: Complete the implementation of this method by reading the first line from the
     * file and following the rules of the method as described above.
     *
     * @param fName The name of the file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms. Since the rooms can have multiple transitions, each room 
     *              will be an ArrayList<String[]> with one String[] per transition with the 
     *              overall structure being an ArrayList of ArrayLists of String[].
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is an IOException or a parsing error. Otherwise, true. 
     */
    public static boolean parseFile(String fName, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        String charRead = "";

        try {
            // gets the input file using FileReader and passes it to a Scanner so that it can be
            // parsed later.
            FileReader inputFile = new FileReader(fName);
            Scanner reader = new Scanner(inputFile);

            // removes whitespace from input
            charRead = reader.nextLine().trim();

            // Checks the string read from scanner and calls the required methods based on the
            // result. Provides user with a message if input is not equal to any known value.
            if (charRead.equals(Config.MAGIC_STORY)) {
                parseStory(reader, rooms, trans, curRoom);
            } else if (charRead.equals(Config.MAGIC_BOOKMARK)) {
                parseBookmark(reader, rooms, trans, curRoom);
            } else {
                System.out.println(
                    "First line: " + charRead + " does not correspond to known" + " value.");
                reader.close();
                return false;
            }

            // catches the exceptions if any is found while implementing the code.
        } catch (IOException exception) {
            System.out.println("Error reading file: " + fName);
            return false;
        } catch (Exception e) {
            System.out.println("Unable to read first line from file: " + fName);
            return false;
        }

        return true;

    }

    /**
     * Loads the story and the current room from a bookmark file. This method assumes that the first
     * line of the file, containing Config.MAGIC_BOOKMARK, has already been read from the Scanner.
     *
     * The format of a bookmark file is as follows:
     * Line No: Contents
     *       1: Config.MAGIC_BOOKMARK
     *       2: Story filename
     *       3: Current room id
     *
     * As an example, the following contents would load the story Goldilocks.story and set the 
     * current room to id 7.
     *
     * #!BOOKMARK
     * Goldilocks.story
     * 7
     *
     * Your method should not duplicate the code from the parseFile method. It must use the
     * parseFile method to populate the rooms and trans methods based on the contents of the story
     * filename read and trimmed from line 2 of the file. The value of for the cell at index 0 of 
     * curRoom is the trimmed value read on line 3 of the file.
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel 
     *              ArrayList to rooms.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */
    public static boolean parseBookmark(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        String currentRoom = "";
        String fileName = "";
        boolean error = true;
        try {
            // Reads the values from scanner, assign it to required variables and finally calls
            // parseFile. The value returned determines if error exists or not in parsing the file.
            fileName = sc.nextLine().trim();
            currentRoom = sc.nextLine().trim();
            curRoom[0] = currentRoom;
            error = parseFile(fileName, rooms, trans, curRoom);

        } catch (Exception e) {
            return false;
        }
        return error;
    }

    /**
     * This method parses a story adventure file.
     *
     * The method will read the contents from the Scanner, line by line, and populate the parallel 
     * ArrayLists rooms and trans. As such the story files have a specific structure. The order of
     * the rooms in the story file correspond to the order in which they will be stored in the 
     * parallel ArrayLists.
     *
     * When reading the file line-by-line, whitespace at the beginning and end of the line should be
     * trimmed. The file format described below assumes that whitespace has been trimmed.
     *
     * Story file format:
     *
     * - Any line (outside of a room's description) that begins with a '#' is considered a comment 
     *   and should be ignored.
     * - Room details begin with a line starting with 'R' followed by the room id, terminated with 
     *   a ':'. Everything  after the first colon is the room title. The substrings of the room id 
     *   and the room title should be trimmed.
     * - The room description begins on the line immediate following the line prefixed with 'R',
     *   containing the room id, and continues until a line of ";;;" is read.
     *   - The room description may be multi-line. Every line after the first one, should be 
     *     prefixed with a newline character ('\n'), and concatenated to the previous description 
     *     lines read for the current room.
     * - The room transitions begin immediately after the line of ";;;", and continue until a line
     *   beginning with 'R' is encountered. There are 3 types of transition lines:
     *   - 1 -- Terminal Transition: A terminal transition is either Config.SUCCESS or 
     *                               Config.FAIL. This room is the end of the story. 
     *                               This value should be stored as a transition with the String at
     *                               index Config.TRAN_DESC set to the value read. The rest of the 
     *                               Strings in the transition String array should be null.
     *                               A room with a terminal transition can only have one transition 
     *                               associated with it. Any additional transitions should result in
     *                               a parse error.
     *   - 2 -- Normal Transition: The line begins with ':' followed by the transition description, 
     *                             followed by " -> " (note the spaces), followed by the room id to 
     *                             transition to. For normal transitions (those without a transition
     *                             weight), set the value at index Config.TRAN_PROB to null.
     *   - 3 -- Weighted Transition: Similar to a normal transition except that there is a 
     *                               probability weight associated with the transition. After the 
     *                               room id (as described in the normal transition) is a '?' 
     *                               followed by the probability weight. 
     *   - You can assume that room ids do not contain a '?'.
     *   - You can assume that Config.SUCCESS and Config.FAIL do not start with a ':'.
     *
     * In the parallel ArrayLists rooms and trans, the internal structures are as follows:
     *
     * The String array structure for each room has a length of Config.ROOM_DET_LEN. The entries in
     * the array are as follows:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * The String array structure for each transition. Note that each room can have multiple 
     * transitions, hence, the ArrayList of ArrayLists of String[]. The length of the String[] is
     * Config.TRAN_DET_LEN. The entries in the String[] are as follows:
     * Index               | Description
     * ------------------------------------------------------------------
     * Config.TRAN_DESC    | The transition description
     * Config.TRAN_ROOM_ID | The transition destination (id of the room) 
     * Config.TRAN_PROB    | The probability weight for the transition
     *
     * If you encounter a line that violates the story file format, the method should print out an 
     * error message, terminated by a new line, to System.out displaying: 
     * "Error parsing file on line: lineNo: lineRead", where lineNo is the number of lines read
     * by the parseStory method (i.e. ignoring the magic number if Milestone #3), and lineRead is 
     * the offending trimmed line read from the Scanner.
     *
     * After parsing the file, if rooms or trans have zero size, or they have different sizes, print
     * out an error message, terminated by a new line, to System.out displaying:
     * "Error parsing file: rooms or transitions not properly parsed."
     *
     * After parsing the file, if curRoom is not null, store the reference of the id of the room at 
     * index 0 of the rooms ArrayList into the cell at index 0 of curRoom. 
     *
     * Hint: This method only needs a single loop, reading the file line-by-line.
     * 
     * Hint: To successfully parse the file, you will need to maintain a state of where you are in 
     *       the file. I.e., are you parsing the description, parsing the transitions; is there an 
             error; etc? One suggestion would be to use an enum to enumerate the different states. 
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details.
     * @param trans The ArrayList structure that will contain the transition details.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true. 
     */
    public static boolean parseStory(Scanner sc, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans, String[] curRoom) {
        String fileDataRead = "";
        String data = "";
        int counter = 0;
        boolean createNewArrayList = true;

        // declared the required ArrayLists and Arrays
        ArrayList<String[]> transArrayList = null;
        String[] roomsArray = null;
        String[] transArray = null;

        // declared and initialized counter variables
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int state = 0;

        // State 0 - comment handling for the file
        // State 1 - rooms ArrayList information filling
        // State 2 - description part of rooms ArrayList information filling
        // State 3 - transition information filling
        try {

            // loop running until no more content is left to read in the file
            while (sc.hasNextLine()) {

                // Initializing counter values inside loop so they are initialized every time
                i = 0;
                j = 1;
                k = 0;
                l = 1;

                // reading lines in the file and removing trailing and leading whitespace.
                fileDataRead = sc.nextLine().trim();
                counter++;

                // assigns states based on the line read
                if (fileDataRead.length() == 0) {
                    continue;
                }

                if (fileDataRead.charAt(0) == '#') {
                    state = 0;
                }
                if (fileDataRead.charAt(0) == 'R') {
                    state = 1;
                }
                if (fileDataRead.charAt(0) != 'R' && fileDataRead.charAt(0) != '#') {
                    state = 3;
                }


                // group of statements to handle comments in the file
                if (state == 0) {

                    // ignores the lines that contain '#' as they are comments
                    while (fileDataRead.charAt(0) == '#' || fileDataRead.isEmpty()) {
                        break;
                    }

                } else if (state == 1) {

                    // creates a new array which contains details to be put in rooms ArrayList
                    roomsArray = new String[Config.ROOM_DET_LEN];

                    // fills in values in data till ':' character appears
                    while (fileDataRead.charAt(j) != ':') {
                        data = data + fileDataRead.charAt(j);
                        j++;
                    }

                    // putting the data at the required index after removing whitespace
                    roomsArray[Config.ROOM_ID] = data.trim();
                    j++;
                    data = "";

                    // putting the rest of values left in the line read in data
                    while (j < fileDataRead.length()) {
                        data = data + fileDataRead.charAt(j);
                        j++;
                    }

                    // putting the data at the required index after removing whitespace
                    roomsArray[Config.ROOM_TITLE] = data.trim();

                    // clearing data variable, making state 2 so that description can be read
                    // and making a boolean variable true which will make sure that a new ArrayList
                    // is created for the transitions in this particular room
                    data = "";
                    state = 2;
                    createNewArrayList = true;

                }

                // group of statements helping handle information filling for rooms array
                if (state == 2) {
                    // reading the next line
                    fileDataRead = sc.nextLine();
                    counter++;

                    // reading multiple lines of data till ";;;" appears and saving that in data
                    while (!fileDataRead.equals(";;;")) {
                        data = data + fileDataRead + "\n";
                        fileDataRead = sc.nextLine();
                        counter++;
                    }

                    // adding data to the array at the required index
                    roomsArray[Config.ROOM_DESC] = data.trim();
                    rooms.add(roomsArray);
                    data = "";
                }

                // group of statements helping handle information filling for rooms array's
                // description part of it
                if (state == 3) {
                    // creating a new ArrayList that will contain the all the transitions for the
                    // current room
                    if (createNewArrayList) {
                        transArrayList = new ArrayList<String[]>();

                        // ensuring that a new ArrayList doesn't get created until a new room is
                        // accessed
                        createNewArrayList = false;
                    }

                    // checks if the line read contains particular characters and assigns those
                    // values to array containing transitions at required indices
                    if (fileDataRead.equals(Config.SUCCESS) || fileDataRead.equals(Config.FAIL)) {
                        // creates a new array to store required values
                        transArray = new String[Config.TRAN_DET_LEN];

                        transArray[Config.TRAN_DESC] = fileDataRead;
                        transArray[Config.TRAN_ROOM_ID] = null;
                        transArray[Config.TRAN_PROB] = null;
                    }

                    // checks if the read line has a particular character
                    else if (fileDataRead.charAt(0) == ':') {

                        // creates a new array to store required values
                        transArray = new String[Config.TRAN_DET_LEN];

                        // checks if the read input contains certain characters in a given order
                        // and if true, adds values around it in data
                        while (fileDataRead.charAt(l) != '-' && fileDataRead.charAt(l + 1) != '>') {
                            data = data + fileDataRead.charAt(l);
                            l++;
                        }

                        // data is removed of whitespace and added to the required index
                        transArray[Config.TRAN_DESC] = data.trim();

                        // clears data variable. Also increments counter l to move the scanner past
                        // '-' and '>' characters
                        data = "";
                        l++;
                        l++;

                        // checks if there is a particular character and if it does, puts the value
                        // in data
                        while (l < fileDataRead.length() && fileDataRead.charAt(l) != '?') {
                            data = data + fileDataRead.charAt(l);
                            l++;
                        }

                        // adds data to the array at the required index
                        transArray[Config.TRAN_ROOM_ID] = data.trim();
                        data = "";
                    }

                    // checks if there is a particular character and if it does, puts the value
                    // in data
                    if (l < fileDataRead.length() && fileDataRead.charAt(l) == '?') {
                        l++;
                        while (l < fileDataRead.length()) {
                            data = data + fileDataRead.charAt(l);
                            l++;
                        }
                        
                        // adds data to the array at the required index
                        transArray[Config.TRAN_PROB] = data.trim();
                        data = "";
                    }
                    
                    // checks if any of the above is implemented and then removes the old
                    // ArrayList from trans to put in the new updated one will all the values
                    if (fileDataRead.charAt(0) == ':' || fileDataRead.equals(Config.SUCCESS)
                        || fileDataRead.equals(Config.FAIL)) {
                        trans.remove(transArrayList);
                        transArrayList.add(transArray);
                    }

                    // puts the updated transArrayList in trans
                    trans.add(transArrayList);

                }
            }
        } 
        // takes care of any exceptions in the code
        catch (Exception e) {
            System.out.println("Error parsing file on line: " + counter + ": " + fileDataRead);
            e.printStackTrace();
            return false;
        }
        if (rooms.size() == 0 || trans.size() == 0 || rooms.size() != trans.size()) {
            System.out.println("Error parsing file: rooms or transitions not properly parsed.");
            return false;
        }
        if (curRoom != null && curRoom.length != 0) {
            curRoom[0] = rooms.get(0)[Config.ROOM_ID];
        }

        return true;
    }

    /**
     * Returns the index of the given room id in an ArrayList of rooms. 
     *
     * Each entry in the ArrayList contain a String array, containing the details of a room. The 
     * String array structure, which has a length of Config.ROOM_DET_LEN, and has the following 
     * entries:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms. 
     * @return The index of the room with the given id if found in rooms. Otherwise, -1.
     */
    public static int getRoomIndex(String id, ArrayList<String[]> rooms) {
        int i = 0;

        // loop that checks which value in the size array is equal to the id and returns the index
        // of that value
        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i)[Config.ROOM_ID].equals(id)) {
                return i;
            }
        }
        // returns -1 if the id doesn't exist in rooms
        return -1;
    }

    /**
     * Returns the room String array of the given room id in an ArrayList of rooms. 
     *
     * Remember to avoid code duplication!
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The reference to the String array in rooms with the room id of id. Otherwise, null.
     */
    public static String[] getRoomDetails(String id, ArrayList<String[]> rooms) {
        // gets roomn's index 
        int roomIndex = getRoomIndex(id, rooms);
        
        // if the id doesn't exists in the rooms, the method returns null
        if (roomIndex == -1) {
            return null;
        }
        
        // method returns details of the room at the roomIndex
        return rooms.get(roomIndex);
    }

    /**
     * Prints out a line of characters to System.out. The line should be terminated by a new line.
     *
     * @param len The number of times to print out c. 
     * @param c The character to print out.
     */
    public static void printLine(int len, char c) {
        // loop to print out character c a total of len times
        
        for (int i = 0; i < len; i++) {
            System.out.print(c);
        }
        
        // ends with a new line
        System.out.println("");
    }

    /**
     * Prints out a String to System.out, formatting it into lines of length no more than len 
     * characters.
     * 
     * This method will need to print the string out character-by-character, counting the number of
     * characters printed per line. 
     * If the character to output is a newline, print it out and reset your counter.
     * If it reaches the maximum number of characters per line, len, and the next character is:
     *   - whitespace (as defined by the Character.isWhitespace method): print a new line 
     *     character, and move onto the next character.
     *   - NOT a letter or digit (as defined by the Character.isLetterOrDigit method): print out the
     *     character, a new line, and move onto the next character.
     *   - Otherwise:
     *       - If the previous character is whitespace, print a new line then the character.
     *       - Otherwise, print a '-', a new line, and then the character. 
     * Remember to reset the counter when starting a new line. 
     *
     * After printing out the characters in the string, a new line is output.
     *
     * @param len The maximum number of characters to print out.
     * @param val The string to print out.
     */
    public static void printString(int len, String val) {

        int i;
        int counter = 0;

        // This loop goes through the string character-by-character
        for (i = 0; i < val.length(); ++i) {
            
            // handles when there is a newline character in the string
            if (val.charAt(i) == '\n') {
                System.out.println("");
                counter = 0;
            }

            // handles the boundary case for characters when the number of
            // characters per line reaches the length len
            else if (counter == len && i != val.length() - 1) {

                // handles the case when the last character in the line is in
                // between a word. In that case a '-' is added and the word is finished being 
                // typed in the next line
                if (val.charAt(i - 1) != '\n'
                    && !Character.isWhitespace(val.charAt(i - 1))
                    && !Character.isWhitespace(val.charAt(i))
                    && Character.isLetterOrDigit(val.charAt(i))) {

                    System.out.println("-");
                    System.out.print(val.charAt(i));
                    counter = 1;
                }
                
                //  handles the condition when last character in the line is a whitespace
                else if (Character.isWhitespace(val.charAt(i))) {
                    System.out.println("");
                    System.out.print(val.charAt(i + 1));
                    counter = 1;
                    ++i;
                }
                
                // handles situations having special symbols.
                else if (!Character.isLetterOrDigit(val.charAt(i))) {
                    System.out.print(val.charAt(i));
                    System.out.println("");
                    counter = 0;
                }
                
                // handles the situation when a word exactly ends with the character limit
                else {
                    System.out.println("");
                    System.out.print(val.charAt(i));
                    counter = 1;
                }
            }
            
            // handles the normal case where the character is simply printed out and counter is 
            // incremented
            else {
                System.out.print(val.charAt(i));
                counter++;
            }
        }
        // ends with newline
        System.out.println();
    }


    /**
     * This method prints out the room title and description to System.out. Specifically, it first
     * loads the room details, using the getRoomDetails method. If no room is found, the method
     * should return, avoiding any runtime errors.
     *
     * If the room is found, first a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output. Followed by the room's title, a new line, and the room's description. Both the title
     * and the description should be printed using the printString method with a maximum length of
     * Config.DISPLAY_WIDTH. Finally, a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is 
     * output.
     *
     * @param id Room ID to display
     * @param rooms ArrayList containing the room details.
     */
    public static void displayRoom(String id, ArrayList<String[]> rooms) {
        // creates and populates a new array containing details for the room
        String[] roomDetails = new String[Config.ROOM_DET_LEN];
        roomDetails = getRoomDetails(id, rooms);

        // doesn't implement method if roomDetails is null 
        if (roomDetails == null) {
            return;
        }

        // prints the required character with the required width
        printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);
        
        // prints the required room details with the required length per line
        printString(Config.DISPLAY_WIDTH, roomDetails[Config.ROOM_TITLE]);
        System.out.println("");
        printString(Config.DISPLAY_WIDTH - 1, roomDetails[Config.ROOM_DESC]);

     // prints the required character with the required width
        printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);
    }

    /**
     * Prints out and returns the transitions for a given room. 
     *
     * If the room ID of id cannot be found, nothing should be output to System.out and null should
     * be returned.
     *
     * If the room is a terminal room, i.e., the transition list is consists of only a single 
     * transition with the value at index Config.TRAN_DESC being either Config.SUCCESS or 
     * Config.FAIL, nothing should be printed out.
     *
     * The transitions should be output in the same order in which they are in the ArrayList, and 
     * only if the transition probability (String at index TRAN_PROB) is null. Each transition 
     * should be output on its own line with the following format:
     * idx) transDesc
     * where idx is the index in the transition ArrayList and transDesc is the String at index 
     * Config.TRAN_DESC in the transition String array.
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param id The room id of the transitions to output and return.
     * @param rooms The ArrayList structure that contains the room details.
     * @param trans The ArrayList structure that contains the transition details.
     * @return null if the id cannot be found in rooms. Otherwise, the reference to the ArrayList of
     *         transitions for the given room.
     */
    public static ArrayList<String[]> displayTransitions(String id, ArrayList<String[]> rooms,
        ArrayList<ArrayList<String[]>> trans) {


        int index = getRoomIndex(id, rooms);

        if (index == -1) {
            return null;
        }

        // if the conditions are met, then a loop is run that outputs the transition details
        if (trans.get(index).size() != 1
            && !trans.get(index).get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)
            && !trans.get(index).get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
            for (int i = 0; i < trans.get(index).size(); i++) {
                if (trans.get(index).get(i)[Config.TRAN_DESC] == null
                    || trans.get(index).get(i)[Config.TRAN_DESC].isEmpty()) {
                    continue;
                }
                System.out.println(i + ") " + trans.get(index).get(i)[Config.TRAN_DESC]);
            }
        }
        return trans.get(index);
    }

    /**
     * Returns the next room id, selected randomly based on the transition probability weights.
     *
     * If curTrans is null or the total sum of all the probability weights is 0, then return null. 
     * Use Integer.parseInt to convert the Strings at index Config.TRAN_PROB of the transition
     * String array to integers. If there is a NumberFormatException, return null.
     *
     * It is important to follow the specifications of the random process exactly. Any deviation may
     * result in failed tests. The random transition work as follows:
     *   - Let totalWeight be the sum of the all the transition probability weights in curTrans.
     *   - Draw a random integer between 0 and totalWeight - 1 (inclusive) from rand.
     *   - From the beginning of the ArrayList curTrans, start summing up the transition probability 
     *     weights.
     *   - Return the String at index Config.TRAN_ROOM_ID of the first transition that causes the 
     *     running sum of probability weights to exceed the random integer.   
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param rand The Random class from which to draw random values.
     * @param curTrans The ArrayList structure that contains the transition details.
     * @return The room id that was randomly selected if the sum of probabilities is greater than 0.
     *         Otherwise, return null. Also, return null if there is a NumberFormatException. 
     */
    public static String probTrans(Random rand, ArrayList<String[]> curTrans) {
        Integer totalWeight = 0;
        Integer totalWeight2 = 0;
        int randomInt = 0;
        if (curTrans == null) {
            return null;
        }
        try {
            
            // loop to find the total probability weight
            for (int i = 0; i < curTrans.size(); i++) {
                totalWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
            }
            
            // random value created between 0 and the total probability weight
            randomInt = rand.nextInt(totalWeight);

            // loop to find the total probability weight again and comparing with the randomly
            // created value
            for (int i = 0; i < curTrans.size(); i++) {
                totalWeight2 += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
                if (totalWeight2 > randomInt) {
                    return curTrans.get(i)[Config.TRAN_ROOM_ID];
                }
            }
            
            //returns null if totalWeight is zero or any other exceptions exit
            if (totalWeight == 0) {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    /**
     * This is the main method for the Story Adventure game. It consists of the main game loop and
     * play again loop with calls to the various supporting methods. This method will evolve over 
     * the 3 milestones.
     * 
     * The Scanner object to read from System.in and the Random object with a seed of Config.SEED 
     * will be created in the main method and used as arguments for the supporting methods as 
     * required.
     *
     * Milestone #1:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #2:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the 
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - Prompt the user for a number between -1 and the number of transitions 
     *                     minus 1, using the promptInt method with a prompt of "Choose: "
     *                   - If the returned value is -1:
     *                      - read a char using promptChar with a prompt of
     *                        "Are you sure you want to quit the adventure? "
     *                      - Set the current room ID to Config.FAIL if that character returned is 'y'
     *                   - Otherwise: Set the current room ID to the room ID at index 
     *                                Config.TRAN_ROOM_ID of the selected transition.
     *                 - Otherwise, the current transition is terminal: Set the current room ID to 
     *                   the terminal state in the transition String array.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a 
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line): 
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #3:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the 
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - If the value returned by the probTrans method is null:
     *                     - Prompt the user for a number between -2 and the number of transitions 
     *                       minus 1, using the promptInt method with a prompt of "Choose: "
     *                     - If the returned value is -1:
     *                        - read a char using promptChar with a prompt of
     *                          "Are you sure you want to quit the adventure? "
     *                        - Set the current room ID to Config.FAIL if that character returned is 
     *                          'y'
     *                     - If the returned value is -2:
     *                        - read a String using the promptString method with a prompt of:
     *                          "Bookmarking current location: curRoom. Enter bookmark filename: ", 
     *                          where curRoom is the current room ID.
     *                        - Call the saveBookmark method and output (terminated by a new line):
     *                           - if successful: "Bookmark saved in fSave"
     *                           - if unsuccessful: "Error saving bookmark in fSave"
     *                       where fSave is the String returned by promptString.
     *                     - Otherwise: Set the current room ID to the room id at index 
     *                                  Config.TRAN_ROOM_ID of the selected transition.
     *                   - Otherwise, the value returned by probTrans is not null: make this value
     *                     the current room ID.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL.
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a 
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line): 
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        char requiredCharacter = '\0';
        String fileName = "";
        ArrayList<String[]> rooms = null;
        ArrayList<ArrayList<String[]>> trans = null;
        String[] curRoom = null;
        // curRoom[0] = "1";
        String curRoomId = "";
        ArrayList<String[]> curTransition = null;
        int returnedIntVal = 0;
        char returnedCharValue = '\0';
        boolean loop = true;
        String bookmarkingFile = "";
        Random rand = new Random(Config.SEED);
        String probTransVal = "";


        System.out.println("Welcome to this choose your own adventure system!");

        // loop that will allow the user to go through another story after finishing one
        while (loop) {
            
            // creating new ArrayLists and Arrays inside the loop so that a new one exist for every
            // new story
            rooms = new ArrayList<String[]>();
            trans = new ArrayList<ArrayList<String[]>>();
            curRoom = new String[1];
            curTransition = new ArrayList<String[]>();
            
            // prompts user for fileName
            fileName = promptString(scnr, "Please enter the story filename: ");
            
            // checking if the file is parsed
            if (parseFile(fileName, rooms, trans, curRoom)) {
                curRoomId = curRoom[0];
                
                // implemented a loop that will run until the user either successes or fails
                do {
                    // displays the room and transition options
                    displayRoom(curRoomId, rooms);
                    curTransition = displayTransitions(curRoomId, rooms, trans);

                    // these statements will only run if the user hasn't succeeded or failed yet
                    if (!curTransition.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)
                        && !curTransition.get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
                        
                        // calling a function that might be used for transitions based on 
                        // probability
                        probTransVal = probTrans(rand, curTransition);
                        
                        // these statements run if no probability weight is associated with 
                        // transitions in the room
                        if (probTransVal == null) {
                            
                            // returns a value in accordance with user's choice of transition
                            returnedIntVal =
                                promptInt(scnr, "Choose: ", -2, (curTransition.size()) - 1);
                            
                            // next few statements perform tasks based on that return value
                            if (returnedIntVal == -1) {
                                returnedCharValue = promptChar(scnr,
                                    "Are you sure you want to quit the adventure? ");
                                if (returnedCharValue == 'y') {
                                    curRoomId = Config.FAIL;
                                    break;
                                } else if (returnedIntVal == -2) {
                                    bookmarkingFile =
                                        promptString(scnr, "Bookmarking current location: "
                                            + curRoomId + ". Enter bookmark filename: ");
                                    if (saveBookmark(fileName, curRoomId, bookmarkingFile)) {
                                        System.out.println("Bookmark saved in " + bookmarkingFile);
                                    } else {
                                        System.out
                                            .println("Error saving bookmark in " + bookmarkingFile);
                                    }
                                }
                            } else {
                                
                                // assigns curRoomId with the one user gave
                                curRoomId = curTransition.get(returnedIntVal)[Config.TRAN_ROOM_ID];
                            }
                        } else {
                            
                            // assigns currRoomId with the one returned from probTrans if it has 
                            // non null probabilities
                            curRoomId = probTransVal;
                        }
                    }
                } 
                
                // check to understand when to exit loop
                while (!curTransition.get(0)[Config.TRAN_DESC].equals(Config.FAIL)
                    && !curTransition.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS));

                // check if user won or lost and these statements prints out statements 
                // accordingly
                if (curTransition.get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
                    System.out
                        .println("You failed to complete the adventure. Better luck next time!");

                } else {
                    System.out
                        .println("Congratulations! You successfully completed the adventure!");
                }
            }

            // user prompt to try another story file
            requiredCharacter = promptChar(scnr, "Do you want to try again? ");

            // until user enters 'n', the loop will keep on asking to try again
            if (requiredCharacter == 'n') {
                System.out.println("Thank you for playing!");
                loop = false;
            }
        }
    }
}

