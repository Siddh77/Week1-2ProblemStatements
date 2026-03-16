import java.util.*;

class MultiLevelCache {

    private LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true);

    private Map<String, String> L2 = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id)) {
            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if (L2.containsKey(id)) {

            System.out.println("L2 HIT");

            String video = L2.get(id);
            L1.put(id, video);

            return video;
        }

        System.out.println("DB HIT");

        String video = fetchFromDB(id);

        L2.put(id, video);

        return video;
    }

    private String fetchFromDB(String id) {
        return "VideoData_" + id;
    }
}
