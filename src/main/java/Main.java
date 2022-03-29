public class Main {
    public static void main(String[] args) {
//        Binary binary = new Binary();
//        binary.readProblemFromFile("src/main/resources/data/binary_10x10");
//        binary.backtrack();

        Futoshiki futoshiki = new Futoshiki();
        futoshiki.readProblemFromFile("src/main/resources/data/futoshiki_4x4");
        futoshiki.backtrack();
    }
}
