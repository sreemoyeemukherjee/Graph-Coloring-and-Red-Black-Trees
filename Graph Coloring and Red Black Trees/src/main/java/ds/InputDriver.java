/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

import java.io.*;
import java.util.*;


// Driver class to read input files and print results to result.txt
public class InputDriver {
    static RedBlackTree rbtree = new RedBlackTree();    // stores the red black tree dictionary
    static boolean[] marked;    // shades i.e. marks true if vertex has been colored else false
    static int totalVertices = 0;   // total vertices in graph

    public static void main(String args[]) {
        String allLines = "";   // stores file content
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String line;
            line = in.readLine();
            while (line != null) {
                processLine(line);
                allLines = allLines + line + "\n";
                line = in.readLine();
            }
            totalVertices++; // since value starts at 0
            marked = new boolean[totalVertices];
            Graph graph = new Graph(totalVertices);
            // Creates a FileWriter
            FileWriter file = new FileWriter("result.txt", true);
            // Creates a PrintWriter
            PrintWriter out = new PrintWriter(file, true);
            out.println();  // to put line break between the two inputs, also to leave space in 1st line for andrewID
            fillGraph(allLines, graph, out);
            out.println();  // to put line break after displaying adjacency matrix
            colorGraph(graph, out);
            System.out.println("Successfully wrote to the file.");
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    // code taken from Project 3 guideline pdf
    public static void processLine(String line) {
        StringTokenizer st;              // use space, and tab for delimiters
        st = new StringTokenizer(line, " \t");
        st.nextToken(); // skipping student's name
        int noOfCourses = Integer.parseInt(st.nextToken());
        while (noOfCourses > 0) {
            String courseName = st.nextToken();
            int value = rbtree.insert(courseName);
            if (value > totalVertices) {
                totalVertices = value;  // updating totalVertices with the maximum value
            }
            noOfCourses--;  // move to the next course taken by the student
        }
    }

    // fills the graph with the courses
    public static void fillGraph(String allLines, Graph graph, PrintWriter out) {
        String[] allStudents = allLines.split("\n");
        // processing for each student
        for (String s : allStudents) {
            StringTokenizer st;              // use space, and tab for delimiters
            st = new StringTokenizer(s, " \t");
            st.nextToken(); // skipping student's name
            int noOfCourses = Integer.parseInt(st.nextToken());
            String[] courses = new String[noOfCourses];
            for (int i=0; i<noOfCourses; i++){
                courses[i] = st.nextToken();
            }
            for (int i = 0; i < noOfCourses; i++) {
                for(int j = i+1; j < noOfCourses; j++){
                    int id1 = rbtree.getValue(courses[i]);
                    int id2 = rbtree.getValue(courses[j]);
                    if(id1>=0 && id2>=0){   // id1 and id2 are both -1 if the read black tree does not have these courses
                        // adding edge because of conflict for this student i.e. these two courses cannot happen at same time
                        graph.addEdge(id1,id2);
                    }
                }
            }
        }
        // printing the adjacency matrix
        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < totalVertices; j++) {
                // if there is a conflict show 1, else 0
                if(graph.isEdge(i,j)) {
                    System.out.print("1");
                    out.print('1');
                }
                else {
                    System.out.print("0");
                    out.print('0');
                }
            }
            // line break between courses
            out.println();
            System.out.println();
        }
    }

    // color the graph
    public static void colorGraph(Graph graph, PrintWriter out){
        int listCount = 0;  // for printing the exam period number
        for (boolean b:marked) {
            if (!b) {
                out.print("Final Exam Period " + (listCount+1) +" => ");
                System.out.print("Final Exam Period " + (listCount+1) +" => ");
                List newclr = greedy(graph, new List());
                listCount++;
                LinkedListNode current = newclr.next(); // to move to the first node from the dummy node
                // printing the exam schedule for each period
                while (current != null){
                    out.print((rbtree.getName(current.getData()) + " "));
                    System.out.print(rbtree.getName(current.getData()) + " ");
                    current = current.getLink();
                }
                // line break between exam periods
                out.println();
                System.out.println();
            }
        }
    }

    // algorithm taken from Project 3 guideline pdf
    public static List greedy(Graph graph, List newclr){
        // greedy assigns to newclr those vertices that may be given the same color
        boolean found;
        int v=0;
        newclr = new List();
        for (int i=0; i<marked.length; i++){
            if(!marked[i]){
                v=i;
                break;
            }
        }
        while (v<totalVertices){
            found=false;
            LinkedListNode current = newclr.next();
            while (current != null){
                if(graph.isEdge(v,current.getData())){
                    found=true; // cannot be added to same exam period
                }
                current = current.getLink();
            }
            if (!found){    // no conflicts found
                marked[v] = true;   // coloring graph vertex
                newclr.add(v);
            }
            if(v==totalVertices-1)
                v++;
            for (int i=v+1; i<marked.length; i++){
                v=i;
                if(!marked[i]){     // if uncolored vertex is found, then check for this new vertex
                    break;
                }
            }
        }
        return newclr;
    }
}
