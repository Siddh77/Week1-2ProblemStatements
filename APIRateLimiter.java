import java.util.*;

class RateLimiter {

    class TokenBucket {

        int tokens;
        int maxTokens;
        long lastRefill;

        TokenBucket(int maxTokens) {
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.lastRefill = System.currentTimeMillis();
        }
    }

    private Map<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(1000));

        TokenBucket bucket = clients.get(clientId);

        refill(bucket);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    private void refill(TokenBucket bucket) {

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill >= 3600000) {
            bucket.tokens = bucket.maxTokens;
            bucket.lastRefill = now;
        }
    }
}
