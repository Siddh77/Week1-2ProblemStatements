import java.util.*;

class AutocompleteSystem {

    private Map<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<String> pq =
                new PriorityQueue<>((a, b) ->
                        frequency.get(a) - frequency.get(b));

        for (String query : frequency.keySet()) {

            if (query.startsWith(prefix)) {

                pq.add(query);

                if (pq.size() > 10)
                    pq.poll();
            }
        }

        List<String> result = new ArrayList<>(pq);
        Collections.reverse(result);

        return result;
    }
}
