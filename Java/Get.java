// how to run in cmd.exe
// javac Get.java & java Get https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java
import java.io.*;
import java.net.*;

public class Get {

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
                return result.toString();
        }

        public static void main(String[] args) throws Exception
        {
                System.out.println(getHTML(args[0]));
        }
}
