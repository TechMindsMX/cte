databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1461687005731-1") {
        addColumn(tableName: "processor_payroll") {
            column(name: "company_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1461687005731-2") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "processor_payroll", constraintName: "FK_dfw8x1hd8sf47lskaiphuusvl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }
}
