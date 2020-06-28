public class Server {
    private String ipAddress;

    public Server(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return this.ipAddress;
    }
}