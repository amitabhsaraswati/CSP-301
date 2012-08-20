package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TrieNode {

    public TrieNode[] t;
    public boolean end = false;

    public TrieNode() {
        t = new TrieNode[26];
    }

   // i am not a god..just kidding..i am the god //
    
    public void insert(String s) {
        String w = s.toLowerCase();
        int p = w.charAt(0) - 97;
        if (t[p] == null) {
            t[p] = new TrieNode();
        }
        if (w.length() > 1) {
            t[p].insert(s.substring(1));
        } else {
            t[p].end = true;
        }
    }
    
    public int square(int n){
        int p = n*n;
        return p;
    }

    public void MakeTrie() throws IOException {
        FileReader read = new FileReader("C:\\Users\\sony\\Documents\\NetBeansProjects\\Assignment2\\src\\dict.txt");
        BufferedReader reader = new BufferedReader(read);
        while (true) {
            String word = reader.readLine();
            if (word == null) {
                break;
            }
            insert(word);
        }
    }

    public boolean has(String s) {
        String w = s.toLowerCase();
        int p = w.charAt(0) - 97;
        if (t[p] == null && w.length() >= 1) {
            return false;
        }
        if (t[p] != null && w.length() > 1) {
            return t[p].has(w.substring(1));
        } else if (t[p].end == true && w.length() == 1) {
            return true;
        }
        return false;
    }

    public boolean checkarray(String a, String[] x) {
        boolean p = false;
        for (int y = 0; y < 20; y++) {
            if (a.equals(x[y])) {
                p = true;
                break;
            }
        }
        return p;
    }

    public String[] delerror(String s) {
        int y = 0;
        String w = s.toLowerCase();
        String[] x = new String[20];
        for (int j = 0; j <= w.length(); j++) {
            for (int i = 0; i < 26; i++) {
                char p = (char) (i + 97);
                String a = w.substring(0, j) + p + w.substring(j);
                if (has(a) && checkarray(a, x) == false) {
                    x[y] = a;
                    y++;
                } else {
                    continue;
                }
            }
        }
        for (int z = 0; z < 20; z++) {
            if (x[z] != null) {
                System.out.println(x[z]);
            } else {
                continue;
            }
        }
        return x;
    }

    public String[] replaceerror(String s) {
        int y = 0;
        String w = s.toLowerCase();
        String[] x = new String[20];
        for (int j = 0; j < w.length(); j++) {
            for (int i = 0; i < 26; i++) {
                char das = (char) (i + 97);
                String a = w.substring(0, j) + das + w.substring(j + 1);
                if (has(a) && checkarray(a, x) == false) {
                    x[y] = a;
                    y++;
                } else {
                    continue;
                }
            }
        }
        for (int z = 0; z < 20; z++) {
            if (x[z] != null) {
                System.out.println(x[z]);
            } else {
                continue;
            }
        }
        return x;
    }

    public String[] inserterror(String s) {
        int y = 0;
        String w = s.toLowerCase();
        String[] x = new String[20];
        if (s.length() == 1) {
            return x;
        } else {
            for (int j = 0; j < w.length(); j++) {
                String a = w.substring(0, j) + w.substring(j + 1);
                if (has(a) && checkarray(a, x) == false) {
                    x[y] = a;
                    y++;
                } else {
                    continue;
                }
            }
        }
        for (int z = 0; z < 20; z++) {
            if (x[z] != null) {
                System.out.println(x[z]);
            } else {
                continue;
            }
        }
        return x;
    }

    public String[] changeerror(String s) {
        int y = 0;
        String w = s.toLowerCase();
        String[] x = new String[20];
        for (int j = 0; j < w.length() - 1; j++) {
            String a = w.substring(0, j) + w.substring(j + 1, j + 2) + w.substring(j, j + 1) + w.substring(j + 2);
            if (has(a) && checkarray(a, x) == false) {
                x[y] = a;
                y++;
            } else {
                continue;
            }

        }
        for (int z = 0; z < 20; z++) {
            if (x[z] != null) {
                System.out.println(x[z]);
            } else {
                continue;
            }
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome!");
        TrieNode a = new TrieNode();
        long t1 = System.currentTimeMillis();
        a.MakeTrie();
        long t2 = System.currentTimeMillis();
        long t = t2 - t1;
        System.out.println("Time taken to build the trie was " + t + " ms.");
        while (true) {
            System.out.println("Please enter a word:");
            Scanner word = new Scanner(System.in);
            String s = word.nextLine();
            long t3 = System.nanoTime();
            if (a.has(s)) {
                long t4 = System.nanoTime();
                long st = t4 - t3;
                System.out.println("The word is correctly spelled");
                System.out.println("Time taken to search the given word is " + st + " ns.");
            } else {
                long t5 = System.nanoTime();
                long m1 = System.currentTimeMillis();
                long st1 = t5 - t3;
                System.out.println("Time taken to search the given word is " + st1 + " ns.");
                System.out.println("The spelling seems to be wrong! Did you mean any of the following?");
                System.out.println("Delete Errors:");
                a.delerror(s);
                System.out.println("Replace Errors");
                a.replaceerror(s);
                System.out.println("Insert Errors");
                a.inserterror(s);
                System.out.println("Change Errors");
                a.changeerror(s);
                long m2 = System.currentTimeMillis();
                long gt = m2 - m1;
                System.out.println("Time taken to generate suggestions was " + gt + " ms.");
                System.out.println("Are you sure its a correct word and want it to be added to the dictionary? (yes/no)");
                while (true) {
                    Scanner iyn1 = new Scanner(System.in);
                    String iyn = iyn1.nextLine();
                    if (iyn.equals("yes") || iyn.equals("Yes")) {
                        long i1 = System.nanoTime();
                        a.insert(s);
                        long i2 = System.nanoTime();
                        long i = i2 - i1;
                        System.out.println("Time taken to insert the word is " + i + " ns.");
                        break;
                    } else if ("no".equals(iyn) || "No".equals(iyn)) {
                        break;
                    } else {
                        System.out.println("Enter yes or no please!");
                    }
                }
            }
            System.out.println("Do you wish to enter another word? (yes/no)");
            while (true) {
                Scanner loop = new Scanner(System.in);
                String loop1 = loop.nextLine();
                if (loop1.equals("yes") || loop1.equals("Yes")) {
                    break;
                } else if (loop1.equals("no") || loop1.equals("No")) {
                    System.exit(0);
                } else {
                    System.out.println("Please enter yes or no!");
                }
            }
        }
    }
}
