package main.java.org.example3.logging;

public class DatabaseLogger implements Logger{
    @Override
    public void log(String data) {

        System.out.println("lOGGED ON Database"+data);
    }
}
