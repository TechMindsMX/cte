databaseChangeLog = {

  changeSet(author: "says33 (manual)", id: "creating-profile-integrado") {

      preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
        sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_INTEGRADO'")
      }

      sql(""" INSERT INTO role(authority,version) values ('ROLE_INTEGRADO',0) """ )

  }
}