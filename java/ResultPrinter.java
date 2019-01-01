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
        private CompletionService<String> DoneRequestQueue;
        private long endTime;
        public ResultPrinter(int attackSeconds,String targetUrl,CompletionService<String> DoneRequestQueue){
                System.out.println("Target URL:");
                System.out.println(targetUrl);
                this.endTime = System.currentTimeMillis()+(attackSeconds*1000);
                System.out.println("Attacking for '"+attackSeconds+"' seconds...");
                this.DoneRequestQueue=DoneRequestQueue;

        }
        public void printRealtimeStatus() throws Exception {

                while(true) {
                        Future<String> future = this.DoneRequestQueue.poll(10,TimeUnit.SECONDS);
                        if ((System.currentTimeMillis()>this.endTime) || (future == null) || future.isCancelled()) {
                                break;
                        }
                        String newResult = future.get();
                        printRealtimeStatusMain(newResult);
                }
        }
        public void printRealtimeStatusMain(String newResult){
                System.out.println("resultPrinter "+newResult);

        }
        public void printSummary(){
                System.out.println("resultPrinter printSummary");

        }
}
