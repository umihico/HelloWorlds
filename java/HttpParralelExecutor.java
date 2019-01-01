import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Callable;
// javac HttpParralelExecutor.java & java HttpParralelExecutor
public class HttpParralelExecutor {
        private CompletionService<String> jobQueue;
        private HttpParralelExecutorBG httpParralelExecutorBG;
        public HttpParralelExecutor(int attackSeconds,String targetUrl) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                int vcpu = Runtime.getRuntime().availableProcessors();
                // System.out.println("vCPU: " + vcpu);
                BlockingQueue completionInsideQueue=new LinkedBlockingQueue();
                this.jobQueue=new ExecutorCompletionService<String>(executorService,completionInsideQueue);
                this.httpParralelExecutorBG=new HttpParralelExecutorBG(attackSeconds,targetUrl,this.jobQueue,completionInsideQueue,executorService);
        }
        public CompletionService<String> getDoneRequestQueue(){
                return this.jobQueue;
        }
        public void startInBackground(){
                // System.out.println("startInBackground!!");
                ExecutorService execBackground = Executors.newSingleThreadExecutor();
                execBackground.submit(this.httpParralelExecutorBG);
                execBackground.shutdown();
        }
}
