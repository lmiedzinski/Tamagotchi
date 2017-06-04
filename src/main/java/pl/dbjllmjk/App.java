package pl.dbjllmjk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.Controller;

public class App {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Tamagotchi started! =>DBJLLMJK<=");
        Controller mainController = new Controller();
    }
}
