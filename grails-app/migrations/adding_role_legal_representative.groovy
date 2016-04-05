databaseChangeLog = {

  changeSet(author: "says33 (manual)", id: "creating-profile-legal") {

    preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
      sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_LEGAL_REPRESENTATIVE'")
    }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_LEGAL_REPRESENTATIVE',0) """ )

  }
}
