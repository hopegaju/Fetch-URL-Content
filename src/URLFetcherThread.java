import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLFetcherThread implements Runnable {
    private String urlString;

    public URLFetcherThread(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "]: Line from URL: " + line);
            }
        } catch (MalformedURLException e) {
            System.out.println("Thread [" + Thread.currentThread().getName() + "]: Invalid URL - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Thread [" + Thread.currentThread().getName() + "]: IO error - " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
