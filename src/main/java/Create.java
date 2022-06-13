import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

import java.net.InetSocketAddress;


public class Create {

    public static void main(String[] args) {

        try(CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .addContactPoint(new InetSocketAddress("127.0.0.2", 9042))
                .addContactPoint(new InetSocketAddress("127.0.0.3", 9042))
                .withLocalDatacenter("datacenter1")
                .build()) {

            session.execute("insert into testKeyspace.users_by_country(countrycode, name, email, age) values('RO', 'Ionel', 'ionel@test.com', 30);");

            SimpleStatement ss = SimpleStatement.newInstance("select * from testKeyspace.users_by_country where countrycode = 'RO'");
            ResultSet rs = session.execute(ss);
            rs.forEach(row -> {
                var name = row.getString("name");
                var email = row.getString("email");
                int age = row.getInt("age");
                System.out.println(name + " - " + " - " + email + " - " + age);
            });
        }
    }
}


