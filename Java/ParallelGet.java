// how to run in cmd.exe
// javac ParallelGet.java & java ParallelGet
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class ParallelGet {
        public static void parallelGet(String TargetURL){
                int vcpu = Runtime.getRuntime().availableProcessors();
                System.out.println("vCPU: " + vcpu);
                ExecutorService service = Executors.newFixedThreadPool(vcpu);
                List<Future<String> > futures = new ArrayList<>();
                for(int i = 0; i < 3; i++) {
                        futures.add(service.submit(TargetURL->getHTML(TargetURL)));
                }
                service.shutdown();
                System.out.println("dummy: " + futures.stream().mapToDouble(future->{
                        try{ return future.get(); }catch(Exception e) { return 0.0d; }
                }).sum());
        }
        public static String getHTML(String TargetURL) throws Exception {
                URL url = new URL(TargetURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }
                rd.close();
                // return result.toString();
                return conn.getResponseCode();
        }
        public static void main(String[] args) throws Exception
        {
                // System.out.println(parallelGet(args[0]));
                getHTML(args[0]);
        }
}
