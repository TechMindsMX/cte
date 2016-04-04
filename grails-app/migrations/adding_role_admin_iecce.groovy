databaseChangeLog = {

	changeSet(author: "says33 (manual)",id: "creating-role-admin-iecce") {

	preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
  	sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_ADMIN_IECCE'")
  }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_ADMIN_IECCE',0) """)

	}

}