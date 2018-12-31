import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
// credit:https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java

// how to run in cmd.exe
// javac Get2.java & java Get2 https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java

public class Get2 {
        public static String getHTML(String TargetURL) throws Exception {
                StringBuilder result = new StringBuilder();
                URL url = new URL(TargetURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }
                rd.close();
                int code = conn.getResponseCode();
                System.out.println(code);
                return result.toString();

        }

        public static void main(String[] args) throws Exception
        {
                System.out.println(getHTML(args[0]));
        }
}
