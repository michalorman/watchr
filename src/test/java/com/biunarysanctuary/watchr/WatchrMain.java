package com.biunarysanctuary.watchr;

import com.binarysanctuary.watchr.OnChangeCallback;
import com.binarysanctuary.watchr.Watchr;
import com.binarysanctuary.watchr.WatchrThread;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.List;

public class WatchrMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 0) {
            WatchrThread thread = Watchr.watch(new OnChangeCallback() {
                @Override
                public void onChange(Path dir, List<WatchEvent<?>> events) {
                    for (WatchEvent<?> event : events) {
                        Path file = dir.resolve((Path) event.context());
                        System.out.println("Notified: " + event.kind() + " on file: " + file);
                    }
                }
            }, args);

            while (true) {
                System.in.read();
                System.out.println("Interrupting....");
                thread.interrupt();
                thread.join();
                break;
            }
        }
    }

}
