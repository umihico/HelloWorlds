import java.util.Random;
// javac RandomUrlGenerator.java & java RandomUrlGenerator
public class RandomUrlGenerator {
        static String rawHosts="google.co.jp,yahoo.co.jp,google.com,youtube.com,amazon.co.jp,twitter.com,docomo.ne.jp,rakuten.co.jp,fc2.com,dmm.co.jp,facebook.com,wikipedia.org,news.yahoo.co.jp,nicovideo.jp,pornhub.com,xvideos.com,livedoor.jp,pixiv.net,auone.jp,ameblo.jp,syosetu.com,instagram.com,eroterest.net,goo.ne.jp,livedoor.com,naver.jp,blog.jp,movie.eroterest.net,mbga.jp,kakaku.com,news.livedoor.com,shopping.yahoo.co.jp,ampproject.org,dmm.com,cityheaven.net,5ch.net,tabelog.com,dmkt-sp.jp,ameba.jp,line.me,cookpad.com,xbooks.to,gamewith.jp,xhamster.com,granbluefantasy.jp,zozo.jp,bakusai.com,jra.go.jp,cmoa.jp";
        public static void main(String[] args){
                System.out.println(genUrlRandomly());
        }
        public static String genUrlRandomly() {
                String urlString="https://"+genHostRandomly();
                System.out.println(urlString);
                return urlString;
        }
        public static String genHostRandomly() {
                String[] splitedUrls=rawHosts.split(",",0);
                Random rnd = new Random();
                int randomedInt = rnd.nextInt(splitedUrls.length);
                return splitedUrls[randomedInt];
        }
}
