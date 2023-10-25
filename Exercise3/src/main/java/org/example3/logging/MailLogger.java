package main.java.org.example3.logging;

public class MailLogger implements Logger{
    @Override
    public void log(String data) {
        System.out.println("lOGGED ON Mail"+data);
    }
}
