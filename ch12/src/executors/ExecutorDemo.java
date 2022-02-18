package executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 执行器
 *
 * @author chenjianglin
 * @date 2021/2/11
 * @since 1.0.0
 **/
public class ExecutorDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String start = scanner.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String word = scanner.nextLine();

            Set<Path> files = descendants(Paths.get(start));
            ArrayList<Callable<Long>> tasks = new ArrayList<>();
            for (Path file : files) {
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }
            ExecutorService executor = Executors.newCachedThreadPool();

            Instant startTime = Instant.now();
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result :
                    results) {
                total += result.get();
            }
            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + word + ": " + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");
            ArrayList<Callable<Path>> searchTasks = new ArrayList<>();
            for (Path file : files) {
                searchTasks.add(searchForTask(word, file));
            }
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + " occurs in: " + found);

            if (executor instanceof ThreadPoolExecutor) {
                System.out.println("Largest pool size: " + ((ThreadPoolExecutor) executor).getLargestPoolSize());
            }
            executor.shutdown();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile)
                    .collect(Collectors.toSet());
        }
    }

    private static Callable<Path> searchForTask(String word, Path path) {
        return ()->{
            try (Scanner scanner = new Scanner(path)){
                while (scanner.hasNext()) {
                    if (scanner.next().equals(word)) {
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    private static Long occurrences(String word, Path path) {
        try (Scanner scanner = new Scanner(path);) {
            int count = 0;
            while (scanner.hasNext()) {
                if (scanner.next().equals(word)) {
                    count++;
                }
            }
            return (long) count;
        } catch (IOException e) {
            return 0L;
        }
    }
}
