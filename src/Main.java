//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        BlockingQueue<String> bq = new BlockingQueue<>(5);
        new StringProducer(bq);
        new Consumer<>(bq);
        new StringProducer(bq);
        new Consumer<>(bq);
        new Consumer<>(bq);
        new Consumer<>(bq);
    }
}