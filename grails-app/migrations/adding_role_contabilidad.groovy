databaseChangeLog = {

  changeSet(author: "says33 (manual)", id:"adding_role_contabilidad") {
    preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
  	  sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_CONTABILIDAD'")
    }

    sql(""" INSERT INTO role(authority,version) value('ROLE_CONTABILIDAD',0) """)

  }

}
