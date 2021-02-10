package blockingQueue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 程序在一个目录及其所有子目录下搜索所有文件，打印包涵指定关键字的行
 *
 * @author chenjianglin
 * @date 2021/2/10
 * @since 1.0.0
 **/
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final Path DUMMY = Paths.get("");
    private static final BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String directory = scanner.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = scanner.nextLine();

            Runnable enumerator = ()->{
                try {
                    enumerate(Paths.get(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();

            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () ->{
                    boolean done = false;
                    while (!done) {
                        try {
                            Path file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            }else search(file,keyword);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    public static void enumerate(Path directory) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(directory)) {
            for (Path child :
                    children.collect(Collectors.toList())) {
                if (Files.isDirectory(child)) {
                    enumerate(child);
                }else {
                    queue.put(child);
                }
            }
        }
    }

    public static void search(Path file, String keyword) throws IOException {
        try(Scanner in = new Scanner(file, String.valueOf(StandardCharsets.UTF_8))){
            int lineNumber =0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n",file,lineNumber,line);
                }
            }
        }
    }
}
