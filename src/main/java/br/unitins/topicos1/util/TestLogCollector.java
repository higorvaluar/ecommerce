package br.unitins.topicos1.util;

import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

public class TestLogCollector {
    private static final Logger LOG = Logger.getLogger(TestLogCollector.class);
    private static final List<String> logBuffer = new ArrayList<>();

    public static void addLog(String message) {
        logBuffer.add(message);
    }

    public static void flushLogs() {
        LOG.info("=== Início dos Logs Manuais dos Testes ===");
        for (String log : logBuffer) {
            LOG.info(log);
        }
        LOG.info("=== Fim dos Logs Manuais dos Testes ===");
        logBuffer.clear();
    }
}