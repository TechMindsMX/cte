
databaseChangeLog = {

	changeSet(author: "says33 (manual)",id: "creating-role-operador_iecce") {

	preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
  	sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_OPERADOR_IECCE'")
  }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_OPERADOR_IECCE',0) """)

	}

}