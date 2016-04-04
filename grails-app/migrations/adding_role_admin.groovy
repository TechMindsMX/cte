databaseChangeLog = {

  changeSet(author: "says33 (manual)", id: "creating-profile-admin") {

      preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
        sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_ADMIN'")
      }

      sql(""" INSERT INTO role(authority,version) values ('ROLE_ADMIN',0) """ )

  }
}