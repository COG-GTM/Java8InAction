package lambdasinaction.chap11;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class CompletableFutureTest {

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        assertEquals("hello", future.get());
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> "hello")
                .thenApply(String::length);
        assertEquals(Integer.valueOf(5), future.get());
    }

    @Test
    public void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello")
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> " world"),
                        String::concat);
        assertEquals("hello world", future.get());
    }

    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world"));
        assertEquals("hello world", future.get());
    }

    @Test
    public void exceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .<String>supplyAsync(() -> { throw new RuntimeException("error"); })
                .exceptionally(ex -> "recovered");
        assertEquals("recovered", future.get());
    }

    @Test
    public void shopGetPriceFormat() {
        Shop shop = new Shop("BestShop");
        String price = shop.getPrice("iPhone");
        assertNotNull(price);
        String[] parts = price.split(":");
        assertEquals(3, parts.length);
        assertEquals("BestShop", parts[0]);
        Double.parseDouble(parts[1]);
        Discount.Code.valueOf(parts[2]);
    }

    @Test
    public void quoteParse() {
        Quote quote = Quote.parse("MyShop:100.0:GOLD");
        assertEquals("MyShop", quote.getShopName());
        assertEquals(100.0, quote.getPrice(), 0.01);
        assertEquals(Discount.Code.GOLD, quote.getDiscountCode());
    }

    @Test
    public void completableFutureAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "a");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "b");
        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);
        all.get();
        assertEquals("a", f1.get());
        assertEquals("b", f2.get());
    }

    @Test
    public void completableFutureAnyOf() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "first");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "second");
        Object result = CompletableFuture.anyOf(f1, f2).get(5, TimeUnit.SECONDS);
        assertNotNull(result);
    }
}
