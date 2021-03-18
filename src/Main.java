
import java.io.*;
import parcs.*;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        task curtask = new task();
        curtask.addJarFile("Algo.jar");

        AMInfo info = new AMInfo(curtask, null);

        System.out.println("Start executing");
        long startTime = System.nanoTime();

        List<channel> channels = new ArrayList<>();

        point p1 = info.createPoint();
        System.out.println(p1);
        channel c1 = p1.createChannel();
        p1.execute("Algo");
        c1.write("GEEKS FOR GEEKS");
        c1.write("GEEK");
        c1.write(101);
        channels.add(c1);

        point p2 = info.createPoint();
        channel c2 = p2.createChannel();
        p2.execute("Algo");
        c2.write("GEEKS FOR GEKS");
        c2.write("GEEK");
        c2.write(101);
        channels.add(c2);

        int i = 1;

        for (parcs.channel channel : channels) {
            System.out.println("\n\n\n\n Processing string" + String.valueOf(i));
            i++;
            System.out.println("Waiting for result...");

            String count = (String) channel.readObject();
            System.out.println("Result found:");
            System.out.println(count);

        }

        double estimatedTime = (double) (System.nanoTime() - startTime) / 1000000000;
        System.out.println("Time total (excluding IO): " + estimatedTime);

        curtask.end();
    }
}
