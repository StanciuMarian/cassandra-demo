import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

import java.net.InetSocketAddress;


public class Read {

    public static void main(String[] args) {

        try(CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .addContactPoint(new InetSocketAddress("127.0.0.2", 9042))
                .addContactPoint(new InetSocketAddress("127.0.0.3", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {

            ResultSet rs = session.execute("select * from testKeyspace.users_by_country where countryCode='RO';");
            rs.forEach(row -> {
                var name = row.getString("name");
                var email = row.getString("email");
                int age = row.getInt("age");
                System.out.println(name + " - " + " - " + email + " - " + age);
            });



        }
    }
}


