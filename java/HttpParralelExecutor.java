import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Callable;
// javac HttpParralelExecutor.java & java HttpParralelExecutor
public class HttpParralelExecutor {
        private CompletionService<List<String> > jobQueue;
        private HttpParralelExecutorBG httpParralelExecutorBG;
        public HttpParralelExecutor(int attackSeconds,String targetUrl) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                BlockingQueue completionInsideQueue=new LinkedBlockingDeque();
                this.jobQueue=new ExecutorCompletionService<List<String> >(executorService,completionInsideQueue);
                // System.out.println(this.completionInsideQueue.size());
                this.httpParralelExecutorBG=new HttpParralelExecutorBG(attackSeconds,targetUrl,this.jobQueue,completionInsideQueue);
        }
        public CompletionService<List<String> > getDoneRequestQueue(){
                return this.jobQueue;
        }
        public void startInBackground(){
                System.out.println("startInBackground!!");
                ExecutorService execBackground = Executors.newSingleThreadExecutor();
                execBackground.submit(this.httpParralelExecutorBG);
                execBackground.shutdown();
                // for(int i = 0; i < 3; i++) {
                //         this.jobQueue.submit(new HttpRequest(this.targetUrl));
                // }
                // this.executorService.shutdown();
        }
}
