import java.util.*;

class DNSCache {

    class DNSEntry {
        String ip;
        long expiry;

        DNSEntry(String ip, long ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl;
        }
    }

    private Map<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (System.currentTimeMillis() < entry.expiry) {
                hits++;
                return entry.ip;
            }

            cache.remove(domain);
        }

        misses++;

        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(ip, 300000));

        return ip;
    }

    private String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public void getStats() {
        int total = hits + misses;
        System.out.println("Hit rate: " + (hits * 100.0 / total) + "%");
    }
}
