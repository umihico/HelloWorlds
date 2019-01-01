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
                long endTime = System.currentTimeMillis()+(this.attackSeconds*1000);

                while (true) {
                        if (System.currentTimeMillis()<endTime) {
                                // System.out.println("put");
                                this.jobQueue.submit(new HttpRequest(endTime,this.targetUrl));
                        } else {
                                System.out.println("break");
                                break;
                        }
                }
                // this.executorService.shutdown();
                this.executorService.shutdownNow();
                System.out.println("shutdownNow");
                return 1l;
        }
}
