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
        private CompletionService<List<String> > jobQueue;
        private BlockingQueue completionInsideQueue;
        public static void main(String... args){

        }

        public HttpParralelExecutorBG(int attackSeconds,String targetUrl,CompletionService<List<String> > jobQueue,BlockingQueue completionInsideQueue) {
                this.attackSeconds=attackSeconds;
                this.targetUrl=targetUrl;
                this.jobQueue=jobQueue;
                this.completionInsideQueue=completionInsideQueue;
        }
        @Override
        public Long call() throws Exception {
                Thread.sleep(5000);
                System.out.println("call3");
                return 1l;
        }
}
