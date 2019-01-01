// javac doWhileTime.java & java doWhileTime 2 https://github.com/
public class doWhileTime {
        public static void main(String[] args) throws Exception {
                long startTime = System.currentTimeMillis();
                while (true) {
                        if (System.currentTimeMillis()<startTime+1*1000) {
                                System.out.println(startTime+1*1000-System.currentTimeMillis());
                        } else {
                                break;
                        }
                }
        }
}
