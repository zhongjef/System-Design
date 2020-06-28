import java.rmi.ServerError;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instantiate a demo with no server. Each server added after has 200 virtual nodes.
        List<Server> servers = new LinkedList<>();
        int numberOfVirtualNodes = 200;
        ConsistentHash consistentHashDemo = new ConsistentHash(numberOfVirtualNodes, servers);

        // Add a new server
        Server newServer1 = new Server("8.8.8.8");
        Server newServer2 = new Server("8.8.4.4");
        consistentHashDemo.add(newServer1);
        consistentHashDemo.add(newServer2);
        consistentHashDemo.hash("key0");

        // Find out which server "key0" is mapped to
        Server initialServer = consistentHashDemo.get("key0");
        System.out.println(initialServer);

        // Remove the server, see if key0 is re-mapped
        consistentHashDemo.remove(initialServer);
        // Find out which server "key0" is mapped to now
        System.out.println(consistentHashDemo.get("key0"));
    }
}
