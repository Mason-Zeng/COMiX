package model.foreign;

import model.foreign.importing.*;

public enum DataImporterEnum {
    CSV(new CSVDataImporter()),
    XML(new XMLDataImporter()),
    JSON(new JSONDataImporter());

    private DataImporter importer;

    private DataImporterEnum(DataImporter importer) {
        this.importer = importer;
    }

    public static DataImporter getImporter(String fileType) {
        return Enum.valueOf(DataImporterEnum.class, fileType).importer;
    }
}
