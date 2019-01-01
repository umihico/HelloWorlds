import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Callable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
// javac HttpRequest.java & java HttpRequest



public class HttpRequest implements Callable<String> {
        private String targetUrl;
        public HttpRequest(String targetUrl) {
                this.targetUrl=targetUrl;
        }
        @Override
        public String call() throws Exception {
                StringBuilder result = new StringBuilder();
                URL url = new URL(this.targetUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                System.out.println("start request");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }
                rd.close();
                int code = conn.getResponseCode();
                // code.toString();
                System.out.println(code);
                return Integer.toString(code);
                // return result.toString();
        }
}