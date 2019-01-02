// how to run in cmd.exe
// javac ThreadTest.java & java ThreadTest
class Sample extends Thread {
        private String message;

        Sample(String msg){
                this.message = msg;
        }
        @Override
        public void run(){
                for (int i = 0; i<3; i++) {
                        System.out.println(message);
                }
        }
}
class Sample2 {
        void runSample(){
                System.out.println("test");
        }
}
public class ThreadTest {
        public static int fib(int n) {
                if (n == 0) {
                        return 0;
                } else if (n == 1) {
                        return 1;
                } else {
                        return fib(n - 1) + fib(n - 2);
                }
        }

        public static void main(String[] args) {
                System.out.println(fib(45));
        }

}
