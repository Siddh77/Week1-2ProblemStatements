import java.util.*;

class UsernameChecker {

    private Map<String, Integer> users = new HashMap<>();
    private Map<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String alt = username + i;
            if (!users.containsKey(alt))
                suggestions.add(alt);
        }

        String dotVersion = username.replace("_", ".");
        if (!users.containsKey(dotVersion))
            suggestions.add(dotVersion);

        return suggestions;
    }

    public String getMostAttempted() {
        return Collections.max(attempts.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }
}
