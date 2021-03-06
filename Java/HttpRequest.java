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
import java.util.HashMap;
// javac HttpRequest.java & java HttpRequest



public class HttpRequest implements Callable<HashMap<String,String> > {
        private String targetUrl;
        public HttpRequest(String targetUrl) {
                this.targetUrl=targetUrl;
        }
        @Override
        public HashMap<String,String> call() throws Exception {
                HashMap<String,String> resultMap = new HashMap<String,String>();
                // System.out.println("received.");
                try{
                        httpRequestFunc(resultMap);
                } catch( Exception e) {
                        resultMap.put("statusCode","error");
                        resultMap.put("detail",e.toString());
                }
                return resultMap;
        }
        public void httpRequestFunc(HashMap<String,String> resultMap) throws Exception {
                StringBuilder result = new StringBuilder();
                URL url = new URL(this.targetUrl);
                RandomUrlGenerator rurl= new RandomUrlGenerator();// replace random poplar url not to attack one
                url=new URL(rurl.genUrlRandomly()); // replace random poplar url not to attack one
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // conn.setConnectTimeout(100000);
                conn.setRequestMethod("GET");
                // System.out.println("start request");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }
                rd.close();
                int statusCode = conn.getResponseCode();
                resultMap.put("statusCode",Integer.toString(statusCode));
                // System.out.println(statusCode);
                resultMap.put("detail",result.toString());
                // return Integer.toString(statusCode);
                // return result.toString();
        }
}
