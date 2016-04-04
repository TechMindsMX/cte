databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1459786791948-1") {
        addColumn(tableName: "user") {
            column(name: "profile_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459786791948-2") {
        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "user", constraintName: "FK_1mcjtpxmwom9h9bf2q0k412e0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }
}
