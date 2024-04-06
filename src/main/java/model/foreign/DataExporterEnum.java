package model.foreign;

import model.foreign.exporting.*;

public enum DataExporterEnum {
    CSV(new CSVDataExporter()),
    XML(new XMLDataExporter()),
    JSON(new JSONDataExporter());

    private DataExporter exporter;

    private DataExporterEnum(DataExporter exporter) {
        this.exporter = exporter;
    }

    public DataExporter getExporter() {
        return exporter;
    }
}
