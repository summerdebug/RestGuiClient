package com.example.client;

import java.awt.event.ActionEvent;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Flux;

public final class RestClient {

    private RestClient() {
        throw new AssertionError("Suppress default constructor");
    }

    static void submit(ActionEvent ignore) {
        Ui.RESPONSE_AREA.setText("");
        String requestType = (String) Ui.REQUEST_TYPE_SELECT.getSelectedItem();
        switch (requestType) {
            case "GET":
                submitGetRequest();
                break;
            case "POST":
                submitPostRequest();
                break;
            default:
                Ui.RESPONSE_AREA.append(requestType + " request type not implemented");
        }
    }

    /**
     * To add employee, put it to Request body window:
     * {"name" : "Bill", "role" : "teacher"}
     */
    private static void submitPostRequest() {
        String url = Ui.URL_FIELD.getText();
        WebClient client = WebClient
                .builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String body = Ui.REQUEST_AREA.getText();
        Employee employee = client
                .post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();

        Ui.RESPONSE_AREA.append("Employee added" + System.lineSeparator() + employee);
    }

    /**
     * It can get single or list of employees: http://localhost:8080/employees/1 or http://localhost:8080/employees
     */
    private static void submitGetRequest() {
        String url = Ui.URL_FIELD.getText();
        WebClient client = WebClient.create(url);
        RequestHeadersUriSpec<?> request = client.get();
        Flux<Employee> employeesFlux = request.retrieve()
                .bodyToFlux(Employee.class);
        List<Employee> employees = employeesFlux.collectList().block();
        for (Employee employee : employees) {
            Ui.RESPONSE_AREA.append(employee + System.lineSeparator());
        }
    }
}
