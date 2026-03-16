import java.util.*;

class FlashSaleInventory {

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int quantity = stock.getOrDefault(productId, 0);

        if (quantity > 0) {
            stock.put(productId, quantity - 1);
            return "Purchase successful. Remaining: " + (quantity - 1);
        }

        waitingList.get(productId).add(userId);
        return "Added to waiting list. Position: "
                + waitingList.get(productId).size();
    }
}
