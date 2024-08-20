package org.example;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class Main {
    private static final String PROMETHEUS_URl = "";

    public static void main(String[] args) {
        String query = "tps";
        String start = "";
        String end = "";
        String step = "10";

        String url = String.format("%s?query=%s&start=%s&end=%s?step%s", PROMETHEUS_URl, query, start, end, step);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            PrometheusResponse prometheusResponse = gson.fromJson(jsonResponse, PrometheusResponse.class);
            try (FileWriter fw = new FileWriter("src/main/resources/metrics.csv")) {
                fw.append("Метрика,Значение\n");

                if ("success".equals(prometheusResponse.getStatus())) {

                    for (PrometheusResponse.Result result : prometheusResponse.getData()) {
                        for (Metric metric : result.getResult()) {
                            fw.append(metric.getMetric()).append(",").append(String.valueOf(metric.getValue())).append("\n");

                        }
                    }
                } else {
                    System.out.println("Ошибка получения данных: " + prometheusResponse.getStatus());
                }
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}