import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteAppcenterBuilds {

	public static void main(String[] args) throws IOException {
		for(int i=242;i<=275;i++) {
			URL url = new URL("https://api.appcenter.ms/v0.1/apps/mendix-technology/com.nalm_ci_"+i+"ui.mobile-ios");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("DELETE");
			http.setRequestProperty("Accept", "*/*");
			http.setRequestProperty("X-API-Token", "ff8241914e9cd002fa5fc5e6357156c42a12fc0e");

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			http.disconnect();
		}
		
	}

}
