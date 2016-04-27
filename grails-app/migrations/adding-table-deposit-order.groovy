databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1461787736677-1") {
        createTable(tableName: "deposit_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "deposit_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid_transaction", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-2") {
        createTable(tableName: "deposit_order_authorization") {
            column(name: "deposit_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-3") {
        createTable(tableName: "deposit_order_s3asset") {
            column(name: "deposit_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-4") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_documents_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_78mxqus4c33f8agef1720orat", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-5") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_fc4511gyg27b7fsbnh548xidq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-6") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_authorizations_id", baseTableName: "deposit_order_authorization", constraintName: "FK_gd2dhyoj1fmwk484482lnquso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-7") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "deposit_order_authorization", constraintName: "FK_lfljxhh8n5pik85wl02g2cuj4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "makingdevs (generated)", id: "1461787736677-8") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "deposit_order", constraintName: "FK_qxtq9xmyhwqmc3b2o4ty4ge8g", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }
}
