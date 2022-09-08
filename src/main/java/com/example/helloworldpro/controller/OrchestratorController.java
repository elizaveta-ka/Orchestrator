package com.example.helloworldpro.controller;

import com.example.helloworldpro.kafka.MessageListener;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orchestra")
public class OrchestratorController {
private MessageListener messageListener;

    public OrchestratorController(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @GetMapping
    public String sendResponse() throws IOException {
        // Создать Httpclient, который эквивалентен открытию браузера
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // Создать запрос HttpGet, эквивалентный вводу адреса в браузере
        HttpGet httpGet = new HttpGet("http://localhost:8082/api/hello");
        String resp = null;
        CloseableHttpResponse response = null;

        try {
            // Выполнить запрос, который эквивалентен нажатию Enter после выбивания адреса, чтобы получить ответ
            response = httpClient.execute(httpGet);

            // Определяем, равен ли статус возврата 200
            if (response.getStatusLine().getStatusCode() == 200) {
                // Анализируем ответ и получаем данные
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
//                if(content.contains("Ok")) {
                    resp = content;

//                    URL url1 = new URL("http://localhost:3000");
//                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
//                    connection1.setRequestMethod("GET");
//                    connection1.setDoOutput(true);
//                    try (BufferedReader br1 = new BufferedReader(new InputStreamReader(
//                            connection1.getInputStream()))) {
//                        String line1;
//                        while ((line1 = br1.readLine()) != null) {
//                            System.out.println(line1);
//                        }
//                    } catch (Exception e) {
//                        assert e != null;
//                        e.printStackTrace();
//                    }
//                } else {
//                    resp = "Fail response";
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                // Закрыть ресурсы
                response.close();
            }
            // Закрыть браузер
            httpClient.close();
        }

        return resp;
    }

}
