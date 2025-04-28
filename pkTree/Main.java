package pkTree;

public class Main {
    public static void main(String[] args) {

        String path = "/Users/prajanmalla/InteeliJ Projects/final-project/src/pkTree/ entities_hierarchy.txt";
        Tree<String> treeMain=new Tree<>();
        FileIO.readFile(path,treeMain);
        treeMain.displayTree();

    }
}