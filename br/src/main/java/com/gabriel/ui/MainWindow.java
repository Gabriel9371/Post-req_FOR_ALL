package com.gabriel.ui;

import com.gabriel.http.HttpEngine;
import com.gabriel.http.HttpRequestData;
import com.gabriel.http.HttpResponseData;

import javax.swing.*;
import java.awt.*;
import java.net.ConnectException;
import java.net.http.HttpTimeoutException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainWindow extends JFrame {

    private final HttpEngine engine = new HttpEngine();

    private final JComboBox<String> methodBox =
            new JComboBox<>(new String[]{"GET", "POST", "PUT", "PATCH", "DELETE"});
    private final JTextField urlField = new JTextField("https://");
    private final JButton sendButton = new JButton("Enviar");

    private final JTextArea requestHeadersArea = mono(true);
    private final JTextArea requestBodyArea = mono(true);

    private final JLabel statusLabel = new JLabel("Pronto");
    private final JTextArea responseBodyArea = mono(false);
    private final JTextArea responseHeadersArea = mono(false);

    public MainWindow() {
        super("ReqRes Portable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        requestHeadersArea.setText("Content-Type: application/json");

        // Barra superior: método | URL | botão
        JPanel top = new JPanel(new BorderLayout(6, 0));
        top.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        top.add(methodBox, BorderLayout.WEST);
        top.add(urlField, BorderLayout.CENTER);
        top.add(sendButton, BorderLayout.EAST);

        // Requisição: abas Headers e Body
        JTabbedPane requestTabs = new JTabbedPane();
        requestTabs.addTab("Headers (Nome: valor)", new JScrollPane(requestHeadersArea));
        requestTabs.addTab("Body", new JScrollPane(requestBodyArea));

        // Resposta: status em cima, abas Body e Headers
        JTabbedPane responseTabs = new JTabbedPane();
        responseTabs.addTab("Body", new JScrollPane(responseBodyArea));
        responseTabs.addTab("Headers", new JScrollPane(responseHeadersArea));

        JPanel responsePanel = new JPanel(new BorderLayout());
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        responsePanel.add(statusLabel, BorderLayout.NORTH);
        responsePanel.add(responseTabs, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, requestTabs, responsePanel);
        split.setResizeWeight(0.35);

        add(top, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        sendButton.addActionListener(e -> send());
        urlField.addActionListener(e -> send());   // Enter no campo de URL também envia
    }

    private void send() {
        HttpRequestData req;
        try {
            req = buildRequest();
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
            return;
        }

        sendButton.setEnabled(false);
        statusLabel.setText("Enviando...");

        new SwingWorker<HttpResponseData, Void>() {
            @Override
            protected HttpResponseData doInBackground() throws Exception {
                return engine.send(req);          // roda fora da thread da janela
            }

            @Override
            protected void done() {               // volta pra thread da janela
                try {
                    showResponse(get());
                } catch (ExecutionException e) {
                    showError(describe(e.getCause()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    sendButton.setEnabled(true);
                }
            }
        }.execute();
    }

    private HttpRequestData buildRequest() {
        String url = urlField.getText().trim();
        if (url.isEmpty()) {
            throw new IllegalArgumentException("Informe a URL.");
        }
        String lower = url.toLowerCase();
        if (!lower.startsWith("http://") && !lower.startsWith("https://")) {
            throw new IllegalArgumentException("A URL precisa começar com http:// ou https://");
        }

        Map<String, String> headers = new LinkedHashMap<>();
        for (String line : requestHeadersArea.getText().split("\n")) {
            if (line.isBlank()) continue;
            int sep = line.indexOf(':');
            if (sep <= 0) {
                throw new IllegalArgumentException("Header inválido (use Nome: valor): " + line);
            }
            headers.put(line.substring(0, sep).trim(), line.substring(sep + 1).trim());
        }

        return new HttpRequestData(
                (String) methodBox.getSelectedItem(),
                url,
                headers,
                requestBodyArea.getText());
    }

    private void showResponse(HttpResponseData res) {
        statusLabel.setText(res.status() + "   •   " + res.ms() + " ms");

        StringBuilder sb = new StringBuilder();
        res.headers().forEach((k, v) -> sb.append(k).append(": ").append(v).append('\n'));
        responseHeadersArea.setText(sb.toString());
        responseBodyArea.setText(res.body());

        responseHeadersArea.setCaretPosition(0);
        responseBodyArea.setCaretPosition(0);
    }

    private void showError(String message) {
        statusLabel.setText("Erro");
        responseHeadersArea.setText("");
        responseBodyArea.setText(message);
    }

    private String describe(Throwable t) {
        if (t instanceof HttpTimeoutException) {
            return "Tempo esgotado: o servidor demorou demais para responder.";
        }
        if (t instanceof ConnectException) {
            return "Não foi possível conectar ao servidor.";
        }
        if (t instanceof IllegalArgumentException) {
            return "Requisição inválida: " + t.getMessage();
        }
        return t.getClass().getSimpleName() + ": " + t.getMessage();
    }

//    private static JTextArea mono(boolean editable) {
//        JTextArea area = new JTextArea();
//        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
//        area.setEditable(editable);
//        return area;
//    }

    private static JTextArea mono(boolean editable) {
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setEditable(editable);
        area.setBackground(new Color(0x1E1E1E));
        area.setForeground(new Color(0xDDDDDD));
        area.setCaretColor(new Color(0xDDDDDD));
        return area;
    }
}