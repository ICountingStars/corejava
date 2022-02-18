package concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用一个并发的散列映射来统计一个目录树的Java文件中所有单词
 *
 * @author chenjianglin
 * @date 2021/2/10
 * @since 1.0.0
 **/
public class CHMDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path file) {
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                String word = scanner.next();
                map.merge(word,1L,Long::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)){
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathToRoot = Paths.get(".");
        for (Path p : descendants(pathToRoot)) {
            if (p.getFileName().toString().endsWith(".java")) {
                executor.execute(() -> process(p));
            }
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.MINUTES);
            map.forEach((k,v)->{
                if (v >= 10) {
                    System.out.println(k+" occurs "+v+ " times");
                }
            });
        }
    }
}
