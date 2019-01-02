import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
// javac HttpParralelExecutor.java & java HttpParralelExecutor
public class HttpParralelExecutor {
        private CompletionService<String> doneRequestQueue;
        private BlockingQueue jobQueue;
        private Integer attackSeconds;
        private String targetUrl;
        private ExecutorService backgroundExecutorService;
        private long jobQueueMaxSize;

        public HttpParralelExecutor(int attackSeconds,String targetUrl) {
                this.attackSeconds=attackSeconds;
                this.targetUrl=targetUrl;

                int vcpu = Runtime.getRuntime().availableProcessors();
                // System.out.println("vCPU: " + vcpu);
                this.jobQueueMaxSize=vcpu*2;
                int threadNum= vcpu*100;
                this.jobQueue=new LinkedBlockingQueue();
                this.backgroundExecutorService =new ThreadPoolExecutor(threadNum, threadNum,
                                                                       60L, TimeUnit.SECONDS,
                                                                       this.jobQueue,new ThreadFactory() {
                        public Thread newThread(Runnable r) {
                                Thread t = Executors.defaultThreadFactory().newThread(r);
                                t.setDaemon(true);
                                return t;
                        }
                });
                this.doneRequestQueue=new ExecutorCompletionService<String>(backgroundExecutorService);
        }
        public CompletionService<String> getDoneRequestQueue(){
                return this.doneRequestQueue;
        }
        public class backgroundProducer implements Callable<Long> {
                @Override
                public Long call() throws Exception {
                        long endTime = System.currentTimeMillis()+(attackSeconds*1000);
                        long i = 0l;

                        while (true) {
                                if (System.currentTimeMillis()<endTime) {
                                        // System.out.println(System.currentTimeMillis()-endTime);
                                        if (jobQueue.size()<jobQueueMaxSize) {
                                                i++;
                                                // System.out.println("true"+i+" queue size:"+jobQueue.size());
                                                doneRequestQueue.submit(new HttpRequest(targetUrl));
                                                // System.out.println("submitted"+i);
                                        } else {
                                                Thread.sleep(10);// doneRequestQueue.submit cause deadlock if inside queue has limitation when  without this.
                                        }
                                } else {
                                        // System.out.println("break"+i);
                                        break;
                                }
                        }
                        // System.out.println("breaked");
                        backgroundExecutorService.shutdown();
                        return 1l;
                }
        }

        public void startInBackground(){
                // System.out.println("startInBackground!!");
                ExecutorService execBackground = Executors.newSingleThreadExecutor();
                execBackground.submit(new backgroundProducer());
                execBackground.shutdown();
        }
}
