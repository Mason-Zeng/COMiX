package model.foreign;

import model.foreign.importing.*;

public enum DataImporterEnum {

    //TODO Fix CSV and uncomment this
    CSV(new CSVDataImporter()),
    XML(new XMLDataImporter()),
    JSON(new JSONDataImporter());

    private DataImporter importer;

    private DataImporterEnum(DataImporter importer) {
        this.importer = importer;
    }

    public DataImporter getImporter() {
        return importer;
    }
}
