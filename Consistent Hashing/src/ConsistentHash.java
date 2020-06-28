import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.zip.CRC32;

/**
 * Implements consistent hashing algorithm for distributed caching.
 */
public class ConsistentHash {
    private final SortedMap<Long, Server> hashRing;
    private final int numberOfVirtualNodes;

    /**
     * Constructor
     * @param numberOfVirtualNodes number of virtual nodes for each server
     * @param servers physical servers
     */
    public ConsistentHash(int numberOfVirtualNodes, Collection<Server> servers) {
        this.numberOfVirtualNodes = numberOfVirtualNodes;
        this.hashRing = new TreeMap<>();
        if (servers != null) {
            for (Server s : servers) {
                this.add(s);
            }
        }
    }

    /**
     * Add a new server to the hashRing, alongside with corresponding virtual nodes.
     * @param server physical server
     */
    public void add(Server server) {
        for (int i = 0; i < this.numberOfVirtualNodes; i++) {
            Long virtualNode = hash(server.toString() + i);
            this.hashRing.put(virtualNode, server);
        }
    }

    /**
     * Remove a server from the hashRing, alongside with corresponding virtual nodes.
     * @param server physical server
     */
    public void remove(Server server) {
        for (int i = 0; i < this.numberOfVirtualNodes; i++) {
            Long virtualNode = hash(server.toString() + i);
            this.hashRing.remove(virtualNode);
        }
    }

    /**
     * Given a distributed key, return the server it is mapped to.
     * @param key distributed key
     */
    public Server get(String key) {
        if (this.hashRing.isEmpty()) {
            return null;
        }
        Long hashVal = hash(key);
        if (!this.hashRing.containsKey(hashVal)) {
            SortedMap<Long, Server> tailMap = hashRing.tailMap(hashVal);
            hashVal = tailMap.isEmpty() ? this.hashRing.firstKey() : tailMap.firstKey();
        }
        return hashRing.get(hashVal);
    }

    /**
     * Uniformly distributed hashing function
     * @param key key to hash
     * @return hash value. range 0 ~ 2^32 - 1
     */
    protected Long hash(String key) {
        CRC32 crc = new CRC32();
        crc.update(key.getBytes());
        return crc.getValue();
    }
}