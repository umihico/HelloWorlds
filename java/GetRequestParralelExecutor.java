import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
// javac HttpRequestParralelExecutor.java & java HttpRequestParralelExecutor
public class HttpRequestParralelExecutor {
        private ExecutorService executorService;
        public CompletionService<List<String> > DoneRequestQueue;
        public HttpRequestParralelExecutor() {
                this.executorService = Executors.newCachedThreadPool();
                this.DoneRequestQueue=new ExecutorCompletionService<List<String> >(executorService);
        }
}
