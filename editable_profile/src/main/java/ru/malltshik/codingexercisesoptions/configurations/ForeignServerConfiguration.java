package ru.malltshik.codingexercisesoptions.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * This configuration starts and stops our foreign server which provide attributes and locations.
 * Cause we can create or update profiles without this data (some of the attributes is mandatory)
 * I think whole application should be dependent on this server. For do that I write this class, which start
 * simple python server in {@link PostConstruct} method and kill it in {@link PreDestroy} method.
 * As we know {@link PreDestroy} methods work as a shutdown hook, therefore I hope there will no problem while
 * JVM process will be killed.
 * However if something has gone wrong, we have to find python process and kill it on our own.
 * {@code pkill -f server/server.py}
 */
@Configuration
@Order(0)
public class ForeignServerConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(ForeignServerConfiguration.class);
    private static Process FOREIGN_SERVER_PROCESS;

    /**
     * Running foreign server process
     * @throws IOException might rise if server.py file will not exist, or permission denied or else IO exception
     */
    @PostConstruct
    public void init() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "server/server.py");
        LOGGER.debug("Ready to start foreign server");
        FOREIGN_SERVER_PROCESS = processBuilder.start();
        LOGGER.info("Foreign server started!");
    }

    /**
     * Stopping  foreign server process
     */
    @PreDestroy
    public void destroy() {
        if (FOREIGN_SERVER_PROCESS != null && FOREIGN_SERVER_PROCESS.isAlive()) {
            LOGGER.debug("Ready to shutdown foreign server");
            FOREIGN_SERVER_PROCESS.destroy();
            LOGGER.info("Foreign server turn off!");
        }
    }
}
