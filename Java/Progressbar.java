// javac Progressbar.java & java Progressbar
class Progressbar {
        public static void main (String args[]) throws InterruptedException {
                String str = "Hello, world!";
                System.out.print(str);
                Thread.sleep(1000);
                System.out.print("\r");
                for (int i=0; i<str.length(); i++) {
                        System.out.print(" ");
                }
                System.out.println("abc");

        }
}
