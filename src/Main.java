import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        String[] result = new String[2];
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            result = data.split("--");
        }


        task curtask = new task();
        curtask.addJarFile("MarchingParallel.jar");

        AMInfo info = new AMInfo(curtask, null);

        System.out.println("Start executing");
        long startTime = System.nanoTime();

        List<channel> channels = new ArrayList<>();

        point p1 = info.createPoint();
        channel c1 = p1.createChannel();
        p1.execute("MarchingParallel");
        c1.write(result[0]);
        c1.write("abc");
        c1.write(101);
        channels.add(c1);

        point p2 = info.createPoint();
        channel c2 = p2.createChannel();
        p2.execute("MarchingParallel");
        c2.write(result[0]);
        c2.write("abc");
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
