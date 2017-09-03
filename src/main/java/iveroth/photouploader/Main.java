package iveroth.photouploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;

public class Main {
	private final static String applicationName = "PhotosUpload";
	private final static JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static void main(String[] args) throws Exception {
		PicasawebService service = new PicasawebService(applicationName);
		service.setOAuth2Credentials(authorize());

		PicasawebClient client = new PicasawebClient(service);

		System.out.println("Listing albums");
		List<AlbumEntry> albums = client.getAlbums();
		for (AlbumEntry e : albums) {
			System.out.println("Album: " + e.getTitle().getPlainText());
			for (PhotoEntry p : client.getPhotos(e)) {
				System.out.println(" - " + p.getTitle().getPlainText());
			}
		}

//		System.out.println("Adding album");
//		AlbumEntry newAlbum = client.addAlbum("java-foo-test");
//		System.out.println("Added: " + newAlbum.getTitle().getPlainText());
		
//		System.out.println("Adding image");
//		MediaFileSource myMedia = new MediaFileSource(new File("/tmp/76FF7736-2A8D-4827-A065-A460081DA8E7.jpg"), "image/jpeg");
//		String url = "https://picasaweb.google.com/data/feed/api/user/username/albumid/" + newAlbum.getTitle().getPlainText();
//		PhotoEntry returnedPhoto = service.insert(new URL(url), PhotoEntry.class, myMedia); 
//		System.out.println("Added: " + returnedPhoto.getTitle().getPlainText());
		System.out.println("Done");
	}

	private static Credential authorize() throws Exception {
		DataStoreFactory dataStoreFactory = new FileDataStoreFactory(new File("src/main/resources/"));
		// load client secrets
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
				new InputStreamReader(new FileInputStream("src/main/resources/client_secret.json")));
		// set up authorization code flow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JSON_FACTORY,
				clientSecrets, Arrays.asList("https://www.googleapis.com/auth/photos"))
						.setDataStoreFactory(dataStoreFactory).build();
		// authorize
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("default");
	}
}
