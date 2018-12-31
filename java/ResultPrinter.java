import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
// javac ResultPrinter.java & java ResultPrinter
public class ResultPrinter {
        public ResultPrinter(int attackSeconds,String targetUrl,CompletionService<List<String> > DoneRequestQueue){
                System.out.println("Attacknig the url:");
                System.out.println(targetUrl);
                System.out.println("for "+attackSeconds+" seconds...");
        }
        public void printTillEnd(){
                System.out.println("Finished!!");

        }
}
