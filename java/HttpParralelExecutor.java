import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
// javac HttpParralelExecutor.java & java HttpParralelExecutor
public class HttpParralelExecutor {
        private ExecutorService executorService;
        public CompletionService<List<String> > DoneRequestQueue;
        public HttpParralelExecutor(int attackSeconds,String targetUrl) {
                this.executorService = Executors.newCachedThreadPool();
                this.DoneRequestQueue=new ExecutorCompletionService<List<String> >(executorService);
        }
        public void startInBackground(){
                System.out.println("startInBackground!!");
        }
}
