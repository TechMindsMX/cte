
databaseChangeLog = {

	changeSet(author: "says33 (manual)",id: "creating-role-integrado-autorizador-and-delete-autorizador") {

		preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
      sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_INTEGRADO_AUTORIZADOR'")
    }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_INTEGRADO_AUTORIZADOR',0) """)

    sql(""" UPDATE user_role set role_id = (select r.id from role r where authority = 'ROLE_INTEGRADO_AUTORIZADOR') where role_id = (select ro.id from role ro where authority = 'ROLE_AUTORIZADOR') """)

    sql(""" delete from role where authority = 'ROLE_AUTORIZADOR' """)

	}

}