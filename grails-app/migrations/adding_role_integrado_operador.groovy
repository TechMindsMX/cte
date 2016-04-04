databaseChangeLog = {

	changeSet(author: "says33 (manual)",id: "creating-role-integrado-operador") {

	preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
  	sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_INTEGRADO_OPERADOR'")
  }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_INTEGRADO_OPERADOR',0) """)

	}

}