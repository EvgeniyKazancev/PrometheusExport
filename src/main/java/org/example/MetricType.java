package org.example;

public enum MetricType {

    TPS("sum(rate(tps_total[1m]"),
    ACTIVE_USERS("active_users"),
    CPU_USAGE("sum(rate(node_cpu_seconds_total{instance=\"<myserver>\"}[1m])) "),
    MEMORY_USAGE("100 * (1 - (node_memory_MemAvailable_bytes{instance=\"<myserver>\"} / node_memory_MemTotal_bytes{instance=\"server\"}))"),
    DISK_USAGE("100* (node_filesystem_size_bytes{instance=\"<myserver>\"} - " +
            "node_filesystem_free_bytes{instance=\"<myserver>\"}) " +
            "/ node_filesystem_size_bytes{instance=\"<myserver>\"}"),
    NETWORK_RECEIVE("sum(rate(node_network_receive_bytes_total{instance=\"<myserver>\"}[1m]"),
    NETWORK_TRANSMIT("sum(rate(node_network_transmit_bytes_total{instance=\"<myserver>\"}[1m]");
    private final String query;


    MetricType(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
