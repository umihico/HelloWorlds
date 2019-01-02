import java.util.concurrent.Executors;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.Queue;
// javac ResultPrinter.java & java ResultPrinter
public class ResultPrinter {
        private CompletionService<String> doneRequestQueue;
        private long endTime;
        public ResultPrinter(int attackSeconds,String targetUrl,CompletionService<String> doneRequestQueue){
                System.out.println("Target URL:");
                System.out.println(targetUrl);
                this.endTime = System.currentTimeMillis()+(attackSeconds*1000);
                System.out.println("Attacking for '"+attackSeconds+"' seconds...");
                this.doneRequestQueue=doneRequestQueue;

        }
        public void printRealtimeStatus() throws Exception {

                long i = 0l;
                while (true) {
                        i++;
                        // Future<String> future = this.doneRequestQueue.poll(10,TimeUnit.SECONDS);
                        Future<String> future = this.doneRequestQueue.take();
                        if (System.currentTimeMillis()>this.endTime) {
                                System.out.println("Printer break.");
                                break;
                        }
                        String newResult = future.get();
                        printRealtimeStatusMain(newResult,i);
                }
        }
        public void printRealtimeStatusMain(String newResult,long i){
                System.out.println("resultPrinter "+i+" :"+newResult);

        }
        public void printSummary(long requestedNum){
                System.out.println("resultPrinter printSummary");
                System.out.println(requestedNum);

        }
}
