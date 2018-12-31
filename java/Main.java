import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
// javac Main.java & java Main
public class Main {
        public static void main(String[] args) throws Exception {
                Integer attackSeconds=Integer.parseInt(args[0]);
                String targetUrl=args[1];
                HttpParralelExecutor httpParralelExecutor = new HttpParralelExecutor(attackSeconds,targetUrl);
                ResultPrinter resultPrinter=new ResultPrinter(attackSeconds,targetUrl,httpParralelExecutor.DoneRequestQueue);
                httpParralelExecutor.startInBackground();
                resultPrinter.printTillEnd();
        }
}
