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
        private long endTime;
        public HttpRequest(long endTime,String targetUrl) {
                this.targetUrl=targetUrl;
                this.endTime=endTime;
        }
        @Override
        public String call() throws Exception {
                String result;
                try{
                        result=httpRequestFunc();
                } catch( Exception e) {

                        result=e.toString();
                }
                return result;
        }
        public String httpRequestFunc() throws Exception {
                StringBuilder result = new StringBuilder();
                URL url = new URL(this.targetUrl);
                UseRandomUrlInstead rurl= new UseRandomUrlInstead();// replace random poplar url not to attack one
                url=new URL(rurl.pickUrlRandomly()); // replace random poplar url not to attack one
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                System.out.println("start request");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }
                rd.close();
                int statusCode = conn.getResponseCode();
                // System.out.println(statusCode);
                return Integer.toString(statusCode);
                // return result.toString();
        }
}
