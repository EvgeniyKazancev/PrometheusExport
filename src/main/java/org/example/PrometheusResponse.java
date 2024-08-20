package org.example;


public class PrometheusResponse {
    private String status;
    private Result[] data;

    public static class Result {
        private Metric[] result;


        public Metric[] getResult() {
            return result;
        }

        public Result(Metric[] result) {
            this.result = result;
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result[] getData() {
        return data;
    }

    public void setData(Result[] data) {
        this.data = data;
    }
}
