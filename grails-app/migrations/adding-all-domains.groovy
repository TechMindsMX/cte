databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1459373861362-1") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "addressPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "colony", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "country", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "federal_entity", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "street", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "street_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "suite", type: "VARCHAR(255)")

            column(name: "town", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "zip_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-2") {
        createTable(tableName: "authorization") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "authorizationPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-3") {
        createTable(tableName: "bank") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "bankPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "banking_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-4") {
        createTable(tableName: "bank_account") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "bank_accountPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_number", type: "VARCHAR(11)") {
                constraints(nullable: "false")
            }

            column(name: "banco_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "branch_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "clabe", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-5") {
        createTable(tableName: "business_entity") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "business_entityPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "website", type: "VARCHAR(50)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-6") {
        createTable(tableName: "business_entity_address") {
            column(name: "business_entity_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-7") {
        createTable(tableName: "business_entity_bank_account") {
            column(name: "business_entity_banks_accounts_id", type: "BIGINT")

            column(name: "bank_account_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-8") {
        createTable(tableName: "client_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "client_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "client_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-9") {
        createTable(tableName: "commission") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "commissionPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fee", type: "DECIMAL(11, 2)")

            column(name: "percentage", type: "DECIMAL(5, 2)")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-10") {
        createTable(tableName: "company") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "companyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bussiness_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "employee_numbers", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "gross_annual_billing", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "number_of_authorizations", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tax_regime", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "web_site", type: "VARCHAR(50)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-11") {
        createTable(tableName: "company_address") {
            column(name: "company_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-12") {
        createTable(tableName: "company_bank_account") {
            column(name: "company_banks_accounts_id", type: "BIGINT")

            column(name: "bank_account_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-13") {
        createTable(tableName: "company_business_entity") {
            column(name: "company_business_entities_id", type: "BIGINT")

            column(name: "business_entity_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-14") {
        createTable(tableName: "company_rejected_log") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "company_rejected_logPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "asset_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reason", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "type_class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-15") {
        createTable(tableName: "company_s3asset") {
            column(name: "company_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-16") {
        createTable(tableName: "company_telephone") {
            column(name: "company_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-17") {
        createTable(tableName: "company_user") {
            column(name: "company_actors_id", type: "BIGINT")

            column(name: "user_id", type: "BIGINT")

            column(name: "company_legal_representatives_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-18") {
        createTable(tableName: "compose_name") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "compose_namePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "business_entity_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-19") {
        createTable(tableName: "employee_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "employee_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "employee_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-20") {
        createTable(tableName: "fees_receipt") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "fees_receiptPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "business_entity_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "isr", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva_with_holding", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-21") {
        createTable(tableName: "fees_receipt_s3asset") {
            column(name: "fees_receipt_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-22") {
        createTable(tableName: "first_access_to_legal_representatives") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "first_access_to_legal_representativesPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "url_verification", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-23") {
        createTable(tableName: "legal_representatives_assets") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "legal_representatives_assetsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "asset", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-24") {
        createTable(tableName: "loan_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "loan_orderPK")
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

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-25") {
        createTable(tableName: "loan_order_authorization") {
            column(name: "loan_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-26") {
        createTable(tableName: "payroll") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "payrollPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "apply_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-27") {
        createTable(tableName: "processor_payroll") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "processor_payrollPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name_file", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-28") {
        createTable(tableName: "processor_payroll_payroll") {
            column(name: "processor_payroll_payrolls_id", type: "BIGINT")

            column(name: "payroll_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-29") {
        createTable(tableName: "profile") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "profilePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "birth_date", type: "datetime")

            column(name: "curp", type: "VARCHAR(18)")

            column(name: "email", type: "VARCHAR(200)") {
                constraints(nullable: "false")
            }

            column(name: "gender", type: "VARCHAR(255)")

            column(name: "last_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "mother_last_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "nationality", type: "VARCHAR(255)")

            column(name: "rfc", type: "VARCHAR(50)")

            column(name: "trademark", type: "VARCHAR(100)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-30") {
        createTable(tableName: "profile_s3asset") {
            column(name: "profile_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-31") {
        createTable(tableName: "profile_telephone") {
            column(name: "profile_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-32") {
        createTable(tableName: "provider_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "provider_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "provider_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-33") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "registration_codePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(200)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-34") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-35") {
        createTable(tableName: "s3asset") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "s3assetPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bucket", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "local_path", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "local_url", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "mime_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "original_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "protocol", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-36") {
        createTable(tableName: "telephone") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "telephonePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "extension", type: "VARCHAR(10)")

            column(name: "number", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-37") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-38") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-39") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-40") {
        addUniqueConstraint(columnNames: "rfc", constraintName: "UC_COMPANYRFC_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-41") {
        addUniqueConstraint(columnNames: "email", constraintName: "UC_PROFILEEMAIL_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "profile")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-42") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "role")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-43") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-44") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_18m22ybal0ga91hy3s5l66bul", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-45") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "employee_link", constraintName: "FK_3y480lpgswma3dlahkas0piv2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-46") {
        addForeignKeyConstraint(baseColumnNames: "company_business_entities_id", baseTableName: "company_business_entity", constraintName: "FK_5iomp5vtj1eh4ykkyf02cd808", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-47") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "fees_receipt", constraintName: "FK_7q3lp7dsc51couicv3tdeuhoh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-48") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_order_authorization", constraintName: "FK_7s9iidg2nghqx5vm8ufivle2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-49") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "commission", constraintName: "FK_a64q6jugjh0qqbrmik3wc4m57", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-50") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-51") {
        addForeignKeyConstraint(baseColumnNames: "profile_telephones_id", baseTableName: "profile_telephone", constraintName: "FK_atewti54qfefm3c47tl56bc6q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-52") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "company_telephone", constraintName: "FK_b9vi6vh4wjnnlhg3fiqhibbn0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-53") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_addresses_id", baseTableName: "business_entity_address", constraintName: "FK_bi96koa3fssjo9uxnnivj99hy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-54") {
        addForeignKeyConstraint(baseColumnNames: "company_banks_accounts_id", baseTableName: "company_bank_account", constraintName: "FK_br0e1cf387hqfyl8r6kwgda39", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-55") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "company_user", constraintName: "FK_c5ho50yl88ujw9fvhyicsmb13", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-56") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "company_business_entity", constraintName: "FK_cndhy0ioljqk55e72fq57caui", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-57") {
        addForeignKeyConstraint(baseColumnNames: "processor_payroll_payrolls_id", baseTableName: "processor_payroll_payroll", constraintName: "FK_d9u0dl6mex5wpx7o2ax1y2r4p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "processor_payroll")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-58") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "company_address", constraintName: "FK_fuicds1wwge4vwujato9u3cks", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-59") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_authorizations_id", baseTableName: "loan_order_authorization", constraintName: "FK_gh6vd4lpw7rfi6udgpmefs9r7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-60") {
        addForeignKeyConstraint(baseColumnNames: "company_actors_id", baseTableName: "company_user", constraintName: "FK_glhg26i028exp1dyq3wug04bh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-61") {
        addForeignKeyConstraint(baseColumnNames: "company_legal_representatives_id", baseTableName: "company_user", constraintName: "FK_h26e77n4ja7q3bl2te4cvc1t0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-62") {
        addForeignKeyConstraint(baseColumnNames: "payroll_id", baseTableName: "processor_payroll_payroll", constraintName: "FK_h4r345284os77tyws5bh4ty56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "payroll")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-63") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_documents_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_i78rkp7yywh4tembtx5ihxm7n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-64") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-65") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "business_entity_address", constraintName: "FK_jur33w6ryvek3oa3dngwt8lqw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-66") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "payroll", constraintName: "FK_kfot01i7baasiwuerg4r5cxgp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-67") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_mxvud6egd9nvaa0dtuo4rch8s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-68") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_banks_accounts_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ndjediucqay0cjd4o508k0yxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-69") {
        addForeignKeyConstraint(baseColumnNames: "company_addresses_id", baseTableName: "company_address", constraintName: "FK_nrso9xpub8w0ncciyafd6qq4d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-70") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ny4e4sb5gwjknkbkimh9ad12e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-71") {
        addForeignKeyConstraint(baseColumnNames: "company_documents_id", baseTableName: "company_s3asset", constraintName: "FK_owheu11vhnpfmqfpd4y01k1ct", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-72") {
        addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "bank_account", constraintName: "FK_p03yka0x638p1lt4fu2f8cafh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-73") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "authorization", constraintName: "FK_p0svctns3tvk3nsg2ofu95f6y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-74") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "company_bank_account", constraintName: "FK_plp60f9wa9rbix1e3wegi3o4t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-75") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "provider_link", constraintName: "FK_q6rmqkgm8gwg1qtpvu4eu25m5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-76") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_qatwcpuy6ur5hskwnmo8spjdw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-77") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "company_s3asset", constraintName: "FK_qmcq07p0niw1bxin654va8xxv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-78") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "compose_name", constraintName: "FK_rjqgae44682dns471c7hweb8e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-79") {
        addForeignKeyConstraint(baseColumnNames: "company_telephones_id", baseTableName: "company_telephone", constraintName: "FK_s14mlklg07gxek8vc2qmg5yk4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-80") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "client_link", constraintName: "FK_s2tgg8i7gbg9fgnkb7j05vhh1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-81") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "profile_telephone", constraintName: "FK_s6qu1iyo2xra9o42sj0vney", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-82") {
        addForeignKeyConstraint(baseColumnNames: "profile_documents_id", baseTableName: "profile_s3asset", constraintName: "FK_tf5ckj7ugmmbtd0nku9nm1hs5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-83") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "profile_s3asset", constraintName: "FK_vevapj0ikfqe1s21iy1d9yyf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1459373861362-84") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_order", constraintName: "FK_wbaipjtxlax1ajhy32amn5pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }
}
