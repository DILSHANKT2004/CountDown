import java.util.Scanner;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
public class CountDown {
    public static void main(String[] args) throws Exception {
        Scanner SC = new Scanner(System.in);
        System.out.print("Enter the number of seconds:");
        int seconds = SC.nextInt();

        for(int i=seconds;i>0;i--){
            System.out.println(i);
            Thread.sleep(1000);

        }
        generateBeep(600,500);
        System.out.println("Times up!");

        SC.close();
    }

    public static void generateBeep(int hz, int msecs) throws Exception {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(8000f, 8, 1, true, false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();

        for (int i = 0; i < msecs * 8; i++) {
            double angle = i / (8000f / hz) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0);
            sdl.write(buf, 0, 1);
        }

        sdl.drain();
        sdl.stop();
        sdl.close();
    }
}
