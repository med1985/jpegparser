package parser

class Main {
    public static void main(String[] args) {
        JPEGParser parser = new JPEGParser(new FileInputStream(args[0]))
        parser.parse()
    }
}
