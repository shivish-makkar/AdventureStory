
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: Creating the Adventure Story
//Files: AdventureStory.java
//Course: CS 200, Spring 2018-2019
//
//Author: Akarsh B Vasisht
//Email: avasisht@wisc.edu
//Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates
//strangers, etc do. If you received no outside help from either type of
//source, then please explicitly indicate NONE.
//
//Persons: (identify each person and describe their help in detail)
//Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class testRunThing {

/**
* Prompts the user for a value by displaying prompt. Note: This method should not add a new
* line to the output of prompt.
*
* After prompting the user, the method will consume an entire line of input while reading an
* int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
* "Invalid value." terminated by a new line is output and the user is prompted again.
*
* @param sc The Scanner instance to read from System.in.
* @param prompt The name of the value for which the user is prompted.
* @param min The minimum acceptable int value (inclusive).
* @param max The maximum acceptable int value (inclusive).
* @return Returns the value read from the user.
*/
public static int promptInt(Scanner sc, String prompt, int min, int max) {
int resultValue = 0;
boolean valid = false;

System.out.print(prompt);

// This loop ensures that the user is prompted for a seed value until a valid value is
// entered.
// The loop is entered at least once because valid is initialized to false.
while (!valid) {

// This if statement checks for appropriate integer seed values.
if (sc.hasNextInt()) {
resultValue = sc.nextInt();

// The nextLine() implemented below takes care of anything inputed after the integer
// which cleans up the scanner for the next loop.
if (sc.hasNextLine()) {
   sc.nextLine();
}

// This if statement checks if integer input is within a range of values.
if (resultValue >= min && resultValue <= max) {
   valid = true;

   // If the value inputed is an integer value but not within the range this else
   // block is entered.
} else {
   System.out.println("Invalid value.");
   System.out.print(prompt);
}

// Any input which is not an integer enters this else block.
} else {
System.out.println("Invalid value.");
System.out.print(prompt);
sc.nextLine();
}
}
return resultValue;
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
System.out.print(prompt);
String str = sc.nextLine();
// string is converted to lowerCase and whitespace is trimmed. Then the
// the first char of the string is taken and returned
char promptChar = str.toLowerCase().trim().charAt(0);

return promptChar;
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
String userInput = "";

System.out.print(prompt);
// This if statement consumes an entire line of output and trims the string which is to be
// returned
if (sc.hasNextLine()) {
userInput += sc.nextLine().trim();
}
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
// This creates the bookmark file which consists of the bookmark information
File file = new File(bookmarkFile);
// The try-catch blocks handles any IOExceptions when creating the bookmark file
try {
// The PrintWriter is responsible for filling the bookmark file with the bookmark
// details
PrintWriter writer = new PrintWriter(file);

// The next three lines fills the bookmark file with the bookmark information
writer.println(Config.MAGIC_BOOKMARK);
writer.println(storyFile);
writer.println(curRoom);

writer.close();
} catch (IOException excpt) {
return false;
}
return true;
}

/**
* Loads the story and current location from a file either a story file or a bookmark file.
* NOTE: This method is partially implementd in Milestone 2 and then finished in Milestone 3.
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
// The file command loads the story file and which is passed into the scanner as input
File toRead = new File(fName);
Scanner readr = null;
// The try-catch blocks handles:
// 1. IOExceptions when a wrong file name is entered
// 2. Exceptional situations when the first line is neither Config.MAGIC_STORY nor
// Config.MAGIC_BOOKMARK
try {
readr = new Scanner(toRead);
if (!readr.hasNextLine()) {
throw new Exception("Unable to read first line from file: " + fName);
}
String trimmedLineRead = readr.nextLine().trim();
// This if statement throws the second exceptional case mentioned above
if (!trimmedLineRead.equals(Config.MAGIC_STORY)
&& !trimmedLineRead.equals(Config.MAGIC_BOOKMARK)) {
throw new Exception(
   "First line: " + trimmedLineRead + " does not correspond to known value.");
}

// The following if-else blocks calls parseStory or parseBookmark method depending onthe
// first line of the file
if (trimmedLineRead.equals(Config.MAGIC_STORY)) {
return parseStory(readr, rooms, trans, curRoom);
} else if (trimmedLineRead.equals(Config.MAGIC_BOOKMARK)) {
return parseBookmark(readr, rooms, trans, curRoom);
} else {
return false;
}
} catch (IOException e) {
System.out.println("Error reading file: " + fName);
return false;

} catch (Exception excpt) {
System.out.println(excpt.getMessage());
return false;
// The finally block closes the scanner if its value is not null
} finally {
if (readr != null) {
readr.close();
}
}
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

// The following two statements get the bookmark information from the bookmark file
String fileName = sc.nextLine().trim();
curRoom[0] = sc.nextLine().trim();

// The bookmark information is then passed into the parseFile method again to continue the
// game from where it was left off
// The value returned by the parseFile method is also returned by the parseBookmark method,
// i.e. false if there are parsing errors otherwise true
boolean parsingError = parseFile(fileName, rooms, trans, curRoom);

return parsingError;
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
// The following three variables are used to record the stage of parsing
final int START = 0;
final int TRANSITIONS = 1;
final int DESCRIPTIONS = 2;
final int ERROR = 3;

// The empty lines within the file are all ignored except within descriptions, this variable
// is used to keep track of when there are empty lines within the description
boolean newLine = false;

// The parsing always starts with the START state
int state = START;

// The following data structures are the smaller, broken up versions of the larger rooms and
// trans arrayLists
String[] roomArray = new String[Config.ROOM_DET_LEN];
String[] transArray = new String[Config.TRAN_DET_LEN];
ArrayList<String[]> transArrayList = new ArrayList<String[]>();

int counter = 0;
while (sc.hasNextLine()) {
String line = sc.nextLine().trim();
++counter;
// This if statement ignores empty lines except when it is a description. The newLine
// variable is recalled here
if (line.length() == 0) {
if (state == DESCRIPTIONS) {
   newLine = true;
}
continue;
}
// This if statement ignores comments (beginning with #) usually at the start of the
// file to be parsed. Lines starting with # in descriptions are not ignored
else if (line.charAt(0) == '#' && state != DESCRIPTIONS) {
continue;
// This if statement keeps track of the start of room details or the START state
} else if (line.charAt(0) == 'R' && (line.charAt(2) == ':' || line.charAt(3) == ':')) {
state = START;
// This if statement keeps track of the end of the DESCRIPTION state and the start
// of the TRANSITION state
} else if (line.equals(";;;")) {
line = sc.nextLine().trim();
state = TRANSITIONS;
}

// This if statement carries out the functions in the START state
if (state == START) {
transArrayList = new ArrayList<String[]>();

// A new ArrayList is created and put into the trans ArrayList to store transitions
// for every new room
trans.add(transArrayList);
roomArray = new String[Config.ROOM_DET_LEN];

// The roomArray is added to the roomArray to store details of each room
rooms.add(roomArray);

// This if statement extracts the room ID and the room title
if (line.contains(":")) {
   // The number after 'R' and before ':' in a line starting with 'R' is the room
   // ID and is extracted using the substring
   roomArray[Config.ROOM_ID] = line.substring(1, line.indexOf(":")).trim();

   // The remaining line after ':' is the room title and is extracted using the
   // substring
   roomArray[Config.ROOM_TITLE] = line.substring((line.indexOf(":") + 1)).trim();
} else {
   state = ERROR;
}

// The DESCRIPTIONS state follows immediately after the START state gets over
line = sc.nextLine().trim();
++counter;
state = DESCRIPTIONS;

}
// This if statement carries out the functions in the DESCRIPTIONS state
if (state == DESCRIPTIONS) {
// This if statement is for cases when there are descriptions
if (!line.equals(";;;")) {
   // This if statement addresses the empty line identified earlier
   if (newLine) {
       roomArray[Config.ROOM_DESC] += "\n";
       newLine = false;
   }
   // This if statement is entered only when the description index of the array is
   // being filled for the first time
   if (roomArray[Config.ROOM_DESC] == null) {
       roomArray[Config.ROOM_DESC] = line;
       // This else statement is for all the remaining situations after the
       // description
       // is filled for the first time
   } else {
       roomArray[Config.ROOM_DESC] += "\n" + line;
   }
}
// This else statement is for cases when there are no descriptions
else {
   state = TRANSITIONS;
   line = sc.nextLine().trim();
   ++counter;
}
}
// This if statement carries out the functions in the TRANSITIONS state
if (state == TRANSITIONS) {

// The transArray is populated with each transition for each room
transArray = new String[Config.TRAN_DET_LEN];
transArrayList.add(transArray);

while (true) {
   // In cases of success or failure in the game the entire line is added as the
   // transition through this if statement
   if ((line.equals(Config.SUCCESS)) || (line.equals(Config.FAIL))) {
       transArray[Config.TRAN_DESC] = line;
       break;

       // The remaining transitions start with ':' and are added into the ArrayList
       // through this if statement
   } else if (line.charAt(0) == ':') {
       // The transition description is added here
       transArray[Config.TRAN_DESC] =
           line.substring((line.indexOf(":") + 1), line.lastIndexOf("-")).trim();

       // This if statement adds the transition room ID when there is no
       // probability weights
       if (line.lastIndexOf("?") == -1) {
           transArray[Config.TRAN_ROOM_ID] =
               line.substring((line.lastIndexOf(">") + 1)).trim();
           break;

           // This if statement adds the transition room ID when there are
           // probability weights
       } else if (line.lastIndexOf("?") != -1) {
           transArray[Config.TRAN_ROOM_ID] =
               line.substring((line.lastIndexOf(">") + 1), line.lastIndexOf("?"))
                   .trim();
           transArray[Config.TRAN_PROB] =
               line.substring((line.lastIndexOf("?") + 1)).trim();
           break;
       } else {
           state = ERROR;
       }

       // If there is no success or failure in the game yet, the next line is read
       // for the next transition
   } else {
       line = sc.nextLine().trim();
       ++counter;
   }
}
}
// This if statement handles story file format exceptions
if (state == ERROR) {
System.out.println("Error parsing file on line: " + (counter + 1) + ": " + line);
return false;
}
}
// This if statement handles parsing errors
if (rooms.size() != trans.size() || rooms.size() == 0 || trans.size() == 0) {
System.out.println("Error parsing file: rooms or transitions not properly parsed.");
return false;
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
// This for loop runs traverses the rooms ArrayList
for (int i = 0; i < rooms.size(); i++) {
// This if statement returns the index of the room with the given id
if (id.equals(rooms.get(i)[Config.ROOM_ID])) {
return i;
}
}
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
int index = 0;
// Gets the index of the room in the room ArrayList by calling the getRoom Index method
index = getRoomIndex(id, rooms);
// If there returns the array stored in the index 'index' of the rooms ArrayList
if (index != -1) {
String[] roomDetails = rooms.get(getRoomIndex(id, rooms));
return roomDetails;
// If no such id exists, null is returned
} else {
return null;
}
}

/**
* Prints out a line of characters to System.out. The line should be terminated by a new line.
*
* @param len The number of times to print out c.
* @param c The character to print out.
*/
public static void printLine(int len, char c) {
// This for loop iterates len number of times and prints the char c once every iteration
for (int i = 0; i <= len; i++) {
System.out.print(c);
}
// Ends with a newline
System.out.println();
}

/**
*
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
final char NEW_LINE_CHARACTER = '\n';

// This for loop traverses through the string character-by-character
for (i = 0; i < val.length(); ++i) {
// This if statement handles when there is a newline character in the string
if (val.charAt(i) == NEW_LINE_CHARACTER) {
System.out.print(NEW_LINE_CHARACTER);
counter = 0;
}

// This if statement handles the boundary case for each character when the number of
// characters per line reaches its limit
else if (counter == len && i != val.length() - 1) {

// This if statement handles the case when the last character in the line is in
// between a word. In that case a '-' is added and the word is continued on the
// next line
if (val.charAt(i - 1) != NEW_LINE_CHARACTER
   && !Character.isWhitespace(val.charAt(i - 1))
   && !Character.isWhitespace(val.charAt(i))
   && Character.isLetterOrDigit(val.charAt(i))) {

   System.out.print("-" + NEW_LINE_CHARACTER);
   System.out.print(val.charAt(i));
   counter = 1;
}
// This if statement is when the last character in the line is a whitespace
else if (Character.isWhitespace(val.charAt(i))) {
   System.out.print(NEW_LINE_CHARACTER);
   System.out.print(val.charAt(i + 1));
   counter = 1;
   ++i;
}
// This if statement is for cases of special symbols like '.', ''', '1', ','
else if (!Character.isLetterOrDigit(val.charAt(i))) {
   System.out.print(val.charAt(i));
   System.out.print(NEW_LINE_CHARACTER);
   counter = 0;
}
// In cases when a word exactly ends with the character limit this else statement is
// entered
else {
   System.out.print(NEW_LINE_CHARACTER);
   System.out.print(val.charAt(i));
   counter = 1;
}
}
// For regular cases the character is simply printed out using this else statement and
// the counter is incremented
else {
System.out.print(val.charAt(i));
counter += 1;
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
// This try-catch block is to handle null pointer exceptions when there is no room
// description
try {
// This if statement gets the details of the room to be displayed and the calls the
// printLine and the printString method to get the desired output
if (getRoomDetails(id, rooms) != null) {
printLine(Config.DISPLAY_WIDTH - 1, Config.LINE_CHAR);
printString(Config.DISPLAY_WIDTH - 1, getRoomDetails(id, rooms)[Config.ROOM_TITLE]);

System.out.println();

printString(Config.DISPLAY_WIDTH - 1, getRoomDetails(id, rooms)[Config.ROOM_DESC]);

printLine(Config.DISPLAY_WIDTH - 1, Config.LINE_CHAR);
// If no room is returned nothing is printed out
} else {
return;
}
} catch (NullPointerException e) {
System.out.println();
printLine(Config.DISPLAY_WIDTH - 1, Config.LINE_CHAR);
}
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
// The index of the room whose transitions need to be printed out is extracted using the
// getRoomIndex method
// The getRoomIndex method is used as rooms and trans are parallel ArrayLists
int index = getRoomIndex(id, rooms);
// This if statement is for cases where no room is found
if (index == -1) {
return null;
} else {
ArrayList<String[]> transitionList = trans.get(index);

// This if statement is used to print out transitions excluding the success or failure
// cases
if (transitionList.size() != 1
&& !transitionList.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)
&& !transitionList.get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
// This for loop is used to identify transitions without probability weights
for (int i = 0; i < transitionList.size(); i++) {
   if (transitionList.get(i)[Config.TRAN_PROB] != null) {
       continue;
   }
   // Transitions are printed out excluding success, failure and probability weight
   // cases
   System.out.print((i) + ") ");
   System.out.println(transitionList.get(i)[Config.TRAN_DESC]);
}

}
return transitionList;
}
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
int i;
int totalWeight = 0;
int sumWeight = 0;

// This if statement is used to exclude null cases
if (curTrans == null) {
return null;
}
// The try-catch blocks handles NumberFormatExceptions
try {
// This for loop calculates the sum of all the transition probability weights
for (i = 0; i < curTrans.size(); ++i) {
totalWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
}
// This if statement handles the case when the sum of all the transition probability
// weights is zero
if (totalWeight == 0) {
return null;
}

// generates random value
int randomValue = rand.nextInt(totalWeight);

// This for loop returns the required value
for (i = 0; i < curTrans.size(); ++i) {
sumWeight += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
if (sumWeight > randomValue) {
   return curTrans.get(i)[Config.TRAN_ROOM_ID];
}
}
} catch (NumberFormatException excpt) {
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
Scanner scanner = new Scanner(System.in);

// Welcome message
System.out.println("Welcome to this choose your own adventure system!");

char playAgain = 'y';

// This while loop iterates until the user does not want to play another game
while (playAgain != 'n') {
// The following 3 statements get the filename from the user
String playFile = ("Please enter the story filename: ");
String fileName = new String();
fileName = promptString(scanner, playFile);

// All the required data structures are initialized inside the loop in order to reset
// them every time a new game begins
ArrayList<String[]> rooms = new ArrayList<String[]>();
ArrayList<ArrayList<String[]>> trans = new ArrayList<ArrayList<String[]>>();
String[] curRoom = new String[1];
curRoom[0] = "1";

Random rand = new Random(Config.SEED);

boolean success = false;
boolean fail = false;

// This if statement parses the file and proceeds if there are no parsing errors
if (parseFile(fileName, rooms, trans, curRoom)) {

// This while loop iterates until the user wants to quit
while (true) {

   // The output is printed out using the displayRoom and the displayTransitions
   // method
   displayRoom(curRoom[0], rooms);
   ArrayList<String[]> curTrans = displayTransitions(curRoom[0], rooms, trans);
   // If user wins, the game loop exits
   if (curTrans.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)) {
       success = true;
       break;
   }
   // If user loses, the game loop exits
   if (curTrans.get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
       fail = true;
       break;
   }
   // This if statement is for cases when the user has to play the next move
   if (!success && !fail) {
       String probTrans = probTrans(rand, curTrans);
       // If no random id is generated based on probability weights then this if
       // statement is entered
       if (probTrans == null) {
           // The user is prompted to enter desired transition
           int desiredTransition =
               promptInt(scanner, "Choose: ", -2, curTrans.size() - 1);

           // If user enters -1 the program asks users they want to quit
           if (desiredTransition == -1) {
               String quitPrompt = "Are you sure you want to quit the adventure? ";
               char quitChar = promptChar(scanner, quitPrompt);
               // This if statement exits the game if the user types 'y'
               if (quitChar == 'y') {
                   fail = true;
                   break;
               }

               // If user enters -2 the program asks users they want to bookmark
               // current turn
           } else if (desiredTransition == -2) {
               String bookmarkPrompt = "Bookmarking current location: "
                   + curRoom[0] + ". Enter bookmark filename: ";

               String bookmarkFile = promptString(scanner, bookmarkPrompt);

               // This if-else block saves the bookmark or returns error message
               if (saveBookmark(fileName, curRoom[0], bookmarkFile)) {
                   System.out.println("Bookmark saved in " + bookmarkFile);
               } else {
                   System.out.println("Error saving bookmark in " + bookmarkFile);
               }

               // This else block assigns curRoom[0] with the user entered id
           } else {
               curRoom[0] = curTrans.get(desiredTransition)[Config.TRAN_ROOM_ID];
           }

           // If a random id is generated based on probability weights then this
           // else statement is entered and curRoom[0] is assigned with the
           // returned value of the probTrans method for the next transition
       } else {
           curRoom[0] = probTrans;
       }
   }
}
}
// This if statement prints out the message if the user won the game
if (success) {
success = false;
System.out.println("Congratulations! You successfully completed the adventure!");

}
// This if statement prints out the message if the user lost the game
if (fail) {
fail = false;
System.out.println("You failed to complete the adventure. Better luck next time!");

}
// Prompts is the user wants to restart the game
String tryAgain = ("Do you want to try again? ");
playAgain = promptChar(scanner, tryAgain);
}
// exit message
System.out.println("Thank you for playing!");
}
}

