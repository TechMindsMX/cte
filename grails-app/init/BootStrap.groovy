import com.cte.Role
import com.cte.User
import com.cte.UserRole
import com.cte.Profile
import com.cte.Bank
import com.cte.Company

import grails.util.Environment

class BootStrap {

  def springSecurityService

  def init = { servletContext ->
    saveBanksInfo()
    //createUuidToCompanyWithUuidNull()
    //if (!User.count()) {
    //  createUserWithRole('admin', 'admin', 'admin@email.com', 'ROLE_ADMIN')
    //  createUserWithRole('integrado', 'admin', 'integrado@email.com', 'ROLE_INTEGRADO')
    //  createUserWithRole('autorizador', 'autorizador', 'autorizador@email.com', 'ROLE_INTEGRADO_AUTORIZADOR')
    //}
  }

  def destroy = {
  }


  def createUuidToCompanyWithUuidNull() {
    def companyList = Company.findAllByUuid("")
    companyList.each{ company ->
      company.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
      company.save()
    }
  }

  def createUserWithRole(String username, String password, String email, String authority) {
    if(Environment.current != Environment.PRODUCTION){
      def userRole = Role.findByAuthority(authority)
      def user = User.findByUsername(username) ?: new User(username:username,
      password:password,
      enabled:true,
      profile:new Profile(name:username,
      lastName:'lastName',
      motherLastName:'motherLastName',
      email:email)).save()

      if(!UserRole.get(user.id,userRole.id))
        UserRole.create user, userRole, true
    }
  }

  def saveBanksInfo(){

    if(!Bank.findByBankingCode("2001")){
      [new Bank(bankingCode:"2001",name:"BANXICO"),
      new Bank(bankingCode:"37006",name:"BANCOMEXT"),
      new Bank(bankingCode:"37009",name:"BANOBRAS"),
      new Bank(bankingCode:"37019",name:"BANJERCITO"),
      new Bank(bankingCode:"37135",name:"NAFIN"),
      new Bank(bankingCode:"37166",name:"BANSEFI"),
      new Bank(bankingCode:"37168",name:"HIPOTECARIA FED"),
      new Bank(bankingCode:"40002",name:"BANAMEX"),
      new Bank(bankingCode:"40012",name:"BBVA BANCOMER"),
      new Bank(bankingCode:"40014",name:"SANTANDER"),
      new Bank(bankingCode:"40021",name:"HSBC"),
      new Bank(bankingCode:"40030",name:"BAJIO"),
      new Bank(bankingCode:"40032",name:"IXE"),
      new Bank(bankingCode:"40036",name:"INBURSA"),
      new Bank(bankingCode:"40037",name:"INTERACCIONES"),
      new Bank(bankingCode:"40042",name:"MIFEL"),
      new Bank(bankingCode:"40044",name:"SCOTIABANK"),
      new Bank(bankingCode:"40058",name:"BANREGIO"),
      new Bank(bankingCode:"40059",name:"INVEX"),
      new Bank(bankingCode:"40060",name:"BANSI"),
      new Bank(bankingCode:"40062",name:"AFIRME"),
      new Bank(bankingCode:"40072",name:"BANORTE"),
      new Bank(bankingCode:"40102",name:"INVESTA BANK"),
      new Bank(bankingCode:"40103",name:"AMERICAN EXPRES"),
      new Bank(bankingCode:"40106",name:"BAMSA"),
      new Bank(bankingCode:"40108",name:"TOKYO"),
      new Bank(bankingCode:"40110",name:"JP MORGAN"),
      new Bank(bankingCode:"40112",name:"BMONEX"),
      new Bank(bankingCode:"40113",name:"VE POR MAS"),
      new Bank(bankingCode:"40124",name:"DEUTSCHE"),
      new Bank(bankingCode:"40126",name:"CREDIT SUISSE"),
      new Bank(bankingCode:"40127",name:"AZTECA"),
      new Bank(bankingCode:"40128",name:"AUTOFIN"),
      new Bank(bankingCode:"40129",name:"BARCLAYS"),
      new Bank(bankingCode:"40130",name:"COMPARTAMOS"),
      new Bank(bankingCode:"40131",name:"BANCO FAMSA"),
      new Bank(bankingCode:"40132",name:"BMULTIVA"),
      new Bank(bankingCode:"40133",name:"ACTINVER"),
      new Bank(bankingCode:"40134",name:"WAL-MART"),
      new Bank(bankingCode:"40136",name:"INTERBANCO"),
      new Bank(bankingCode:"40137",name:"BANCOPPEL"),
      new Bank(bankingCode:"40138",name:"ABC CAPITAL"),
      new Bank(bankingCode:"40139",name:"UBS BANK"),
      new Bank(bankingCode:"40140",name:"CONSUBANCO"),
      new Bank(bankingCode:"40141",name:"VOLKSWAGEN"),
      new Bank(bankingCode:"40143",name:"CIBANCO"),
      new Bank(bankingCode:"40145",name:"BBASE"),
      new Bank(bankingCode:"40146",name:"BICENTENARIO"),
      new Bank(bankingCode:"40147",name:"AGROFINANZAS"),
      new Bank(bankingCode:"40148",name:"PAGATODO"),
      new Bank(bankingCode:"40150",name:"INMOBILIARIO"),
      new Bank(bankingCode:"40151",name:"DONDE"),
      new Bank(bankingCode:"40152",name:"BANCREA"),
      new Bank(bankingCode:"90600",name:"MONEXCB"),
      new Bank(bankingCode:"90601",name:"GBM"),
      new Bank(bankingCode:"90602",name:"MASARI"),
      new Bank(bankingCode:"90605",name:"VALUE"),
      new Bank(bankingCode:"90606",name:"ESTRUCTURADORES"),
      new Bank(bankingCode:"90607",name:"TIBER"),
      new Bank(bankingCode:"90608",name:"VECTOR"),
      new Bank(bankingCode:"90610",name:"B&B"),
      new Bank(bankingCode:"90614",name:"ACCIVAL"),
      new Bank(bankingCode:"90615",name:"MERRILL LYNCH"),
      new Bank(bankingCode:"90616",name:"FINAMEX"),
      new Bank(bankingCode:"90617",name:"VALMEX"),
      new Bank(bankingCode:"90618",name:"UNICA"),
      new Bank(bankingCode:"90619",name:"MAPFRE"),
      new Bank(bankingCode:"90620",name:"PROFUTURO"),
      new Bank(bankingCode:"90621",name:"CB ACTINVER"),
      new Bank(bankingCode:"90622",name:"OACTIN"),
      new Bank(bankingCode:"90623",name:"SKANDIA"),
      new Bank(bankingCode:"90626",name:"CBDEUTSCHE"),
      new Bank(bankingCode:"90627",name:"ZURICH"),
      new Bank(bankingCode:"90628",name:"ZURICHVI"),
      new Bank(bankingCode:"90630",name:"CB INTERCAM"),
      new Bank(bankingCode:"90631",name:"CI BOLSA"),
      new Bank(bankingCode:"90634",name:"FINCOMUN"),
      new Bank(bankingCode:"90636",name:"HDI SEGUROS"),
      new Bank(bankingCode:"90637",name:"ORDER"),
      new Bank(bankingCode:"90638",name:"AKALA"),
      new Bank(bankingCode:"90640",name:"CB JPMORGAN"),
      new Bank(bankingCode:"90642",name:"REFORMA"),
      new Bank(bankingCode:"90646",name:"STP"),
      new Bank(bankingCode:"90647",name:"TELECOMM"),
      new Bank(bankingCode:"90648",name:"EVERCORE"),
      new Bank(bankingCode:"90649",name:"OSKNDIA"),
      new Bank(bankingCode:"90651",name:"SEGMTY"),
      new Bank(bankingCode:"90652",name:"ASEA"),
      new Bank(bankingCode:"90653",name:"KUSPIT"),
      new Bank(bankingCode:"90655",name:"SOFIEXPRESS"),
      new Bank(bankingCode:"90656",name:"UNAGRA"),
      new Bank(bankingCode:"90659",name:"ASP INTEGRA OPC"),
      new Bank(bankingCode:"90670",name:"LIBERTAD"),
      new Bank(bankingCode:"90671",name:"HUASTECAS"),
      new Bank(bankingCode:"90673",name:"GNP"),
      new Bank(bankingCode:"90674",name:"AXA"),
      new Bank(bankingCode:"90675",name:"FICREA"),
      new Bank(bankingCode:"90677",name:"CAJA POP MEXICA"),
      new Bank(bankingCode:"90678",name:"SURA"),
      new Bank(bankingCode:"90901",name:"CLS"),
      new Bank(bankingCode:"90902",name:"INDEVAL")]*.save()
    }
  }
}
