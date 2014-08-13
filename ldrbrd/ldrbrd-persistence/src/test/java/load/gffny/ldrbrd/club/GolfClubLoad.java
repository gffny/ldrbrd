/**
 * 
 */
package load.gffny.ldrbrd.club;

import java.net.UnknownHostException;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.mongodb.MongoClient;

/**
 * @author John D. Gaffney | gffny.com
 */
public class GolfClubLoad {

	/** */
	private MongoClient mongoClient;

	/** */
	private Morphia morphia;

	/** */
	private Datastore datastore;

	/**
	 * @throws UnknownHostException
	 */
	public GolfClubLoad() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
		morphia = new Morphia();
		datastore = morphia.createDatastore(mongoClient, Constant.MONGO_DB_NAME);
		morphia.mapPackage(Constant.MONGO_MAP_PACKAGE);
	}

	@Test
	public void loadGolfClub() {
		Club loadClub = new Club("test club");
		loadClub.setAddress("test address");
		loadClub.setManagerName("test manager");
		loadClub.setDressCodePolicy("test dcp");
		loadClub.setGreenKeeperName("test green keeper");
		loadClub.setProGolferName("test pro");

		Key<Club> key = datastore.save(loadClub);
		System.out.println(key.getId().toString());
	}

	@Test
	public void getGolfClub() {
		Query<Club> query = datastore.createQuery(Club.class);
		query.field("address").equal("test address");
		Club retrievedClub = query.get();
		System.out.println(retrievedClub.getClubName());
	}
}
