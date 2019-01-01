import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Callable;
// javac HttpParralelExecutorBG.java & java HttpParralelExecutorBG



public class HttpParralelExecutorBG implements Callable<Long> {
        private Integer attackSeconds;
        private String targetUrl;
        private CompletionService<String> jobQueue;
        private BlockingQueue completionInsideQueue;
        private ExecutorService executorService;
        public HttpParralelExecutorBG(int attackSeconds,String targetUrl,CompletionService<String> jobQueue,BlockingQueue completionInsideQueue,ExecutorService executorService) {
                this.attackSeconds=attackSeconds;
                this.targetUrl=targetUrl;
                this.jobQueue=jobQueue;
                this.completionInsideQueue=completionInsideQueue;
                this.executorService=executorService;
        }
        @Override
        public Long call() throws Exception {
                // Thread.sleep(5000);

                for(int i = 0; i < 3; i++) {
                        this.jobQueue.submit(new HttpRequest(this.targetUrl));
                }
                this.executorService.shutdown();
                System.out.println("call4");
                return 1l;
        }
}
